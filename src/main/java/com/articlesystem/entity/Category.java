package com.articlesystem.entity;

/**
 * @author 云佳
 * @create 2022-11-15 19:31
 * @往之不谏，来者可追。
 */
public class Category {
    private Integer categoryId;

    private String categoryName;

    public Category() {
    }

    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
