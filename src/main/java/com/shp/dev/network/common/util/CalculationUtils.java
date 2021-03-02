package com.shp.dev.network.common.util;

import net.sf.json.JSONArray;

import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 计算工具类
 * @CreateTime: 2020/9/11 15:19
 * @PackageName: com.vimochina.vimo.util.ai
 * @ProjectName: wisdomeyeapi0114
 */
public class CalculationUtils {

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 传入int数组,返回最大和最小值
     * @CreateTime: 2020/9/11 15:23
     * @param: arr
     * @return: int[] 第一个角标是最大,第二个角标是最小。
     */
    public static int[] getxn(List<Integer> a) {
        return getxn(forList(a));
    }

    public static int[] getxn(int[] arr) {
        int[] result = new int[2];
        try {
            int max = arr[1];//将数组的第一个元素赋给max
            int min = arr[0];//将数组的第一个元素赋给min
            for (int i = 1; i < arr.length; i++) {//从数组的第二个元素开始赋值,依次比较
                if (arr[i] > max) {//如果arr[i]大于最大值,就将arr[i]赋给最大值
                    max = arr[i];
                }
                if (arr[i] < min) {//如果arr[i]小于最小值,就将arr[i]赋给最小值
                    min = arr[i];
                }
            }
            result[0] = min;
            result[1] = max;
        } catch (Exception e) {
            result[0] = 0;
            result[1] = 0;
        }
        return result;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 获取最小值
     * @CreateTime: 2020/9/11 15:26
     * @param: arr
     * @return: int
     */
    public static int getMin(JSONArray arr, double d) {
        int min = 0;
        try {
            min = (int) (Integer.valueOf(arr.getJSONArray(0).get(1).toString()) / d);//将数组的第一个元素赋给min
            for (int i = 1; i < arr.size(); i++) {//从数组的第二个元素开始赋值,依次比较
                if (Integer.valueOf(arr.getJSONArray(i).get(1).toString()) / d < min) {//如果arr[i]小于最小值,就将arr[i]赋给最小值
                    min = (int) (Integer.valueOf(arr.getJSONArray(i).get(1).toString()) / d);
                }
            }
        } catch (Exception e) {
            System.out.println("计算最小值失败" + e.getMessage());
        }
        return min;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 获取最小值
     * @CreateTime: 2020/9/11 15:26
     * @param: arr
     * @return: int
     */
    public static int getMin(int[] arr) {
        int min = 0;
        try {
            min = arr[0];//将数组的第一个元素赋给min
            for (int i = 1; i < arr.length; i++) {//从数组的第二个元素开始赋值,依次比较
                if (arr[i] < min) {//如果arr[i]小于最小值,就将arr[i]赋给最小值
                    min = arr[i];
                }
            }
        } catch (Exception e) {
        }
        return min;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 获取最大值
     * @CreateTime: 2020/9/11 15:26
     * @param: arr
     * @return: int
     */
    public static int getMax(int[] arr) {
        int max = 0;
        try {
            max = arr[0];//将数组的第一个元素赋给mi
            for (int i = 1; i < arr.length; i++) {//从数组的第二个元素开始赋值,依次比较
                if (arr[i] > max) {//如果arr[i]大于最大值,就将arr[i]赋给最大值
                    max = arr[i];
                }
            }
            return max;
        } catch (Exception e) {
        }
        return max;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 获取数组中所有数的和值
     * @CreateTime: 2020/9/14 9:29
     * @param: arr
     * @return: int
     */
    public static int getSum(int[] arr) {
        int sum = 0;
        try {
            for (int i : arr) {
                sum += i;
            }
        } catch (Exception e) {
        }
        return sum;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 如果总数是奇数取中间值,如果偶数则取两个数的平均值
     * @CreateTime: 2020/9/14 9:36
     * @param: a
     * @return: int
     */
    public static int exec(List<Integer> a) {
        return exec(forList(a));
    }

    public static int exec(int a[]) {
        if (a == null) {
            return 0;
        }
        int result = 0;
        try {
            int N = a.length;
            // 取中间
            if (N % 2 == 1) {
                for (int i = 0; i <= N / 2; i++) {
                    for (int j = 0; j < (N - 1) - i; j++) {
                        if (a[j] > a[j + 1]) {
                            a[j] = a[j + 1] + 0 * (a[j + 1] = a[j]);
                        }
                    }
                }
                result = a[N / 2];
            }// 取中间两数平均值
            else {
                for (int i = 0; i <= N / 2; i++) {
                    for (int j = 0; j < (N - 1) - i; j++) {
                        if (a[j] > a[j + 1]) {
                            a[j] = a[j + 1] + 0 * (a[j + 1] = a[j]);
                        }
                    }
                }
                result = (a[N / 2 - 1] + a[N / 2]) / 2;
            }
        } catch (Exception e) {
            return 0;
        }
        return result;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 遍历集合为数组
     * @CreateTime: 2020/10/16 16:29
     * @param: a
     * @return: int[]
     */
    public static int[] forList(List<Integer> a) {
        int[] arr = new int[a.size()];
        for (int i = 0; i < a.size(); i++) {
            arr[i] = a.get(i);
        }
        return arr;
    }

}
