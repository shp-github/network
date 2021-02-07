package com.shp.dev.network.menu.service;

import com.shp.dev.network.common.bean.ResultBean;
import com.shp.dev.network.menu.mapper.SysMenuMapper;
import com.shp.dev.network.menu.model.SysMenuResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 按钮业务层
 * @CreateTime: 2020-07-06 16:06
 * @PackageName: com.shp.dev.network.menu.service
 * @ProjectName: network
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysMenuService {


    @Autowired
    private SysMenuMapper mapper;

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 展示所有按钮
     * @CreateTime: 2020-07-06 18:16
     * @param:
     * @return: com.shp.dev.network.common.bean.ResultModel
     */
    public ResultBean queryAllMenu() {

        List<SysMenuResult> allMenu = mapper.findAllMenu();
        List<SysMenuResult> menuList=new ArrayList<>();
        SysMenuResult sysMenuResult;

        for (SysMenuResult menu : allMenu) {
            sysMenuResult = new SysMenuResult();
            sysMenuResult.setMenuid(menu.getMenuid());
            sysMenuResult.setIcon(menu.getIcon());
            sysMenuResult.setMenuname(menu.getMenuname());
            sysMenuResult.setMenus(mapper.findMenuByPid(menu.getMenuid()));
            menuList.add(sysMenuResult);
        }
        return  ResultBean.success("菜单获取成功！！！", menuList);
    }
}
