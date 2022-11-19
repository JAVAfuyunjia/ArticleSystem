package com.articlesystem.Utils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 云佳
 * @create 2022-11-15 16:25
 * @往之不谏，来者可追。
 */
public class PageUtils<T> {
    // 总记录数
    private int totalNum;

    // 每页的记录数
    private int pageSize;

    // 当前页码
    private int currentPage;

    // 数据
    private List<T> list = new ArrayList<T>();

    public PageUtils() {
    }

    public PageUtils(int totalNum, int pageSize, int currentPage) {
        this.totalNum = totalNum;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public String toString(){
        return JSON.toJSONString(this);
    }
}
