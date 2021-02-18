package com.shp.dev.network.common.util.activemq;

import com.shp.dev.network.common.bean.ResultBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateBy: Administrator
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2021/2/18 12:06
 * @PackageName: com.shp.dev.network.common.util.activemq
 * @ProjectName: network
 */
@RestController
@RequestMapping("/active")
@CrossOrigin
public class ActiveMQController {

    @Autowired//注入activemq
    private ActiveMQProducer producer;

    @RequestMapping(value = "/send", method = {RequestMethod.POST})
    @ApiOperation("发送消息到activeMQ")
    public ResultBean send(String topic, String message) {
        producer.sendMsg(topic,message);
        return ResultBean.success();
    }

}
