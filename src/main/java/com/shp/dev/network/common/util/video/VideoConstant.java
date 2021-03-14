package com.shp.dev.network.common.util.video;

import com.shp.dev.network.common.util.jdbc.JDBCUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Map;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO 同步video的常量信息
 * @CreateTime: 2021/3/12 15:41
 * @PackageName: com.shp.dev.network.common.util.video
 * @ProjectName: network
 */

//@Component
//@EnableScheduling
@Slf4j
public class VideoConstant {

    static List<Map> tbVideoList = JDBCUtils.queryList("SELECT * from TB_VIDEO WHERE is_del = 1 ");

    @Scheduled(initialDelay = 0, fixedRate = 60 * 1000)
    private void getSource() {
        log.info("同步video常用信息");
        tbVideoList = JDBCUtils.queryList("SELECT * from TB_VIDEO WHERE is_del = 1 ");
    }


}
