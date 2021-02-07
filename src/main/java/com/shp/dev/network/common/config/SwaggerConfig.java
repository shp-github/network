package com.shp.dev.network.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: shp
 * @version: 1.0
 * @Description TODO swagger的配置
 * @CreateTime 2020/6/21 21:12
 * @PACKAGE_NAME: com.shp.dev.network.common.bean
 * @PROJECT_NAME: network
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shp.dev.network"))//扫描控制层的包
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("Api文档")//标题
                        .description("description")//详细内容
                        .version("1.0")//版本号
                        .contact(new Contact("shp","http://www.shp.bio/","2948299576@qq.com"))//作者--地址--邮箱
                        .licenseUrl("http://www.shp.bio/")//网址
                        .build());
    }
}

