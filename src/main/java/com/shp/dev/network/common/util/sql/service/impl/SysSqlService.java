package com.shp.dev.network.common.util.sql.service.impl;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.util.ShpUtils;
import com.shp.dev.network.common.util.fastdfs.FastDFSClient;
import com.shp.dev.network.common.util.jdbc.JDBCUtils;
import com.shp.dev.network.common.util.redis.RedisConfig;
import com.shp.dev.network.common.util.sql.mapper.SysSqlMapper;
import com.shp.dev.network.common.util.sql.model.SysSql;
import com.shp.dev.network.common.util.sql.service.ISysSqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/9/25 15:18
 * @PackageName: com.shp.dev.network8.0.sql.service
 * @ProjectName: network
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysSqlService implements ISysSqlService {

    @Autowired
    private SysSqlMapper mapper;

    @Autowired
    private RedisConfig redisConfig;

    private final static String host = "47.92.213.36";
    private final static String port = "6379";

    public ResultBean select(SysSql parm) {
        String sql = null;//最终执行的sql语句
        if (ShpUtils.isNull(parm.getSqlText())) {
            try {//获取sql语句
                sql = (String) redisConfig.getRedisTemplate(0).opsForValue().get(parm.getSqlName());
                if (ShpUtils.isNull(sql)) {
                    SysSql sysSql = mapper.queryBySqlName(parm);
                    String sqlText = sysSql.getSqlText();
                    redisConfig.getRedisTemplate(0).opsForValue().set(parm.getSqlName(), sqlText);
                    sql = sqlText;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                sql = null;
            }
        }
        if (ShpUtils.noNull(parm.getSqlText())) {
            sql = parm.getSqlText();
        }
        if (ShpUtils.isNull(sql)) {
            return ResultBean.error("找不到sql语句！");
        }
        try {
            if (ShpUtils.noNull(parm.getObjects()) && parm.getObjects().length > 0 && ShpUtils.noNull(parm.getLimit())) {
                return ResultBean.success("ok", JDBCUtils.queryList(sql + " limit " + parm.getLimit(), parm.getObjects()));
            }
            if (ShpUtils.noNull(parm.getParms()) && ShpUtils.noNull(parm.getLimit())) {
                return ResultBean.success("ok", JDBCUtils.queryList(sql + " limit " + parm.getLimit(), parm.getParms().split(",")));
            }

            if (ShpUtils.noNull(parm.getObjects()) && parm.getObjects().length > 0 && !parm.getObjects()[0].equals("null")) {
                return ResultBean.success("ok", JDBCUtils.queryList(sql, parm.getObjects()));
            }
            if (ShpUtils.noNull(parm.getParms())) {
                return ResultBean.success("ok", JDBCUtils.queryList(sql, parm.getParms().split(",")));
            }
            if (ShpUtils.noNull(parm.getLimit())) {
                return ResultBean.success("ok", JDBCUtils.queryList(sql + " limit " + parm.getLimit()));
            }

            List list = JDBCUtils.queryList(sql);

            return ResultBean.success("ok", list);
        } catch (Exception e) {
            return ResultBean.error("请检查参数,parms请用逗号隔开！");
        }
    }

    public ResultBean update(SysSql parm) {
        String sql = null;//最终执行的sql语句
        if (ShpUtils.isNull(parm.getSqlText())) {
            try {//获取sql语句
                sql = (String) redisConfig.getRedisTemplate(0).opsForValue().get(parm.getSqlName());
                if (ShpUtils.isNull(sql)) {
                    SysSql sysSql = mapper.queryBySqlName(parm);
                    String sqlText = sysSql.getSqlText();
                    redisConfig.getRedisTemplate(0).opsForValue().set(parm.getSqlName(), sqlText);
                    sql = sqlText;
                }
            } catch (Exception e) {
                sql = null;
            }
        }
        if (ShpUtils.noNull(parm.getSqlText())) {
            sql = parm.getSqlText();
        }
        if (ShpUtils.isNull(sql)) {
            return ResultBean.error("找不到sql语句！");
        }
        try {
            if (ShpUtils.noNull(parm.getObjects()) && parm.getObjects().length > 0 && JDBCUtils.sqlFactory(sql, parm.getObjects()) > 0) {
                return ResultBean.success("ok");
            }
            if (ShpUtils.noNull(parm.getParms()) && JDBCUtils.sqlFactory(sql, parm.getParms().split(",")) > 0) {
                return ResultBean.success("ok");
            }
            if (JDBCUtils.sqlFactory(sql) > 0) {
                return ResultBean.success("ok");
            }
        } catch (Exception e) {
            return ResultBean.error("请检查参数，parms请用逗号隔开！");
        }
        return ResultBean.error("操作失败，请检查代码！");
    }

    @Autowired
    private FastDFSClient fastDFSClient;

    public ResultBean uploadFile(MultipartFile file) {
        String s = fastDFSClient.uploadFile(file);
        if (s != null) {
            return ResultBean.success(s);
        } else {
            return ResultBean.error();
        }
    }
}
