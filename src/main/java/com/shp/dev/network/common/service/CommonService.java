package com.shp.dev.network.common.service;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.util.Base64;
import com.shp.dev.network.common.util.ShpUtils;
import com.shp.dev.network.common.util.file.UploadFileUtils;
import com.shp.dev.network.common.util.image.ImageUtil;
import com.shp.dev.network.common.util.minio.MinioClientUtils;
import com.shp.dev.network.common.util.redis.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 共用业务类
 * @CreateTime: 2020/12/11 16:33
 * @PackageName: com.shp.dev.network.common.service
 * @ProjectName: network
 */

@Service
@Slf4j
public class CommonService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ShpUtils shpUtils;

    @Autowired
    RedisConfig redisConfig;

    public ResultBean opsForValueSet(String key, String value, Integer db) {
        try {
            if (db != null && db > 0) {
                redisConfig.getRedisTemplate(db).opsForValue().set(key, value);
                return ResultBean.success();
            }
            redisTemplate.opsForValue().set(key, value);
            return ResultBean.success();
        } catch (Exception e) {
            return ResultBean.error("插入redis失败" + e.getMessage());
        }
    }

    public ResultBean opsForValueGet(String key, Integer db) {
        try {
            if (db != null && db > 0) {
                return ResultBean.success("获取redis成功", redisConfig.getRedisTemplate(db).opsForValue().get(key));
            }
            return ResultBean.success("获取redis成功", redisTemplate.opsForValue().get(key));
        } catch (Exception e) {
            return ResultBean.error("获取redis失败" + e.getMessage());
        }
    }

    public ResultBean opsForListSet(String key, List list, Integer db) {
        try {
            if (db != null && db > 0) {
                return ResultBean.success("插入redis成功", redisConfig.getRedisTemplate(db).opsForList().rightPushAll(key, list));
            }
            return ResultBean.success("插入redis成功", redisTemplate.opsForList().rightPushAll(key, list));
        } catch (Exception e) {
            return ResultBean.error("插入redis失败" + e.getMessage());
        }
    }

    public ResultBean opsForListGet(String key, Integer db) {
        try {
            if (db != null && db > 0) {
                return ResultBean.success("获取redis成功", redisConfig.getRedisTemplate(db).opsForList().range(key, 0, -1));
            }
            return ResultBean.success("获取redis成功", redisTemplate.opsForList().range(key, 0, -1));
        } catch (Exception e) {
            return ResultBean.error("获取redis失败" + e.getMessage());
        }
    }

    public ResultBean opsForHashGet(String key, Integer db) {
        try {
            if (db != null && db > 0) {
                return ResultBean.success("获取redis成功", redisConfig.getRedisTemplate(db).opsForHash().entries(key));
            }
            return ResultBean.success("获取redis成功", redisTemplate.opsForHash().entries(key));
        } catch (Exception e) {
            return ResultBean.error("获取redis失败" + e.getMessage());
        }
    }

    public ResultBean opsForHashSet(String key, Map map, Integer db) {
        try {
            if (db != null && db > 0) {
                redisConfig.getRedisTemplate(db).opsForHash().putAll(key, map);
                return ResultBean.success("插入redis成功");
            }
            redisTemplate.opsForHash().putAll(key, map);
            return ResultBean.success("插入redis成功");
        } catch (Exception e) {
            return ResultBean.error("插入redis失败" + e.getMessage());
        }
    }

    public ResultBean toBase64(MultipartFile file) {
        try {
            BASE64Encoder bEncoder = new BASE64Encoder();
            String[] suffixArra = file.getOriginalFilename().split("\\.");
            String preffix = "data:image/jpg;base64,".replace("jpg", suffixArra[suffixArra.length - 1]);
            String image = preffix + bEncoder.encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");
            return ResultBean.success("转base64成功", image.substring(22));
        } catch (Exception e) {
            return ResultBean.error("转base64失败" + e.getMessage());
        }
    }

    public ResultBean upload(MultipartFile file, String fileName, String frist, String last) {
        String filePath = null;

        if (file.isEmpty() || shpUtils.isNull(file)) {
            return ResultBean.error("上传失败 文件为空");
        }

        if (shpUtils.isNull(fileName)) {
            fileName = UUID.randomUUID() + file.getOriginalFilename();
        }

        if (shpUtils.noNull(frist) && shpUtils.noNull(last)) {
            filePath = UploadFileUtils.currencySaveFile(file, fileName, frist, last);
        }

        if (shpUtils.isNull(frist) && shpUtils.isNull(last)) {
            filePath = UploadFileUtils.currencySaveFile(file, fileName);
        }

        if (shpUtils.noNull(frist) && shpUtils.isNull(last)) {
            filePath = UploadFileUtils.currencySaveFileFrist(file, fileName, frist);
        }

        if (shpUtils.noNull(last) && shpUtils.isNull(frist)) {
            filePath = UploadFileUtils.currencySaveFileLast(file, fileName, last);
        }

        if (filePath != null) {
            return ResultBean.success("上传成功", filePath);
        }

        return ResultBean.error("上传失败");
    }


    public ResultBean code() {
        try {
            // 转换流信息写出
            FastByteArrayOutputStream out = new FastByteArrayOutputStream();
            Map<String, Object> map = ImageUtil.generateCodeAndPic();
            ImageIO.write((RenderedImage) map.get("codePic"), "jpg", out);
            ResultBean resultBean = new ResultBean();
            resultBean.put("uuid", map.get("code"));
            resultBean.put("img", "data:image/jpg;base64,"+Base64.encode(out.toByteArray()));
            resultBean.put(ResultBean.CODE, 200);
            return resultBean;
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
        }
    }

    public ResultBean uploadMinio(String fileName, String objectName) {
        String upload = MinioClientUtils.upload(fileName, objectName);
        if (upload != null) {
            return ResultBean.success(upload);
        }
        return ResultBean.error();
    }


    public ResultBean updateService(MultipartFile file) {

        try {

            //假设保存接口的地址是/usr/local/project/目录
            String serverPath = System.getProperty("user.dir") + File.separator;
            //服务名称
            String serverName = file.getOriginalFilename();
            //日志名称
            String logName = serverName.substring(0, serverName.length() - 3) + "log";
            //shell脚本名称
            String shellName = serverName.substring(0, serverName.length() - 3) + "sh";
            //模拟linux命令操作
            Runtime.getRuntime().exec("cd " + serverPath);
            Runtime.getRuntime().exec("rm -rf " + serverName + " " + logName);
            //复制jar包到程序运行所在目录
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(serverName));

            //复制shell脚本到程序运行所在目录
            ClassPathResource cpr = new ClassPathResource("static/update.sh");
            InputStream in = cpr.getInputStream();
            FileUtils.copyInputStreamToFile(in, new File(shellName));

            //执行shell脚本
            Runtime.getRuntime().exec("chmod 777 " + shellName);
            Runtime.getRuntime().exec("bash " + shellName + " " + serverName + " " + logName + " " + serverName);

        } catch (Exception e) {
            return ResultBean.error();
        }
        return ResultBean.success();
    }

}
