package com.shp.dev.network.common.util.exprot.api;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.shp.dev.network.common.util.exprot.Bean.RichObject;
import com.shp.dev.network.common.util.exprot.core.Freemarker;
import com.shp.dev.network.common.util.exprot.core.RichHtmlHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * Created by wesley on 2017-05-10.
 * 对外提供调用的接口
 */
@Slf4j
public class WordGeneratorWithFreemarker {

    /**
     * 创建doc文件
     *
     * @param templatePath 模板所在路径 xxx/xxx/template
     * @param templateName 模板名字 xxx.ftl
     * @param dataMap      数据集合
     * @param outPath      输出文件路径  xxx/xxx/xxx.doc
     */
    public static void createDoc(String templatePath, String templateName, Map<String, Object> dataMap, String outPath) throws Exception {
        log.debug("WordGeneratorWithFreemarker createDoc()");
        Freemarker.fprint(templatePath, templateName, dataMap, outPath);
    }

    /**
     * 创建富文本Html处理器，主要处理图片及编码
     *
     * @param richObject 需要的参数
     * @return
     */
    public static RichHtmlHandler createRichHtmlHandler(RichObject richObject) throws Exception {
        log.debug("WordGeneratorWithFreemarker createRichHtmlHandler()");
        RichHtmlHandler richHtmlHandler = new RichHtmlHandler(richObject);
        return richHtmlHandler;
    }

    /**
     * 获取图片的64位字符串
     *
     * @param richHtmlHandlerList
     * @return
     */
    public static String getImagesBase64String(List<RichHtmlHandler> richHtmlHandlerList) {
        log.debug("WordGeneratorWithFreemarker getImagesBase64String()");
        String imagesBase64String = "";
        for (RichHtmlHandler richHtmlHandler : richHtmlHandlerList) {
            if (!CollectionUtils.isEmpty(richHtmlHandler.getDocBase64BlockResults())) {
                for (String item : richHtmlHandler.getDocBase64BlockResults()) {
                    imagesBase64String += item + "\n";
                }
            }
        }
        log.debug("WordGeneratorWithFreemarker getImagesBase64String() result:" + imagesBase64String);
        return imagesBase64String;
    }

    /**
     * 获取图片在xml中的端路径
     *
     * @param richHtmlHandlerList
     * @return
     */
    public static String getXmlImgHref(List<RichHtmlHandler> richHtmlHandlerList) {
        log.debug("WordGeneratorWithFreemarker getXmlImgHref()");
        String xmlImgHref = "";
        for (RichHtmlHandler richHtmlHandler : richHtmlHandlerList) {
            if (!CollectionUtils.isEmpty(richHtmlHandler.getXmlImgRefs())) {
                for (String item : richHtmlHandler.getXmlImgRefs()) {
                    xmlImgHref += item + "\n";
                }
            }
        }
        log.debug("WordGeneratorWithFreemarker getXmlImgHref() result:" + xmlImgHref);
        return xmlImgHref;
    }
}
