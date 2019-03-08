package com.example.bean;

import java.util.List;

public class ResultBean {
    private int total;//总页数
    private int allSize;//查询结果总数据量
    private int pageNumber;//	当前页数
    private List<RawBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAllSize() {
        return allSize;
    }

    public void setAllSize(int allSize) {
        this.allSize = allSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<RawBean> getRows() {
        return rows;
    }

    public void setRows(List<RawBean> rows) {
        this.rows = rows;
    }
}
