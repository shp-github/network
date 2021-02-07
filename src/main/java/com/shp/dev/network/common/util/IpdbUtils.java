package com.shp.dev.network.common.util;

import net.ipip.ipdb.City;
import net.ipip.ipdb.CityInfo;

import java.util.Arrays;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/9/29 21:21
 * @PackageName: com.shp.dev.network.common.util
 * @ProjectName: network
 */
public class IpdbUtils {
    public static void main(String[] args) {

        try {
            // City类可用于IPDB格式的IPv4免费库，IPv4与IPv6的每周高级版、每日标准版、每日高级版、每日专业版、每日旗舰版
            City db = new City("D:\\ipiptest.ipdb");

            // db.find(address, language) 返回索引数组
            System.out.println(Arrays.toString(db.find("1.1.1.1", "CN")));

            // db.findInfo(address, language) 返回 CityInfo 对象
            CityInfo info = db.findInfo("118.28.1.1", "CN");
            System.out.println(info);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
