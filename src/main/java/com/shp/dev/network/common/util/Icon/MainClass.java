package com.shp.dev.network.common.util.Icon;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/10/31 23:54
 * @PackageName: com.shp.dev.network.common.util.Icon
 * @ProjectName: network
 */
import javax.swing.*;
import java.awt.*;

public class MainClass {
    public static void main(String args[]){
        JFrame frame=new JFrame("Java数据统计图");
        frame.setLayout(new GridLayout(2,2,10,10));
        frame.add(new BarChart().getChartPanel());           //添加柱形图
        //frame.add(new BarChart1().getChartPanel());          //添加柱形图的另一种效果
        frame.add(new PieChart().getChartPanel());           //添加饼状图
        frame.add(new TimeSeriesChart().getChartPanel());    //添加折线图
        frame.setBounds(50, 50, 800, 600);
        frame.setVisible(true);
    }
}
