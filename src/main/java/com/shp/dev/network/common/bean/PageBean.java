package com.shp.dev.network.common.bean;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @CreateBy: shp
 * @Version: 1.0
 * @Description: TODO 分页返回值类
 * @CreateTime: 2020-07-08 9:59
 * @PackageName: com.shp.dev.network.common.bean
 * @ProjectName: network
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = -9202109574544652243L;
    private long total;        //总记录数
    private List<T> data;    //结果集
    private int pageNum;    // 第几页
    private int pageSize;    // 每页记录数
    private int pages;        // 总页数
    private int size;        // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性

    private String countColumn; //进行count查询的列名
    private int endRow; //末行
    private int startRow;//startRow
    private String orderBy;//排序
    private Boolean pageSizeZero;//当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
    private Boolean reasonable;//分页合理化

    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，
     * 而出现一些问题。
     * @param list          page结果
     * @param
     */
    public PageBean(List<T> list) {
        if (list instanceof Page) {

            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.data = page;
            this.size = page.size();

            this.reasonable=page.getReasonable();
            this.pageSizeZero=page.getPageSizeZero();
            this.orderBy=page.getOrderBy();
            this.startRow=page.getStartRow();
            this.endRow=page.getEndRow();
            this.countColumn=page.getCountColumn();
        }
    }
}