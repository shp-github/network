package com.shp.dev.network.common.util.serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 序列化就是把对象序列化成二进制进行传输
 * TODO 反序列化就是把二进制数据反序列化成对象
 * @CreateTime: 2021/1/5 15:00
 * @PackageName: com.shp.dev.network.common.util.serializable
 * @ProjectName: network
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persion implements Serializable {

    //序列id
    private static final long serialVersionUID = 729196429762925859L;

    private String name;

    private Integer age;

    private String sex;


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //序列化到硬盘
        SerializePersion();

        //反序列化
        deserializePersion();

    }

    private static void SerializePersion() throws IOException {
        Persion persion = new Persion();
        persion.setAge(20);
        persion.setName("shp");
        persion.setSex("男");

        //ObjectOutputStream 对象输出流
        //把persion对象存储到e盘 完成对persion的序列化操作
        ObjectOutputStream oo = new ObjectOutputStream(
                new FileOutputStream(
                        new File("e:/persion.txt")
                ));

        oo.writeObject(persion);
        oo.close();
        System.out.println("序列化成功");


    }


    private static Persion deserializePersion() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(
                        new File("e:/persion.txt")
                )
        );

        Persion persion = (Persion) ois.readObject();
        System.out.println("反序列化成功");
        System.out.println(persion);
        return persion;

    }


}
