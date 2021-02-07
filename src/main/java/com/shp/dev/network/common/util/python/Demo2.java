package com.shp.dev.network.common.util.python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/11/19 15:43
 * @PackageName: com.shp.dev.network.common.util.python
 * @ProjectName: network
 */
public class Demo2 {

    public static void main(String[] args) {

        int a = 18;
        int b = 23;
        try {

            String templatePath = Class.class.getResource("/python").getPath()+"/demo2.py";
            templatePath=templatePath.substring(1);

            String[] args1 = new String[]{"python", templatePath, String.valueOf(a), String.valueOf(b)};
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
