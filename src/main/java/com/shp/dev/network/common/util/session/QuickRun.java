package com.shp.dev.network.common.util.session;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO session共享
 * @CreateTime: 2021/1/16 16:36
 * @PackageName: com.shp.dev.network.common.util.session
 * @ProjectName: network
 */
@RestController
@RequestMapping(value = "/admin/v1")
public class QuickRun {
    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public Map<String, Object> firstResp (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("map"));
        return map;
    }
}