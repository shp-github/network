package com.shp.dev.network.write.service;

import com.shp.dev.network.common.bean.PageBean;
import com.shp.dev.network.common.bean.PageHelperBean;
import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.common.bean.Tablepar;
import com.shp.dev.network.common.util.IfromUtils;
import com.shp.dev.network.write.mapper.WriteMapper;
import com.shp.dev.network.write.model.SysWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @CreateBy: shp
 * @version：1.0
 * @Description: TODO 向页面输出网页信息业务层
 * @CreateTime: 2020/7/25 23:35
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WriteService {

    @Autowired
    private WriteMapper mapper;

    public void getWrite(HttpServletResponse res, SysWrite model) {

        //设置传输格式
        res.setContentType("text/html;charset=gb2312");
        PrintWriter p = null;

        try {
            p = res.getWriter();
            SysWrite write = mapper.getWrite(model);
            if (write != null && write.getBody() != null) {
                //如果有值，则向页面输出信息
                p.print(write.getBody());
            }
            if (model.getUri() != null && !model.getUri().equals("")) {
                //输出地址信息
                p.print(IfromUtils.toIfrom(model.getUri()));
            }
            if (model == null) {
                //错误页面
                p.print(mapper.getError(model).getBody());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            p.close();
        }
    }

    public ResultBean getWriteList(SysWrite model, Tablepar tablepar) {
        PageHelperBean.startPage(tablepar);
        return ResultBean.success("获取列表成功！！！", new PageBean(mapper.getWriteList(model)));
    }

    public ResultBean delWrite(SysWrite model) {
        return ResultBean.success(mapper.delWrite(model));
    }

}
