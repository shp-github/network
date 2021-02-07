package com.shp.dev.network.common.util.python;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/11/19 15:40
 * @PackageName: com.shp.dev.network.common.util.python
 * @ProjectName: network
 */
public class Demo1 {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Process proc;
        try {

            String templatePath = Class.class.getResource("/python").getPath()+"/demo1.py";
            templatePath=templatePath.substring(1);

            proc = Runtime.getRuntime().exec("python "+templatePath);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
