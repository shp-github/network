package com.shp.dev.network.common.util.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO websocket 配置文件
 * @CreateTime: 2020/9/2 21:50
 * @PackageName: com.vimochina.vimo.util.socket
 * @ProjectName: wisdomeyeapi0114
 */

@Configuration
public class WebSocketConfig {
    /**
     * 给spring容器注入这个ServerEndpointExporter对象
     * 相当于xml：
     * <beans>
     * <bean id="serverEndpointExporter" class="org.springframework.web.socket.server.standard.ServerEndpointExporter"/>
     * </beans>
     * <p>
     * 检测所有带有@serverEndpoint注解的bean并注册他们。
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        System.out.println("注入WebSocket");
        return new ServerEndpointExporter();
    }
}