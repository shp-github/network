package com.shp.dev.network.common.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 解决服务器端tomcat报错
 * @CreateTime: 2020/9/12 13:15
 * @PackageName: com.shp.dev.network.common.config
 * @ProjectName: network
 */
@Component
public class PortalTomcatWebServerCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "{}[]|"));
    }
}
