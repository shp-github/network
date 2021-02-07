package com.shp.dev.network.common.util.excel;

import com.shp.dev.network.common.util.ShpUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelUtils<T> {


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 导出excel表格,一xlsx文件 一个工作表  多个表数据
     * @CreateTime: 2020/10/14 10:31
     * @param: outFlePath 输出地址
     * @param: talbeName 工作簿名称
     * @param: mapData map的key作为标题名称，map的值为导出表的数据
     * @return: java.lang.String
     */
    public static String writeTableMany(String outFlePath, String talbeName, Map<Object, List> mapData) {
        ShpUtils utils = new ShpUtils();
        //创建HSSFWorkbook对象
        Workbook wb = null;
        //输出流
        FileOutputStream output = null;
        String str = outFlePath.substring(outFlePath.lastIndexOf("."));
        if (str.equals(".xls")) {
            //HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls
            wb = new HSSFWorkbook();
        }
        if (str.equals(".xlsx")) {
            //XSSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx
            wb = new XSSFWorkbook();
        }


        List resultList = new ArrayList();
        //int sum=0;
        //创建HSSFSheet对象
        Sheet sheet = wb.createSheet(talbeName);
        //遍历map 获取多个表中的数据
        for (Map.Entry<Object, List> data : mapData.entrySet()) {
            //sum+=data.getValue().size();

            for (int i = 0; i < data.getValue().size(); i++) {
                resultList.add(data.getValue().get(i));
            }
        }

        for (int i = 0; i < resultList.size(); i++) {
            //创建HSSFRow对象
            Row row = sheet.createRow(i);
            Map<Object, Object> map = utils.obj2Map(resultList.get(i));
            int j = 0;
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                //创建HSSFCell对象
                Cell cell = row.createCell(j++);
                //设置单元格的值
                cell.setCellValue(entry.getValue().toString());
            }
        }

        try {
            //输出Excel文件
            output = new FileOutputStream(outFlePath);
            wb.write(output);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return outFlePath;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 导出excel表格,一xlsx文件 一个工作表
     * @CreateTime: 2020/9/5 12:36
     * @param: talbeName 工作表名称
     * @param: outFlePath 输出地址
     * @param: list 对象集合数据
     * @return: java.lang.String
     */
    public static String writeTable(String talbeName, String outFlePath, List list) {
        ShpUtils utils = new ShpUtils();
        //创建HSSFWorkbook对象
        Workbook wb = null;
        //输出流
        FileOutputStream output = null;
        String str = outFlePath.substring(outFlePath.lastIndexOf("."));
        if (str.equals(".xls")) {
            //HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls
            wb = new HSSFWorkbook();
        }
        if (str.equals(".xlsx")) {
            //XSSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx
            wb = new XSSFWorkbook();
        }
        //创建HSSFSheet对象
        Sheet sheet = wb.createSheet(talbeName);
        for (int i = 0; i < list.size(); i++) {
            //创建HSSFRow对象
            Row row = sheet.createRow(i);
            Map<Object, Object> map = utils.obj2Map(list.get(i));
            int j = 0;
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                //创建HSSFCell对象
                Cell cell = row.createCell(j++);
                //设置单元格的值
                cell.setCellValue(entry.getValue().toString());
            }
        }
        try {
            //输出Excel文件
            output = new FileOutputStream(outFlePath);
            wb.write(output);
            output.flush();
        } catch (Exception e) {
            return null;
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return outFlePath;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 导出excel表格,一xlsx文件多个工作表
     * @CreateTime: 2020/9/5 17:28
     * @param: outFlePath 输出地址
     * @param: mapData map的key作为工作表的名称，map的值为导出表的数据
     * @return: java.lang.String
     */
    public static String writeTable(String outFlePath, Map<Object, List> mapData) {
        ShpUtils utils = new ShpUtils();
        //创建HSSFWorkbook对象
        Workbook wb = null;
        //输出流
        FileOutputStream output = null;
        String str = outFlePath.substring(outFlePath.lastIndexOf("."));
        if (str.equals(".xls")) {
            //HSSFWorkbook:是操作Excel2003以前（包括2003）的版本，扩展名是.xls
            wb = new HSSFWorkbook();
        }
        if (str.equals(".xlsx")) {
            //XSSFWorkbook:是操作Excel2007的版本，扩展名是.xlsx
            wb = new XSSFWorkbook();
        }
        for (Map.Entry<Object, List> data : mapData.entrySet()) {
            //创建HSSFSheet对象
            Sheet sheet = wb.createSheet(data.getKey().toString());
            for (int i = 0; i < data.getValue().size(); i++) {
                //创建HSSFRow对象
                Row row = sheet.createRow(i);
                Map<Object, Object> map = utils.obj2Map(data.getValue().get(i));
                int j = 0;
                for (Map.Entry<Object, Object> entry : map.entrySet()) {
                    //创建HSSFCell对象
                    Cell cell = row.createCell(j++);
                    //设置单元格的值
                    cell.setCellValue(entry.getValue().toString());
                }
            }
        }
        try {
            //输出Excel文件
            output = new FileOutputStream(outFlePath);
            wb.write(output);
            output.flush();
        } catch (Exception e) {
            return null;
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return outFlePath;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 读取excel中所有工作表的内容
     * @CreateTime: 2020/9/4 10:23
     * @param: file 文件地址
     * @return: java.util.List
     */
    public static List inputExcel(String file) {
        Workbook workbook;
        List list = new ArrayList();
        List rowList;
        try {
            try {
                workbook = new XSSFWorkbook(new File(file));
            } catch (Exception ex) {
                workbook = new HSSFWorkbook(new FileInputStream(new File(file)));
            }
            Sheet sheet = null;
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
                sheet = workbook.getSheetAt(i);
                for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {//获取每行
                    Row row = sheet.getRow(j);
                    rowList = new ArrayList();
                    for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {//获取每个单元格
                        rowList.add(row.getCell(k));
                    }
                    list.add(rowList);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 获取excel中所有工作表
     * @CreateTime: 2020/9/4 10:32
     * @param: file
     * @return: java.util.List
     */
    public static List<String> forInput(String file) {
        Workbook workbook;
        String sheetName;
        List<String> list = new ArrayList();
        try {
            try {
                workbook = new XSSFWorkbook(new File(file));
            } catch (Exception ex) {
                workbook = new HSSFWorkbook(new FileInputStream(new File(file)));
            }
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {//获取每个Sheet表
                sheetName = workbook.getSheetAt(i).getSheetName();
                list.add(sheetName);
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 读取excel指定工作表
     * @CreateTime: 2020/9/4 2:30
     * @param: file 文件地址
     * @param: table 工作表名
     * @return: java.util.List<java.util.List < java.lang.Object>>
     */
    public List<List<Object>> inputExcel(File file, String table) {
        List<List<Object>> list = new ArrayList();
        FileInputStream in = null;//输入流
        Workbook workbook = null; //工作薄
        try {
            try {
                workbook = new XSSFWorkbook(file);
            } catch (Exception ex) {
                workbook = new HSSFWorkbook(new FileInputStream(file));
            }
            Sheet sheet = workbook.getSheet(table);  //获取表
            //System.out.println("一共有"+sheet.getLastRowNum()+1+"行");//最后一行
            //System.out.println("每行有"+sheet.getRow(0).getLastCellNum()+"列");
            //根据最后一行遍历所有行
            for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
                List rowList = new ArrayList();
                //根据最后列遍历所欲列
                for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                    //如果某行某列（某个单元格）中的内容不为空。
                    if (sheet.getRow(i).getCell(j) != null) {
                        rowList.add(sheetToType(sheet, i, j));//根据对应类型进行指定
                        //list1.add(sheetToString(sheet,i,j));//全部指定为string类型
                    }
                }
                list.add(rowList);
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                return null;
            }
        }
        return list;
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 向页面输出excel
     * @CreateTime: 2020/9/5 17:42
     * @param: response 服务端相应数据
     * @param: tList 要到出的结果集合
     * @param: headers 标题数组
     * @param: tablelName 工作表名字
     * @param: fileName 文件名字
     * @return: void
     */
    public String OutExcel(HttpServletResponse response, List<T> tList, String[] headers, String tablelName, String fileName) {
        HSSFWorkbook workbook = new HSSFWorkbook();//创建模板
        HSSFSheet sheet = workbook.createSheet(tablelName);//创建excel工作表
        fileName = fileName + System.currentTimeMillis() + ".xls";//设置要导出的文件名字
        //headers表示excel表中第一行表头，在excel表中添加表头
        HSSFRow row = sheet.createRow(0);//创建第一行
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);//根据第一行创建每一列
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);//增加每一列
            cell.setCellValue(text);//保存进去值
        }
        int rowNum = 1; //新增数据行，并且设置单元格数据
        Object invoke;
        Method getMethod;
        PropertyDescriptor pd;
        HSSFRow row1;
        Class clazz;
        Field[] fields;
        try {
            for (T t : tList) { //循环添加到行
                row1 = sheet.createRow(rowNum);
                clazz = t.getClass();//获得实体类名
                fields = t.getClass().getDeclaredFields();//获得属性
                for (int i = 0; i < fields.length; i++) {//循环添加到列
                    pd = new PropertyDescriptor(fields[i].getName(), clazz);//获得Object对象中的所有方法
                    getMethod = pd.getReadMethod();//获得get方法
                    invoke = getMethod.invoke(t);//此处为执行该Object对象的get方法
                    row1.createCell(i).setCellValue(invoke != null ? invoke.toString() : "");//添加到每一列中
                }
                rowNum++;
            }
            //向页面输出文件
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.flushBuffer();//刷新缓存
            workbook.write(response.getOutputStream());//响应给页面输出流
        } catch (Exception e) {
            return null;
        }
        return fileName;
    }


    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 传入列转换为string类型
     * @CreateTime: 2020/9/4 2:26
     * @param: sheet
     * @param: i
     * @param: j
     * @return: java.lang.String
     */
    public static String sheetToString(Sheet sheet, int i, int j) {
        sheet.getRow(i).getCell(j).setCellType(CellType.STRING); //把所有类型强制为string类型
        sheet.getRow(i).getCell(j).getStringCellValue();
        return sheet.getRow(i).getCell(j).getStringCellValue();
    }

    /**
     * @CreateBy: shp
     * @version：1.0
     * @Description: TODO 根据类型转换对象类型
     * @CreateTime: 2020/9/4 2:26
     * @param: sheet
     * @param: i
     * @param: j
     * @return: java.lang.Object
     */
    public static Object sheetToType(Sheet sheet, int i, int j) {
        Object value = "";
        SimpleDateFormat formatter = null;
        if (sheet.getRow(i).getCell(j).getCellTypeEnum().toString().equals("STRING")) {//字符串
            value = sheet.getRow(i).getCell(j).getStringCellValue();
        }
        if (sheet.getRow(i).getCell(j).getCellTypeEnum().toString().equals("BOOLEAN")) {//布尔类型
            value = sheet.getRow(i).getCell(j).getBooleanCellValue();
        }
        if (sheet.getRow(i).getCell(j).getCellTypeEnum().toString().equals("ERROR")) {//异常
            value = sheet.getRow(i).getCell(j).getErrorCellValue();
        }
        if (sheet.getRow(i).getCell(j).getCellTypeEnum().toString().equals("BLANK")) {//空值
            value = "";
        }
        if (sheet.getRow(i).getCell(j).getCellTypeEnum().toString().equals("FORMULA")) {//公式计算后的结果
            value = sheet.getRow(i).getCell(j).getNumericCellValue();
        }
        if (sheet.getRow(i).getCell(j).getCellTypeEnum().toString().equals("NUMERIC")) {//数字类型
            if (HSSFDateUtil.isCellDateFormatted(sheet.getRow(i).getCell(j))) {//如果为日期类型
                value = new SimpleDateFormat("yyyy-MM-dd").format(sheet.getRow(i).getCell(j).getDateCellValue());// 获取日期类型的单元格的值
            } else {
                NumberFormat nf = NumberFormat.getInstance();
                Cell num = sheet.getRow(i).getCell(j);
                String s = nf.format(num.getNumericCellValue());
                //这种方法对于自动加".0"的数字可直接解决
                //但如果是科学计数法的数字就转换成了带逗号的，例如：12345678912345的科学计数法是1.23457E+13，经过这个格式化后就变成了字符串“12,345,678,912,345”，这也并不是想要的结果，所以要将逗号去掉
                if (s.indexOf(",") >= 0) {
                    s = s.replace(",", "");
                }
                value = s;
                //value=sheet.getRow(i).getCell(j).getNumericCellValue();//否则为数字类型。
            }
        }
        return value;
    }

}