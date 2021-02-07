package com.shp.dev.network.common.util.xml;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO sax解析xml
 * @CreateTime: 2020/7/31 11:43
 * @PackageName: com.vimochina.vimo.util.file
 * @ProjectName: wisdomeyeapi0114
 */
public class SaxReadXmlUtils<T> {

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 读取xml文件的值
     * @CreateTime: 2020/8/1 13:54
     * @param: filePath xml文件地址
     * @param: nodeName 节点名
     * @param: clz 类型
     * @return: java.util.List<T> 对应实体类的类型
     */
    public List<T> inputXml(String filePath, String nodeName, Class<?> clz) {

        try {
            //创建一个SAX解析器工厂对象;
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            //通过工厂对象创建SAX解析器
            SAXParser saxParser = saxParserFactory.newSAXParser();
            //创建一个数据处理器(自己实现)
            PersonHandle personHandle = new PersonHandle();
            //反射获取类中的属性为数组
            List<String> list = new ArrayList<>();
            Field[] fields = clz.getDeclaredFields(); // 返回属性为public的字段
            for (Field field : fields) {
                list.add(field.toString().substring(field.toString().lastIndexOf(".") + 1));
            }
            String[] strings = list.toArray(new String[list.size()]);
            personHandle.setPropertis(strings);
            personHandle.setNodeName(nodeName);
            //开始解析
            File file = new File(filePath);
            saxParser.parse(file, personHandle);
            //获取处理类的节点数据集合
            List<Map> mapList = personHandle.getList();
            List<T> tArrayList = new ArrayList<>();
            for (Map map : mapList) {
                //把map转为对象然后添加到集合中
                tArrayList.add(JSON.parseObject(JSON.toJSONString(map), (Type) clz));
            }
            return tArrayList;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


/**
 * @CreateBy: shp
 * @version：1.0
 * @Description: TODO sex解析的处理类
 * @CreateTime: 2020/8/1 13:54
 * @return:
 */
@Data
class PersonHandle extends DefaultHandler {

    private List list = new ArrayList();//存储所有节点的值
    private String tag;  //用来存储当前解析的标签名字
    private Map map = new HashMap(); //存储单个节点的值
    private String nodeName; //节点名
    String[] propertis;//节点中的属性名

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 开始解析文档时调用,只会执行一次
     * @CreateTime: 2020/8/1 13:53
     * @param:
     * @return: void
     */
    @Override
    public void startDocument() {
        try {
            super.startDocument();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        //System.out.println("开始解析文档.....");
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 结束解析文档时调用
     * @CreateTime: 2020/8/1 13:53
     * @param:
     * @return: void
     */
    @Override
    public void endDocument() {
        try {
            super.endDocument();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        //System.out.println("结束解析文档.....");
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 每一个标签开始时调用
     * @CreateTime: 2020/8/1 13:53
     * @param: uri
     * @param: localName
     * @param: qName
     * @param: attributes
     * @return: void
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            super.startElement(uri, localName, qName, attributes);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        //获取属性值: attributes.getValue("name")
        if (nodeName.equals(qName)) {
            map = new HashMap();
        }
        tag = qName;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 每一个标签结束时调用
     * @CreateTime: 2020/8/1 13:48
     * @param: uri
     * @param: localName
     * @param: qName
     * @return: void
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        try {
            super.endElement(uri, localName, qName);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        if (nodeName.equals(qName)) {
            list.add(map);
        }
        tag = null;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 当解析到标签中的内容的时候调用(换行也是文本内容)
     * @CreateTime: 2020/8/1 13:45
     * @param: ch 字符数组
     * @param: start 开始标记
     * @param: length 结束标记
     * @return: void
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        try {
            super.characters(ch, start, length);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        if (tag != null) {//把xml标签名和实体类中的属性对应的值拿出来复制个节点
            for (String properti : propertis) {
                if (properti.equals(tag)) {
                    map.put(properti, new String(ch, start, length));
                }
            }
        }
    }
}
