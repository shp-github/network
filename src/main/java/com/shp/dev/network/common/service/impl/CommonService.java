package com.shp.dev.network.common.service.impl;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.config.StartedUpRunner;
import com.shp.dev.network.common.service.ICommonService;
import com.shp.dev.network.common.util.base64.Base64;
import com.shp.dev.network.common.util.DateUtils;
import com.shp.dev.network.common.util.ShpUtils;
import com.shp.dev.network.common.util.code.MongoManager;
import com.shp.dev.network.common.util.code.dao.GeneratorDao;
import com.shp.dev.network.common.util.code.util.GenUtils;
import com.shp.dev.network.common.util.file.CommonFileUtils;
import com.shp.dev.network.common.util.image.ImageUtil;
import com.shp.dev.network.common.util.minio.MinioClientUtils;
import com.shp.dev.network.common.util.redis.RedisConfig;
import com.shp.dev.network.common.util.request.HttpRequestUrl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.zip.ZipOutputStream;

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
public class CommonService implements ICommonService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ShpUtils shpUtils;

    @Autowired
    RedisConfig redisConfig;

    @Autowired
    private GeneratorDao generatorDao;

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


    public ResultBean upload(MultipartFile file, String fileName, String frist, String last) {
        if (file.isEmpty() || shpUtils.isNull(file)) {
            return ResultBean.error("上传失败 文件为空");
        }
        String filePath = CommonFileUtils.saveFile(file, fileName, frist, last);
        if (filePath != null) {
            return ResultBean.success("上传成功", filePath);
        }
        return ResultBean.error("上传失败");
    }

    @Override
    public ResultBean code() {
        try {
            // 转换流信息写出
            FastByteArrayOutputStream out = new FastByteArrayOutputStream();
            Map<String, Object> map = ImageUtil.generateCodeAndPic();
            ImageIO.write((RenderedImage) map.get("codePic"), "jpg", out);
            ResultBean resultBean = new ResultBean();
            resultBean.put("uuid", map.get("code"));
            resultBean.put("img", "data:image/jpg;base64," + Base64.encode(out.toByteArray()));
            resultBean.put(ResultBean.CODE, 200);
            return resultBean;
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
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

    @Override
    public ResultBean file2base(MultipartFile file) {
        try {
            return ResultBean.success("转base64成功", new BASE64Encoder().encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", ""));
        } catch (Exception e) {
            return ResultBean.error("转base64失败" + e.getMessage());
        }
    }

    @Override
    public void base2file(String base64, HttpServletResponse res) {
        try {
            PrintWriter p = null;
            String s = CommonFileUtils.saveFile(base64, null, null, null);
            System.out.println(s);
            p = res.getWriter();
            p.print(s);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public ResultBean shell(String str) {
        try {
            Runtime.getRuntime().exec(str);
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
        }
        return ResultBean.success();
    }

    @Override
    public ResultBean request(String url, String parm) {
        try {
            return ResultBean.success(HttpRequestUrl.sendPost(url, parm));
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
        }
    }

    @Override
    public ResultBean getDateTime() {
        return ResultBean.success(DateUtils.getDateTime());
    }

    @Override
    public ResultBean concurrentRequest(String url, String parm, Integer max) {
        CopyOnWriteArrayList<String> strings = HttpRequestUrl.ConcurrentRun(url, parm, max);
        return ResultBean.success(strings);
    }

    public ResultBean uploadMinio(String fileName, String objectName) {
        String upload = MinioClientUtils.upload(fileName, objectName);
        if (upload != null) {
            return ResultBean.success(upload);
        }
        return ResultBean.error();
    }


    /**
     * 1、“_blank”的意思：
     * 浏览器总在一个新打开、未命名的窗口中载入目标文档。
     * 2、“_parent”的意思：
     * 这个目标使得文档载入父窗口或者包含来超链接引用的框架的框架集。如果这个引用是在窗口或者在顶级框架中，那么它与目标 _self 等效。
     * 3、“_self”的意思：
     * 这个目标的值对所有没有指定目标的 <a> 标签是默认目标，它使得目标文档载入并显示在相同的框架或者窗口中作为源文档。这个目标是多余且不必要的，除非和文档标题 <base> 标签中的 target 属性一起使用。
     * 4、“_top”的意思：
     * 这个目标使得文档载入包含这个超链接的窗口，用 _top 目标将会清除所有被包含的框架并将文档载入整个浏览器窗口。
     */
    public void to(HttpServletResponse res) {
        //设置传输格式
        res.setContentType("text/html;charset=gb2312");
        PrintWriter p = null;
        try {
            p = res.getWriter();
            p.print("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<iframe id=\"mainContent\" width=\"100%\" height=\"100%\"></iframe>\n" +
                    "</body>\n" +
                    "<script>\n" +
                    " window.open(\"http://" + StartedUpRunner.ip + ":" + StartedUpRunner.serverPort + "/swagger-ui.html\", '_top');\n" +
                    "</script>\n" +
                    "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            p.close();
        }
    }


    public ResultBean update(MultipartFile file) {
        try {
            log.info("正在更新接口");
            String serverPath = System.getProperty("user.dir") + File.separator;
            String serverName = file.getOriginalFilename();
            String logName = serverName.substring(0, serverName.length() - 3) + "log";
            String shellName = serverName.substring(0, serverName.length() - 3) + "sh";
            Runtime.getRuntime().exec("cd " + serverPath);
            Runtime.getRuntime().exec("rm -rf " + serverName + " " + logName);
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(serverName));
            ClassPathResource cpr = new ClassPathResource("shell/update.sh");
            InputStream in = cpr.getInputStream();
            FileUtils.copyInputStreamToFile(in, new File(shellName));
            Runtime.getRuntime().exec("chmod 777 " + shellName);
            Runtime.getRuntime().exec("bash " + shellName + " " + serverName + " " + logName + " " + serverName);
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
        }
        return ResultBean.success("更新成功，程序正在启动，请稍后！！！");
    }

    @Override
    public ResultBean restart() {
        try {
            log.info("正在重启接口");
            String serverPath = System.getProperty("user.dir") + File.separator;
            String pn = "network-" + DateUtils.getCustomizeDateTime("MMdd");
            String logName = pn + ".log";
            String shellName = pn + "-restart.sh";
            String serverName = pn + ".jar";
            Runtime.getRuntime().exec("cd " + serverPath);
            ClassPathResource cpr = new ClassPathResource("shell/restart.sh");
            InputStream in = cpr.getInputStream();
            FileUtils.copyInputStreamToFile(in, new File(shellName));
            Runtime.getRuntime().exec("chmod 777 " + shellName);
            Runtime.getRuntime().exec("bash " + shellName + " " + StartedUpRunner.pid + " " + serverName + " " + logName);
            return ResultBean.success("重启成功请稍后！！！");
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
        }
    }


    @Override
    public ResultBean updateService(MultipartFile file) {
        try {
            log.info("正在更新接口");
            String serverPath = System.getProperty("user.dir") + File.separator;
            String serverName = file.getOriginalFilename();
            String logName = serverName.substring(0, serverName.lastIndexOf(".")) + ".log";
            String shellName = serverName.substring(0, serverName.lastIndexOf(".")) + "-updateService.sh";
            Runtime.getRuntime().exec("cd " + serverPath);
            String str = "";
            File jar = new File(serverName);
            str += jar.exists() ? "jar包已存在 " : "";
            File shell = new File(shellName);
            str += shell.exists() ? "shell脚本已存在 " : "";
            File log = new File(logName);
            str += log.exists() ? "log日志已存在 " : "";
            if (ShpUtils.noNull(str)) {
                Runtime.getRuntime().exec("rm -rf " + serverName + " " + logName);
            }
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(serverName));
            ClassPathResource cpr = new ClassPathResource("shell/updateService.sh");
            InputStream in = cpr.getInputStream();
            FileUtils.copyInputStreamToFile(in, new File(shellName));
            Runtime.getRuntime().exec("chmod 777 " + shellName);
            String bash = "bash " + shellName + " " + StartedUpRunner.pid + " " + serverName + " " + logName;
            Runtime.getRuntime().exec(bash);
            if (ShpUtils.noNull(str)) {
                return ResultBean.success(str + "--" + bash + " ，更新成功！！！");
            }
        } catch (Exception e) {
            return ResultBean.error(e.getMessage());
        }
        return ResultBean.success();
    }

    @Override
    public ResultBean hotUpdate(MultipartFile file) {
        String aClass = CommonFileUtils.saveFile(file, null, "/usr/local/project", "class");
        return ResultBean.success("请执行：redefine " + aClass);
    }

    @Override
    public void generateCode(String tables, HttpServletResponse response) {
        try {
            byte[] data = generatorCode(tables.split(","));
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.write(data, response.getOutputStream());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            //查询表信息
            Map<String, String> table = queryTable(tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip);
        }
        if (MongoManager.isMongo()) {
            GenUtils.generatorMongoCode(tableNames, zip);
        }

        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public Map<String, String> queryTable(String tableName) {
        return generatorDao.queryTable(tableName);
    }

    public List<Map<String, String>> queryColumns(String tableName) {
        return generatorDao.queryColumns(tableName);
    }


}
