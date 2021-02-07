package com.shp.dev.network.common.util.listener;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 调用监听 监听某个文件夹里的文件和文件
 * @CreateTime: 2020-07-09 14:20
 * @PackageName: com.vimochina.aiphototaking.util
 * @ProjectName: aiphototaking
 */
public class FileMonitor {

    public static void main(String[] args) {
        FileMonitor m = new FileMonitor(1000);//每隔1秒监听一次
        m.monitor("D:\\aaa", new FileListener());//监听此路径下的文件变化
        m.start();//启动
    }

    FileAlterationMonitor monitor;

    public FileMonitor(long interval) {
        monitor = new FileAlterationMonitor(interval);
    }

    public void monitor(String path, FileAlterationListener listener) {
        FileAlterationObserver observer = new FileAlterationObserver(new File(path));
        monitor.addObserver(observer);
        observer.addListener(listener);
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 停止监听
     * @CreateTime: 2020-07-09 14:53
     * @param:
     * @return: void
     */
    public void stop()  {
        try {
            monitor.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 开启监听
     * @CreateTime: 2020-07-09 14:53
     * @param:
     * @return: void
     */
    public void start(){
        try {
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

