package com.shp.dev.network.common.util.xml;

import com.shp.dev.network.common.util.ShpUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO dom解析xml文件
 * @CreateTime: 2020-07-13 11:28
 * @PackageName: com.vimochina.vimo.util.file
 * @ProjectName: wisdomeyeapi0114
 */

public class DomReadXmlUtils<T> {

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 读取xml文件
     * @CreateTime: 2020/7/31 15:39
     * @param: path xml的文件地址
     * @param: tagName 读取的节点名
     * @param: clz 读取的类型
     * @return: java.util.List<T> T:读取的类型
     */
    public List<T> inputXml(String path, String tagName, Class<?> clz) {
        try {
            //1.创建DocumentBuilderFactory对象
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Document document = builder.parse(path);
            Document document = builder.parse("file:///" + path);//解决java.net.MalformedURLException: unknown protocol: c问题
            NodeList sList = document.getElementsByTagName(tagName);
            return node(sList, clz);
        } catch (ParserConfigurationException e) {
            return null;
        } catch (SAXException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 遍历nodeList 返回 list
     * @CreateTime: 2020-07-13 16:18
     * @param: list
     * @return: java.util.List<com.vimochina.vimo.util.file.DomReadXmlUtils.AiXmlModel>
     */
    public List<T> node(NodeList list, Class<?> clz) {
        List<T> objectList = new ArrayList<T>();
        Object object = new Object();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    map.put(childNodes.item(j).getNodeName(), childNodes.item(j).getFirstChild().getNodeValue());
                }
                try {
                    object = new ShpUtils().map2Obj(map, clz);
                } catch (Exception e) {
                    return null;
                }
            }
            objectList.add((T) object);
        }
        return objectList;
    }

}


