package com.shp.dev.network.common.util.fastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/9/18 21:13
 * @PackageName: com.shp.dev.network.common.util.fastdfs
 * @ProjectName: network
 */
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class ComponetImport {
    // 导入依赖组件
}
