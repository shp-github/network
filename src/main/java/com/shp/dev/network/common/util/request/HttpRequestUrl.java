package com.shp.dev.network.common.util.request;

import net.sf.json.JSONArray;
import org.json.JSONObject;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 发送请求工具类
 * @CreateTime: 2020/8/5 11:41
 * @PackageName: com.vimochina.vimo.util.file
 * @ProjectName: wisdomeyeapi0114
 */
public class HttpRequestUrl {


    public static void main(String[] args) {

        String str="[{\"creationTime\":1609686993767,\"modificationTime\":1609686993767,\"uniqueKey\":\"http://api.assistant.miui.com/urlparser/guide\",\"contentJson\":{\"styleActivity\":\"1\",\"appName\":\"我的收藏\",\"title\":\"我的收藏使用说明\",\"favourite\":0,\"category\":\"article\",\"url\":\"http://api.assistant.miui.com/urlparser/guide\"},\"eTag\":\"131561874175101696\",\"id\":\"AdNm09gxCwE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1609353048920,\"modificationTime\":1610527390245,\"uniqueKey\":\"http://h5.m.taobao.com/awp/core/detail.htm?fpChannel=15&id=623874813132&scm=1007.12144.159502.11401162_0_0&pvid=d20363dd-13cb-4ea4-b7b3-0e14994c6aec&spm=a217e.xzsy\",\"contentJson\":{\"thumbnail\":\"http%3A%2F%2Fgd3.alicdn.com%2Fimgextra%2Fi4%2F393644186%2FO1CN01pI5pas1gnEXno8gYz_%21%21393644186.jpg,\",\"appName\":\"淘宝\",\"extra\":\"{\\\"price\\\":\\\"19.80\\\"}\",\"componentName\":\"com.taobao.taobao/com.taobao.android.detail.wrapper.activity.DetailActivity\",\"title\":\"电弧打火机点烟器电子制作套件diy散件点火器逆变升压高压发生器\",\"category\":\"shopping\",\"url\":\"http://h5.m.taobao.com/awp/core/detail.htm?fpChannel=15&id=623874813132&scm=1007.12144.159502.11401162_0_0&pvid=d20363dd-13cb-4ea4-b7b3-0e14994c6aec&spm=a217e.xzsy\",\"originalUri\":\"http://item.taobao.com/item.htm?id=623874813132&scm=1007.30375.178488.0&spm=a2141.14991849.crossshop.3&pvid=109c33a4-b5f7-4f6b-a97e-4ac9ac742977&utparam=%7B%22x_object_type%22%3A%22item%22%2C%22guess_buckets%22%3A%226595_11513_11609_12893_13440_17348_19178_21337_21230_20666%22%2C%22x_object_id%22%3A%22623874813132%22%7D\"},\"eTag\":\"132002483963758080\",\"id\":\"AdLHlxe5BwE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610936985539,\"modificationTime\":1610936985539,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRprHAQUBlUdUyVGTV8LRGtMR1dGFxBFC1pXUwkEAEAdQFkJBVITBBMHUxNETEdOWmVlC2F7cHwoWztOVnZnAWU9S1JBVCx7VxkyFwZUGlsXBBICVCtbFAMRAVUcUhAFIgdUGlgTAhUOUBw1EAMTBlUZXRUHE2lXGloXAxUCUhxfJQATBFQaWSVUfAdUGloUChIDXRJrFwMTBVQcXhIFFjdVHFsUCxsOVhlYFAYbN1UTXCVeSkIBTg5dARMEVCtrJQEiN2UbaxYyTGlUSFtHVRpUUHUBTwIWDwFfH3sHEgFcHVMTCyIFVBpZFw%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F22944%2F5%2F2%2F131062%2F5c064360Ea49e02fb%2Fd1f6b1281c11efe8.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"3299.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"攀升 P15 升i3 10100四核/GTX1650 4GD6独显/240G SSD/高频8G吃鸡游戏台式DIY组装电脑主机京东游戏UPC\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRprHAQUBlUdUyVGTV8LRGtMR1dGFxBFC1pXUwkEAEAdQFkJBVITBBMHUxNETEdOWmVlC2F7cHwoWztOVnZnAWU9S1JBVCx7VxkyFwZUGlsXBBICVCtbFAMRAVUcUhAFIgdUGlgTAhUOUBw1EAMTBlUZXRUHE2lXGloXAxUCUhxfJQATBFQaWSVUfAdUGloUChIDXRJrFwMTBVQcXhIFFjdVHFsUCxsOVhlYFAYbN1UTXCVeSkIBTg5dARMEVCtrJQEiN2UbaxYyTGlUSFtHVRpUUHUBTwIWDwFfH3sHEgFcHVMTCyIFVBpZFw%3D%3D\"},\"eTag\":\"132217229861258496\",\"id\":\"AdW63rsRBQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610936985617,\"modificationTime\":1610936985617,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRNTFwYbBlATXiUEEgJQHF4TARsPVysfSlpMWGVCHlBDUAxLBQNQVk4YDk5ER1xOGVMbXhAFFwFWElMXHUtCCUZrE0NhWAhQOmNgFk8QZjhhQkFxImMZQw4eN1AaWhQCEAFVHlolAhMGVh1bEgsXAGUbWhQBFAdSEl4SbBcGVBpbFwQSAlR1WRQDEAZSHlwSBiIFVBhaFAAiRjscXhwBFw9lGVoUABMAUBxcETISAFUaUhwLFQNcHl0RMhIPUisHTUdGUgBTWBQBEzdlK1glMiIHZRhrS2wTVFUeWxEAQWkPQVsRWklAFHVeFQQQAVYcWyUAEwZXGQ%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F155933%2F15%2F5248%2F161662%2F5ff97f5cE27617acf%2Fc2285e613982b275.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"2898.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"鑫锐 英特尔i5 10400F/RTX2060升GTX1080高配游戏台式吃鸡电脑主机/DIY组装机 配置一：i5 10400F/GTX1050Ti 主机+24英寸曲面显示器\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRNTFwYbBlATXiUEEgJQHF4TARsPVysfSlpMWGVCHlBDUAxLBQNQVk4YDk5ER1xOGVMbXhAFFwFWElMXHUtCCUZrE0NhWAhQOmNgFk8QZjhhQkFxImMZQw4eN1AaWhQCEAFVHlolAhMGVh1bEgsXAGUbWhQBFAdSEl4SbBcGVBpbFwQSAlR1WRQDEAZSHlwSBiIFVBhaFAAiRjscXhwBFw9lGVoUABMAUBxcETISAFUaUhwLFQNcHl0RMhIPUisHTUdGUgBTWBQBEzdlK1glMiIHZRhrS2wTVFUeWxEAQWkPQVsRWklAFHVeFQQQAVYcWyUAEwZXGQ%3D%3D\"},\"eTag\":\"132217229902152960\",\"id\":\"AdW63r2BBQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610936985628,\"modificationTime\":1610936985628,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtbFAIVB10YXxMyFgZUHV4QABcPUh1rUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sfWhQEFwJXHlMSBA1eEEcGJVpzWDdEBXBhcGIVUzxMV3oEBkISVGIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XBUEGwFXK1kUAxAGUh5cEgYiB1IbWhwLGgJTH1sVCyIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYbCxxSRwY7QQEVBRMEVEg1EAIbAlwdWBIyEAZUGVk%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F159001%2F15%2F140%2F192629%2F5fe9da72E1a72423a%2F28f584bfa8e67ecc.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"3799.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"名睿高配水冷i5 10400/RTX2060/16G内存/吃鸡游戏台式电脑主机DIY组装机全套 配置一\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtbFAIVB10YXxMyFgZUHV4QABcPUh1rUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sfWhQEFwJXHlMSBA1eEEcGJVpzWDdEBXBhcGIVUzxMV3oEBkISVGIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XBUEGwFXK1kUAxAGUh5cEgYiB1IbWhwLGgJTH1sVCyIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYbCxxSRwY7QQEVBRMEVEg1EAIbAlwdWBIyEAZUGVk%3D\"},\"eTag\":\"132217229907922944\",\"id\":\"AdW63r3ZEAE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610936985633,\"modificationTime\":1610936985633,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtaFQsVDlYYWBIyEQFQGF0TAhcFVRxrUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sYXRABFAFVHlkVBQ1eEEcGJWRvTg54Hhd6d08rRDpjSU4PPEdZVVQeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XBMHEgdVK1kUAxAGUh5cEgYiB1IbWhwKFA9cGF0WAyIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYbCUIKQQM7QQEVURFTDUM1EAESAVAYXxIyEAZUGVk%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F156775%2F6%2F5588%2F170600%2F5ffcfca8Ed8f54e12%2F181fdf64a23c3bf7.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"3198.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"磐镭GTX1080显卡8G  DDR5X 吃鸡游戏DIY组装机独立显卡公版 超2060S\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtaFQsVDlYYWBIyEQFQGF0TAhcFVRxrUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sYXRABFAFVHlkVBQ1eEEcGJWRvTg54Hhd6d08rRDpjSU4PPEdZVVQeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XBMHEgdVK1kUAxAGUh5cEgYiB1IbWhwKFA9cGF0WAyIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYbCUIKQQM7QQEVURFTDUM1EAESAVAYXxIyEAZUGVk%3D\"},\"eTag\":\"132217229910542592\",\"id\":\"AdW63r4BCQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610936985638,\"modificationTime\":1610936985638,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRhZHAoSBV0bUxwyFARXHl8cARMGUBNrUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sdWBcHFg5WGloQCg1eEEcGJXFbHSJvXExacgYBbCZgB3BXKB9FFFQeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQHGgVcGFklABMGVxpcEAUVA2UbXBUDGw9QGF4cCxEFZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBZRRgMATDVPWBIOEU4MEWwXB10eUxIAEDdXGloXAA%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F157110%2F16%2F2255%2F178837%2F5ff7b498E21706d38%2F8ca6d6ab754c55d9.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"2849.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"Intel/英特尔 I7 10700K/KF 盒装 搭 微星 B460 Z490 CPU主板套装 微星 B460M MORTAR I7 10700F\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRhZHAoSBV0bUxwyFARXHl8cARMGUBNrUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sdWBcHFg5WGloQCg1eEEcGJXFbHSJvXExacgYBbCZgB3BXKB9FFFQeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQHGgVcGFklABMGVxpcEAUVA2UbXBUDGw9QGF4cCxEFZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBZRRgMATDVPWBIOEU4MEWwXB10eUxIAEDdXGloXAA%3D%3D\"},\"eTag\":\"132217229913164032\",\"id\":\"AdW63r4pCQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610936985643,\"modificationTime\":1610989549769,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRhbFAsTA1cTWRYyEQ5RHlocChoEVBprUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sYUhEHEw5dE1gUAw1eEEcGJVsTXRQfCx1bcQYJaVNnW0RGMFkwYGIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XBALFANUK1kUAxAGUh5cEgYiB1IbWhwKGwRRHl8VCyIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYYCEFXFQ87QQEVURsOXE01EAESBlQZXBUyEAZUGVk%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F160523%2F17%2F3211%2F173192%2F6004e0bfE9264ab9f%2Fca2afe0d0033f64c.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"295.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"金士顿（Kingston） A2000系列M.2接口NVMe协议 固态硬盘SSD A2000无散热片版 250G\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRhbFAsTA1cTWRYyEQ5RHlocChoEVBprUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sYUhEHEw5dE1gUAw1eEEcGJVsTXRQfCx1bcQYJaVNnW0RGMFkwYGIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XBALFANUK1kUAxAGUh5cEgYiB1IbWhwKGwRRHl8VCyIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYYCEFXFQ87QQEVURsOXE01EAESBlQZXBUyEAZUGVk%3D\"},\"eTag\":\"132244788656278016\",\"id\":\"AdW63r5RCwE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610936985648,\"modificationTime\":1610989549824,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRteFQMUA1cTWhUyEgZUGF4RAREFUB5eEgQiQwpDBUoyS0IQWhkeHAxfEE8HCllHGAdFBwsCEwZWHl8WARACUB5cEx1LQglGa2tyRkFVYwhRYBdHAGAZUEZqThRQAUMOHjdQGloUAhABVR5aJQITBlYdWxILFwBlG1oUARQHUhJeEmwXBlQaWxcEEgJUdVkUAxAGUh5cEgYiBVQYWhQAIkY7HVMQAxACZRlaFAATAFAcXBEyEgBVGlIdBxUEUh9YHDISD1IrB01HRlIAU1gUARM3ZStYJTIiB2UYa0tsE1RVS1xHVhJpD0FbHQFEQxJ1XhYDFgddG1glABMGVxk%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F165854%2F5%2F2871%2F276615%2F60047d3aE523678e0%2F839c62c41ba97698.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"89.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"航嘉（Huntkey） 暗夜猎手5 电脑机箱台式DIY全侧透游戏水冷ATX大板背线机箱 暗夜猎手5-黑色\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRteFQMUA1cTWhUyEgZUGF4RAREFUB5eEgQiQwpDBUoyS0IQWhkeHAxfEE8HCllHGAdFBwsCEwZWHl8WARACUB5cEx1LQglGa2tyRkFVYwhRYBdHAGAZUEZqThRQAUMOHjdQGloUAhABVR5aJQITBlYdWxILFwBlG1oUARQHUhJeEmwXBlQaWxcEEgJUdVkUAxAGUh5cEgYiBVQYWhQAIkY7HVMQAxACZRlaFAATAFAcXBEyEgBVGlIdBxUEUh9YHDISD1IrB01HRlIAU1gUARM3ZStYJTIiB2UYa0tsE1RVS1xHVhJpD0FbHQFEQxJ1XhYDFgddG1glABMGVxk%3D\"},\"eTag\":\"132244788685111808\",\"id\":\"AdW63r55DAE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610989549831,\"modificationTime\":1610989549831,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtYEgYQAVQfXRQyEQNRGF8TAxoEUhprUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sYXxEBFgFUE1gSAw1eEEcGJQRET1ZhWEcAcXMFbwhqW3AdVhsIUWIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XRECEAVTK1kUAxAGUh5cEgYiB1IbWh0HFQ9dGV8QAyIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYbUkdRRVM7QQEWBxtADRw1EAcTBl0aWxIyEAZUGVk%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F166087%2F9%2F2793%2F124254%2F60025957E2faa412c%2F75520bfb0d229cc4.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"3999.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"名龙堂 i5 10400/GTX1660/1660S/2060/游戏台式直播吃鸡电脑主机DIY组装机\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtYEgYQAVQfXRQyEQNRGF8TAxoEUhprUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sYXxEBFgFUE1gSAw1eEEcGJQRET1ZhWEcAcXMFbwhqW3AdVhsIUWIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XRECEAVTK1kUAxAGUh5cEgYiB1IbWh0HFQ9dGV8QAyIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYbUkdRRVM7QQEWBxtADRw1EAcTBl0aWxIyEAZUGVk%3D\"},\"eTag\":\"132244788688783616\",\"id\":\"AdXT70UxCQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610989549890,\"modificationTime\":1610989549890,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtSHAQUAVYYXRMyFAZREl4QABQDVxJrUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sdWhELFwJXHV8XCw1eEEcGJUJWBjRPDBAGd1QNEzhUQ0R1HWgya1QeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQCGgJRG18lABMGVxpcEAUVA2UbXBUDGgJTHFoTABEBZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBVRFgZVHDVPWBECCRtZUWwXAlUbUhUAFTdXGloXAA%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F159563%2F20%2F2594%2F147312%2F5ffa628dEf4c924b6%2F5cb2a54d5467b120.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"3599.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"华硕电脑 酷睿i5 10400F/GTX1050Ti/1650 4G吃鸡游戏台式主机/DIY组装机\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtSHAQUAVYYXRMyFAZREl4QABQDVxJrUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sdWhELFwJXHV8XCw1eEEcGJUJWBjRPDBAGd1QNEzhUQ0R1HWgya1QeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQCGgJRG18lABMGVxpcEAUVA2UbXBUDGgJTHFoTABEBZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBVRFgZVHDVPWBECCRtZUWwXAlUbUhUAFTdXGloXAA%3D%3D\"},\"eTag\":\"132244788719716096\",\"id\":\"AdXT70cJBwE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610989549896,\"modificationTime\":1611222639132,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRNTFwYbBlATXiUEEgJQHF4TARoGVCsfSlpMWGVCHlBDUAxLBQNQVk4YDk5ER1xOGVMbXhAFFwFWE1oUHUtCCUZrQQMWBx0ZU0JnTmUVHFt8XFQdEW0PdQ4eN1AaWhQCEAFVHlolAhMGVh1bEgsXAGUbWhQBFAdSEl4SbBcGVBpbFwQSAlR1WRQDEAZSHlwSBiIFVBhaFAAiRjscXhwBFw9lGVoUABMAUBxcETISAFUaUxEHEA9VGFIQMhIPUisHTUdGUgBTWBQBEzdlK1glMiIHZRhrS2wTVFVLCEJXR2kPQVgcXUVYVHVeEAAaAVQeUyUAEwZXGQ%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F162441%2F9%2F1905%2F161469%2F5ff97f66E2d758bae%2Fa9bb7fa1f9f26efc.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"4699.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"鑫锐 英特尔i5 10400F/RTX2060升GTX1080高配游戏台式吃鸡电脑主机/DIY组装机 配置四：i5 10400F/GTX1080 8G 单主机\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRNTFwYbBlATXiUEEgJQHF4TARoGVCsfSlpMWGVCHlBDUAxLBQNQVk4YDk5ER1xOGVMbXhAFFwFWE1oUHUtCCUZrQQMWBx0ZU0JnTmUVHFt8XFQdEW0PdQ4eN1AaWhQCEAFVHlolAhMGVh1bEgsXAGUbWhQBFAdSEl4SbBcGVBpbFwQSAlR1WRQDEAZSHlwSBiIFVBhaFAAiRjscXhwBFw9lGVoUABMAUBxcETISAFUaUxEHEA9VGFIQMhIPUisHTUdGUgBTWBQBEzdlK1glMiIHZRhrS2wTVFVLCEJXR2kPQVgcXUVYVHVeEAAaAVQeUyUAEwZXGQ%3D%3D\"},\"eTag\":\"132366994612226816\",\"id\":\"AdXT70c5BQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610989549905,\"modificationTime\":1611222639181,\"uniqueKey\":\"https://item.m.jd.com/product/1720563037.html\",\"contentJson\":{\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"3598.00\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"硕扬 酷睿i7 10700F/9700/4G独显/16G内存水冷吃鸡游戏台式组装电脑主机DIY组装机\",\"category\":\"shopping\",\"url\":\"https://item.m.jd.com/product/1720563037.html\"},\"eTag\":\"132366994637914880\",\"id\":\"AdXT70eBBQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611072856466,\"modificationTime\":1611222639187,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRhfEgUXDlwZWhwyEgZUGF4WAxABURlbEgciQwpDBUoyS0IQWhkeHAxfEE8HCllHGAdFBwsCEwZWHlgUABQDVxtcEB1LQglGa3wKCHM2fDBAYRUAU3kjEHlBcyJuBkMOHjdQGloUAhABVR5aJQITBlYdWxILFwBlG1oUARQHUhJeEmwXBlQaWxcEEgJUdVkUAxAGUh5cEgYiBVQYWhQAIkY7G1oWChIFXB5rFwMTBVQcXhIFFjdVHFsVAxcGVxldHQcaN1UTXCVeSkIBTg5dARMEVCtrJQEiN2UbaxYyTGlUSFsWC0YPAHUBTwBNQg9DD3sHFA5WHV8RCyIFVBpZFw%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F138672%2F21%2F18856%2F731096%2F5fdd9165E2ab4fdd1%2F6e55195423fd9004.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"458.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"BAOTRON 21.5/23.8/24英寸高清IPS台式电脑液晶显示器游戏家用办公显示屏护眼滤蓝光  圆底座23.8英寸宽屏VGA+HDMI口\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRhfEgUXDlwZWhwyEgZUGF4WAxABURlbEgciQwpDBUoyS0IQWhkeHAxfEE8HCllHGAdFBwsCEwZWHlgUABQDVxtcEB1LQglGa3wKCHM2fDBAYRUAU3kjEHlBcyJuBkMOHjdQGloUAhABVR5aJQITBlYdWxILFwBlG1oUARQHUhJeEmwXBlQaWxcEEgJUdVkUAxAGUh5cEgYiBVQYWhQAIkY7G1oWChIFXB5rFwMTBVQcXhIFFjdVHFsVAxcGVxldHQcaN1UTXCVeSkIBTg5dARMEVCtrJQEiN2UbaxYyTGlUSFsWC0YPAHUBTwBNQg9DD3sHFA5WHV8RCyIFVBpZFw%3D%3D\"},\"eTag\":\"132366994641059840\",\"id\":\"AdX7qImJCQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611222639193,\"modificationTime\":1611222639193,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtZEAIbB1MYXxIyEgZUGloXAREBVxtdJUZNXwtEa0xHV0YXEEULWldTCQQAQB1AWQkFWxQDEwZXGFgTABIBSkIeSV8iTxZ4BFcHalQ1bVh%2BAnpxKXwbQX1Wd1kXaxADEwZVGV0VBxM3VRpaFgQSAFweXCUCEwZWHVsSCxcAOx5aFAMSBVMbXhRsEAZUGVoSBxUAUStZFAETBlcrGXvXm7WBl8PMqprS2KuOnKkiBVQaWRQFFwBSH2sVBRIHVRNfEwsQBlIZaxUKFTcJQx5BV0dPVhpYFDIiN1YrayUCIgRlRTUUURIEXE9TQGxIXVISDkVCGmlRHlgdAhcDXStZFAMQBQ%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F32725%2F27%2F15276%2F359507%2F5cc14af0E3fd5740f%2F6f555eb3b230808a.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"398.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"七彩虹（Colorful）战斧B365M-HD PRO V21 游戏主板 支持9100F/9400F（Intel B365/LGA 1151）\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtZEAIbB1MYXxIyEgZUGloXAREBVxtdJUZNXwtEa0xHV0YXEEULWldTCQQAQB1AWQkFWxQDEwZXGFgTABIBSkIeSV8iTxZ4BFcHalQ1bVh%2BAnpxKXwbQX1Wd1kXaxADEwZVGV0VBxM3VRpaFgQSAFweXCUCEwZWHVsSCxcAOx5aFAMSBVMbXhRsEAZUGVoSBxUAUStZFAETBlcrGXvXm7WBl8PMqprS2KuOnKkiBVQaWRQFFwBSH2sVBRIHVRNfEwsQBlIZaxUKFTcJQx5BV0dPVhpYFDIiN1YrayUCIgRlRTUUURIEXE9TQGxIXVISDkVCGmlRHlgdAhcDXStZFAMQBQ%3D%3D\"},\"eTag\":\"132366994644206080\",\"id\":\"AdZDFI_BAgE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611323947591,\"modificationTime\":1611323947591,\"uniqueKey\":\"https://m.mi.com/commodity/detail/2202600020?client_id=180100031058&masid=20120.00000\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fcdn.cnbj0.fds.api.mi-img.com%2Fb2c-shopapi-pms%2Fpms_1593339346.09992308.jpg%3Fw%3D720%26h%3D721%26thumb%3D1,\",\"appName\":\"小米商城\",\"extra\":\"{\\\"price\\\":\\\"49.0\\\"}\",\"componentName\":\"com.xiaomi.shop/com.xiaomi.shop.activity.MainTabActivity\",\"title\":\"小米小爱随身音箱 白色\",\"category\":\"shopping\",\"url\":\"https://m.mi.com/commodity/detail/2202600020?client_id=180100031058&masid=20120.00000\",\"originalUri\":\"http://m.mi.com/p?pid=101&commodityId=2202600020&client_id=180100041080&cid=20120.00000&fallback=https%3A%2F%2Fm.mi.com%2Fcommodity%2Fdetail%2F2202600020%3Fclient_id%3D180100031058%26masid%3D20120.00000\"},\"eTag\":\"132420109421580800\",\"id\":\"AdZzY08xEgE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611323947674,\"modificationTime\":1611323947674,\"uniqueKey\":\"https://m.mi.com/commodity/detail/2202100040?client_id=180100031058&masid=20120.00000\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fcdn.cnbj0.fds.api.mi-img.com%2Fb2c-shopapi-pms%2Fpms_1590547529.25156020.png%3Fw%3D720%26h%3D721%26thumb%3D1,\",\"appName\":\"小米商城\",\"extra\":\"{\\\"price\\\":\\\"699.0\\\"}\",\"componentName\":\"com.xiaomi.shop/com.xiaomi.shop.activity.MainTabActivity\",\"title\":\"Redmi显示器1A 23.8英寸 黑色\",\"category\":\"shopping\",\"url\":\"https://m.mi.com/commodity/detail/2202100040?client_id=180100031058&masid=20120.00000\",\"originalUri\":\"http://m.mi.com/p?pid=101&commodityId=2202100040&client_id=180100041080&cid=20120.00000&fallback=https%3A%2F%2Fm.mi.com%2Fcommodity%2Fdetail%2F2202100040%3Fclient_id%3D180100031058%26masid%3D20120.00000\"},\"eTag\":\"132420109465094400\",\"id\":\"AdZzY1HJCQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611323947680,\"modificationTime\":1611323947680,\"uniqueKey\":\"https://item.m.jd.com/product/63296087237.html\",\"contentJson\":{\"thumbnail\":\"http%3A%2F%2Fm.360buyimg.com%2Fmobilecms%2Fs750x750_jfs%2Ft1%2F131872%2F28%2F16702%2F165295%2F5fb8a8c8E8862f4dc%2Ff000240d6ca87930.jpg%21q80.dpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"3589.00\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"【二手95新】华硕技嘉GTX1080Ti 11G台式机独立游戏显卡吃鸡LOL逆水寒 七彩虹GTX1080Ti AD 11G 【90天包换 终身售后技术支持】\",\"category\":\"shopping\",\"url\":\"https://item.m.jd.com/product/63296087237.html\"},\"eTag\":\"132420109468239360\",\"id\":\"AdZzY1H5BgE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611323947685,\"modificationTime\":1611323947685,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRprFQMTBFcSXxEDFQRSGlwlRk1fC0RrTEdXRhcQRQtaV1MJBABAHUBZCQVbFAMRBVwfXxQFEQBUHERMR05aZVJYYnpEYCpkOxdKWX0GaSdVVwhgLHtXGTIXBlQaWxcEEgJUK1sUAxEBVRxSEAUiB1QaWBMCFQ5QHDUQAxMGVRldFQcTaVcaWhcDFQJSHF8lABMEVBpZJUN8B1QeUxIKEwdlGVoUABMAUBxcETISAFUbWB0FGwFQE1sVMhIPUisHTUdGUgBTWBQBEzdlK1glMiIHZRhrS2wTVFYSXhYBQGkPQV1TWBJGUnVfHAcSBlUeWSUAEwZXGQ%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F160919%2F38%2F3888%2F214706%2F600a2837Ee1c95850%2Fefc49a570768df3e.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"2998.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"磐镭RTX2060 6G DDR5 台式电脑显卡吃鸡逆水寒游戏显卡 6G\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRprFQMTBFcSXxEDFQRSGlwlRk1fC0RrTEdXRhcQRQtaV1MJBABAHUBZCQVbFAMRBVwfXxQFEQBUHERMR05aZVJYYnpEYCpkOxdKWX0GaSdVVwhgLHtXGTIXBlQaWxcEEgJUK1sUAxEBVRxSEAUiB1QaWBMCFQ5QHDUQAxMGVRldFQcTaVcaWhcDFQJSHF8lABMEVBpZJUN8B1QeUxIKEwdlGVoUABMAUBxcETISAFUbWB0FGwFQE1sVMhIPUisHTUdGUgBTWBQBEzdlK1glMiIHZRhrS2wTVFYSXhYBQGkPQV1TWBJGUnVfHAcSBlUeWSUAEwZXGQ%3D%3D\"},\"eTag\":\"132420109470862592\",\"id\":\"AdZzY1IhDQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611323947691,\"modificationTime\":1611323947691,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRprFQMTBFQcXBUGEQBXHV8lRk1fC0RrTEdXRhcQRQtaV1MJBABAHUBZCQVbFAMRBlIcWxEBFQVTH0RMR05aZXASU0lSbidDOV54QlQXRSBpfhtCNV1XGTIXBlQaWxcEEgJUK1sUAxEBVRxSEAUiB1QaWBMCFQ5QHDUQAxMGVRldFQcTaVcaWhcDFQJSHF8lABMEVBpZJUN8B1QeUxIKEwdlGVoUABMAUBxcETISAFUbWRQDGgNXGVgTMhIPUisHTUdGUgBTWBQBEzdlK1glMiIHZRhrS2wTVFVLUkVXE2kPQV1dSURbA3VfHAUVAFEZXSUAEwZXGQ%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F170491%2F33%2F3751%2F189035%2F600a2f9bEdfba7b75%2Fbe3284f0fbbbb38c.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"5999.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"磐镭RTX2080S/RTX2080TI11G显卡电竞游戏显卡绝地求生台式电脑独立显卡 RTX2080S\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRprFQMTBFQcXBUGEQBXHV8lRk1fC0RrTEdXRhcQRQtaV1MJBABAHUBZCQVbFAMRBlIcWxEBFQVTH0RMR05aZXASU0lSbidDOV54QlQXRSBpfhtCNV1XGTIXBlQaWxcEEgJUK1sUAxEBVRxSEAUiB1QaWBMCFQ5QHDUQAxMGVRldFQcTaVcaWhcDFQJSHF8lABMEVBpZJUN8B1QeUxIKEwdlGVoUABMAUBxcETISAFUbWRQDGgNXGVgTMhIPUisHTUdGUgBTWBQBEzdlK1glMiIHZRhrS2wTVFVLUkVXE2kPQV1dSURbA3VfHAUVAFEZXSUAEwZXGQ%3D%3D\"},\"eTag\":\"132420109474006528\",\"id\":\"AdZzY1JRBgE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611492745671,\"modificationTime\":1611492745671,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtbFAIVB10YXxMyFgZUHV4QABcPUh1rUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sfWhQEFwJXHlMSBA1eEEcGJX4TWwVIKGxYcnARQD8TamVkJWBTclQeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XBUEGwFXK1kUAxAGUh5cEgYiB1IbWxAFEgJUHVsTBiIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYbCBMLEg47QQFFXkpcCFk1EgcaB1cZXhYyEAZUGVk%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F169695%2F11%2F3326%2F190043%2F60064d2fEb827c6a0%2Ff8b59147f7acd300.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"4099.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"名睿高配水冷i5 10400/RTX2060/16G内存/吃鸡游戏台式电脑主机DIY组装机全套 配置一\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtbFAIVB10YXxMyFgZUHV4QABcPUh1rUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sfWhQEFwJXHlMSBA1eEEcGJX4TWwVIKGxYcnARQD8TamVkJWBTclQeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XBUEGwFXK1kUAxAGUh5cEgYiB1IbWxAFEgJUHVsTBiIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYbCBMLEg47QQFFXkpcCFk1EgcaB1cZXhYyEAZUGVk%3D\"},\"eTag\":\"132508608229344512\",\"id\":\"AdbD4IsxBQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611492745772,\"modificationTime\":1611492745772,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRhYFQUWAVIdUhYyFQJUHF4SARUEVhJrUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0scXhQFFwBWHFgWCw1eEEcGJUoTdAZtXBEHcR1PawR8ZWZ0FUsYXHIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQBFQNRHVglABMGVxpcEAUVA2UbXBUCFwNUE1IVBhcBZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBVQRQ8GHDVPWEJRBhlfF2wVAlEaWxIGFjdXGloXAA%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F160181%2F16%2F4147%2F139518%2F600780acE9a33f409%2Fe7330026c174a9a0.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"5599.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"戴尔（DELL）OptiPlex 7080MT i7戴尔台式机3D渲染设计办公商用主机|7070升级 i7-10700单主机【含键鼠套装】 16G 128G+1T 集显\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRhYFQUWAVIdUhYyFQJUHF4SARUEVhJrUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0scXhQFFwBWHFgWCw1eEEcGJUoTdAZtXBEHcR1PawR8ZWZ0FUsYXHIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQBFQNRHVglABMGVxpcEAUVA2UbXBUCFwNUE1IVBhcBZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBVQRQ8GHDVPWEJRBhlfF2wVAlEaWxIGFjdXGloXAA%3D%3D\"},\"eTag\":\"132508608282296320\",\"id\":\"AdbD4I5ZAAE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611492745778,\"modificationTime\":1611492745778,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZR5aFgQXB1MSWSUCEgdWHFocARIDUCsfSlpMWGVCHlBDUAxLBQNQVk4YDk5ER1xOGVUbWxYFEw5WG18QHUtCCUZraFdJZRFDRVxhdGEIXBx9ZHZ3Jl8udQ4eN1AaWhQCEAFVHlolAhMGVh1bEgsXAGUbWhQBFAdSEl4SbBcGVBpbFwQSAlR1WRQDEAZSHlwSBiIFVBhaFAAiRjsbXxQLEQ5lGVoUABMAUBxcETISAFUbXhECEQRRGFISMhIPUisHTUdGUgBTWBQBEzdlK1glMiIHZRhrS2wTVFYSC0BQFWkPQQtDVEEBD3VcEAcRAVYYUyUAEwZXGQ%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F152056%2F4%2F14294%2F152830%2F5ffff4b4E3e07774d%2F789b29214fcaff0c.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"5599.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"狄派 i7-9700升10700十代/16G/6G独显台式机游戏电竞吃鸡游戏DIY组装电脑主机整机 电脑主机 套餐一【i7-10700/8G/GTX1060】\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZR5aFgQXB1MSWSUCEgdWHFocARIDUCsfSlpMWGVCHlBDUAxLBQNQVk4YDk5ER1xOGVUbWxYFEw5WG18QHUtCCUZraFdJZRFDRVxhdGEIXBx9ZHZ3Jl8udQ4eN1AaWhQCEAFVHlolAhMGVh1bEgsXAGUbWhQBFAdSEl4SbBcGVBpbFwQSAlR1WRQDEAZSHlwSBiIFVBhaFAAiRjsbXxQLEQ5lGVoUABMAUBxcETISAFUbXhECEQRRGFISMhIPUisHTUdGUgBTWBQBEzdlK1glMiIHZRhrS2wTVFYSC0BQFWkPQQtDVEEBD3VcEAcRAVYYUyUAEwZXGQ%3D%3D\"},\"eTag\":\"132508608285442048\",\"id\":\"AdbD4I6JAAE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611492745783,\"modificationTime\":1611492745783,\"uniqueKey\":\"https://item.m.jd.com/product/30240147161.html\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fm.360buyimg.com%2Fmobilecms%2Fs200x200_jfs%2Ft1%2F154885%2F17%2F16115%2F144154%2F600cc7f2E78e09477%2Fcf5f1275df97ac7d.jpg.webp,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"¥4199.00\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"亚当贝尔 十代酷睿 i5/ i7 -10700F 八核/ GTX2060 电竞办公游戏吃鸡组装台式电脑主机整机 主机+27英寸曲面电竞显示器 配置二：十代酷睿 i5/1650 4G /512G \",\"category\":\"shopping\",\"url\":\"https://item.m.jd.com/product/30240147161.html\"},\"eTag\":\"132508608288067072\",\"id\":\"AdbD4I6xDgE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611492745788,\"modificationTime\":1611492745788,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtSHAQUAVYYXRMyFAZRElkcBxACUhprUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sdWhELEA5QGV4SAw1eEEcGJWB5RCJzUktVcX8VRANnUlFfPm4GRWIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQCGgJRG18lABMGVxpcEAUVA2UbXBUCFwNSElwRCxoBZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBZVQAFSHDVPWEJcEB0AHWwVAlMZWR0HETdXGloXAA%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F161782%2F8%2F3908%2F164844%2F600b8229Ef9ac7d54%2F8dd444ddc6d43297.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"5699.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"华硕电脑i5 10400F/RTX2060 6G/赛博朋克2077吃鸡水冷游戏台式主机/DIY组装机\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtSHAQUAVYYXRMyFAZRElkcBxACUhprUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sdWhELEA5QGV4SAw1eEEcGJWB5RCJzUktVcX8VRANnUlFfPm4GRWIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQCGgJRG18lABMGVxpcEAUVA2UbXBUCFwNSElwRCxoBZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBZVQAFSHDVPWEJcEB0AHWwVAlMZWR0HETdXGloXAA%3D%3D\"},\"eTag\":\"132508608290687744\",\"id\":\"AdbD4I7ZCwE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1610989549900,\"modificationTime\":1611882742475,\"uniqueKey\":\"https://item.m.jd.com/product/68975598975.html\",\"contentJson\":{\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"100.00\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"硕扬 100元 补差价专用链接/DIY组装机\",\"category\":\"shopping\",\"url\":\"https://item.m.jd.com/product/68975598975.html\"},\"eTag\":\"132713078873721088\",\"id\":\"AdXT70dZCQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611492745793,\"modificationTime\":1612436395836,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtYFAUSAVUSXREyFgNXGV4cBxMAVh9rUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sfXxcAFw5QGlwWBg1eEEcGJV5bT09nWG9LclcnTyd1HEtuUUEzU0QeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XBwAEQFRK1kUAxAGUh5cEgYiB1IbWxAFEgFRGV4VBiIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYbUxJXQQI7QQFFXlNPFkY1EgcbAVwaWhYyEAZUGVk%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F150343%2F3%2F16321%2F108635%2F5fffe13aE2e81477d%2F438e332729614861.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"5499.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"小黄鱼 十代i5 10400F 游戏台式电脑主机 16G内存 电竞直播组装全套整机 单主机（不带显示器） 套餐二 RTX2060 6G+512G M.2\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtYFAUSAVUSXREyFgNXGV4cBxMAVh9rUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sfXxcAFw5QGlwWBg1eEEcGJV5bT09nWG9LclcnTyd1HEtuUUEzU0QeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1XBwAEQFRK1kUAxAGUh5cEgYiB1IbWxAFEgFRGV4VBiIHXRxrSVpXUwBOExYDEQZlK2sWMiI3VStYJVx8BgYbUxJXQQI7QQFFXlNPFkY1EgcbAVwaWhYyEAZUGVk%3D\"},\"eTag\":\"133003352687050752\",\"id\":\"AdbD4I8BAQE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611492745798,\"modificationTime\":1612436395929,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtSFgMbBFQTXR0yFAdSE1gdBxACXBprUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sdWxIKEQ9QGV4cAw1eEEcGJVRReAJpA1FUcAINbxJuGHFnM20Qa3IeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQCFgZcHl0lABMGVxpcEAUVA2UbXBUCFwBXGF8RAxYDZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBVSG1NXHTVPWEJYCR8MUGwVAl0TXBEHFzdXGloXAA%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F126449%2F5%2F9657%2F116330%2F5f3625b8E59a08292%2Fe525a99e3b454697.jpg,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"4999.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"武极星冠 十代英特尔酷睿i5 10400F游戏直播设计赛博朋克台式机电脑主机组装电脑 十代i5 GTX1660S 16G 480G\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRtSFgMbBFQTXR0yFAdSE1gdBxACXBprUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0sdWxIKEQ9QGV4cAw1eEEcGJVRReAJpA1FUcAINbxJuGHFnM20Qa3IeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQCFgZcHl0lABMGVxpcEAUVA2UbXBUCFwBXGF8RAxYDZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBVSG1NXHTVPWEJYCR8MUGwVAl0TXBEHFzdXGloXAA%3D%3D\"},\"eTag\":\"133003352735813632\",\"id\":\"AdbD4I8pCgE\",\"type\":\"mipocket\",\"status\":\"normal\"},{\"creationTime\":1611492745804,\"modificationTime\":1612436395944,\"uniqueKey\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRhfHQASAlYcXxQyFwdcHlgXBBAFVBxrUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0seWxwHEQVTGVkUBQ1eEEcGJWlCBxcfO0JScRlWSwEcUmJTE25TRWIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQDGwNUGFMlABMGVxpcEAUVA2UbXBUCFwBWGFsdABYDZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBVQRQ8GHjVPWEJbHlMLRmwVAl0fXRQBEjdXGloXAA%3D%3D\",\"contentJson\":{\"thumbnail\":\"https%3A%2F%2Fimg14.360buyimg.com%2Fpop%2Fjfs%2Ft1%2F164820%2F8%2F5417%2F144621%2F601b632dE32176eb5%2Fac5f970d7f881698.png,\",\"appName\":\"京东\",\"extra\":\"{\\\"price\\\":\\\"3799.0\\\"}\",\"componentName\":\"com.jingdong.app.mall/com.jd.lib.productdetail.ProductDetailActivity\",\"title\":\"变异者 组装电脑主机DIY台式游戏  i5 9400F/16G/GTX1050Ti  战胜G1\",\"category\":\"shopping\",\"url\":\"https://union-click.jd.com/jdc?e=&p=AyIGZRhfHQASAlYcXxQyFwdcHlgXBBAFVBxrUV1KWQorAlBHU0VeBUVNR0ZbSkAOClBMW0seWxwHEQVTGVkUBQ1eEEcGJWlCBxcfO0JScRlWSwEcUmJTE25TRWIeC2UeWhQDEgVTG14UMhIGVBhdFQUbAlIrWxQDEQFVHFIQBXwCVBpaFQAUB1AaNRcDEwVUHF4SBRY3VxpYFAMQNxR1WxQDGwNUGFMlABMGVxpcEAUVA2UbXBUCFwBWGFsdABYDZRtTEjJOXxBPDkBKEQZWGmslMhE3ZStbJQEiWTsaCBVQRQ8GHjVPWEJbHlMLRmwVAl0fXRQBEjdXGloXAA%3D%3D\"},\"eTag\":\"133003352743676416\",\"id\":\"AdbD4I9ZCwE\",\"type\":\"mipocket\",\"status\":\"normal\"}]";
        JSONArray jsonArray = JSONArray.fromObject(str);
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
            String eTag = jsonObject.getString("eTag");
            String parm="serviceToken=ukiexhGkw3Koj6xF6av9HWCtOdQM7VuL6wxTeGxKtXV9NnE5UdsHH808c3lqx0GycCR4asSrOwwFAKFLMR8GCYzuRxTQyFVquMOvBA594j4IJEWH/ZIgxsQuBFM3Vmy8IPwbEsllxYM2TzA7cGU1NbWIRrWl5fvLrsJ3BQUs/7MQjzmRsapYuD868gzAbC0JTGxDUOtQD0eXGbtdz786NMZNJ8+rjPxDEBIIcoeqpy+tmK54MP+IX/WLgPM1nGJmUeXy8j22XLOG1OseTX7e92Y7/DNRdl5PakjEO0f11xXu9pkPhdxT/tOWbLvgZnLqSJh5CDfORev12fYNnJQb8w==";
            parm+="&userId=1140629875&eTag="+eTag;
            String s = HttpRequestUrl.sendPost("https://i.mi.com/mipocket/record/AdbD4I9ZCwE/delete", parm);
            System.out.println(s);
        }


    }



    public static CopyOnWriteArrayList<String> ConcurrentRun(String url, String parm, Integer max) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
        try {
            for (int i = 0; i < max; i++) {
                Concurrent concurrent = new Concurrent();
                concurrent.url = url;
                concurrent.parm = parm;
                concurrent.start();
                concurrent.join();
                list.add(concurrent.getResult());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static class Concurrent extends Thread {
        static String url;
        static String parm;
        static String result;

        @Override
        public void run() {
            result = HttpRequestUrl.sendPost(url, parm);
        }

        public static String getResult() {
            return result;
        }
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO
     * @CreateTime: 2020/8/26 19:54
     * @param: jsonParam  json参数
     * @param: urls 地址
     * @return: java.lang.String
     */
    public static String getJsonData(JSONObject jsonParam, String urls) {
        if (urls == null) {
            return urls;
        }
        StringBuffer sb = new StringBuffer();
        try {
            // 创建url资源
            URL url = new URL(urls);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);
            // 设置允许输入
            conn.setDoInput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            // 转换为字节数组
            byte[] data = (jsonParam.toString()).getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("contentType", "application/json");
            // 开始连接请求
            conn.connect();
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 写入请求的字符串
            out.write((jsonParam.toString()).getBytes());
            out.flush();
            out.close();
            // 请求返回的状态
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                // 请求返回的数据
                InputStream in1 = conn.getInputStream();
                try {
                    String readLine = new String();
                    BufferedReader responseReader = new BufferedReader(new InputStreamReader(in1, "UTF-8"));
                    while ((readLine = responseReader.readLine()) != null) {
                        sb.append(readLine).append("\n");
                    }
                    responseReader.close();
                } catch (Exception e1) {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString();
    }


    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        if (url == null || param == null) {
            return null;
        }
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                return null;
            }
        }
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        if (url == null) {
            return url;
        }
        if (url == param) {
            return param;
        }
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpsURLConnection conn = (HttpsURLConnection)realUrl.openConnection();
            //URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                return null;
            }
        }
        return result;
    }
}
