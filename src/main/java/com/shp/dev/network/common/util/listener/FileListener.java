package com.shp.dev.network.common.util.listener;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO
 * @CreateTime: 2020/9/4 15:15
 * @PackageName: com.shp.dev.network.common.util.listener
 * @ProjectName: network
 */
class FileListener implements FileAlterationListener {

    FileMonitor monitor = null;

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 启动时执行
     * @CreateTime: 2020-07-09 14:31
     * @param: observer
     * @return: void
     */
    @Override
    public void onStart(FileAlterationObserver observer) {
        //System.out.println("onStart");
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 创建目录时执行
     * @CreateTime: 2020-07-09 14:34
     * @param: directory
     * @return: void
     */
    @Override
    public void onDirectoryCreate(File directory) {
        System.out.println("onDirectoryCreate:" + directory.getName());
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 目录改变时执行
     * @CreateTime: 2020-07-09 14:34
     * @param: directory
     * @return: void
     */
    @Override
    public void onDirectoryChange(File directory) {
        System.out.println("onDirectoryChange:" + directory.getName());
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 目录删除时执行
     * @CreateTime: 2020-07-09 14:33
     * @param: directory
     * @return: void
     */
    @Override
    public void onDirectoryDelete(File directory) {
        System.out.println("onDirectoryDelete:" + directory.getName());
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 文件创建时执行
     * @CreateTime: 2020-07-09 14:32
     * @param: file
     * @return: void
     */
    @Override
    public void onFileCreate(File file) {
        System.out.println("onFileCreate:" + file.getName());
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 文件改变时执行
     * @CreateTime: 2020-07-09 14:32
     * @param: file
     * @return: void
     */
    @Override
    public void onFileChange(File file) {
        System.out.println("onFileChange : " + file.getName());
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 删除文件时执行
     * @CreateTime: 2020-07-09 14:31
     * @param: file
     * @return: void
     */
    @Override
    public void onFileDelete(File file) {
        System.out.println("onFileDelete :" + file.getName());
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 结束时执行
     * @CreateTime: 2020-07-09 14:31
     * @param: observer
     * @return: void
     */
    @Override
    public void onStop(FileAlterationObserver observer) {
        //System.out.println("onStop");
    }

}
