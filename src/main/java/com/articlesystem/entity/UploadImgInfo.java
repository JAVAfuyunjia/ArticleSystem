package com.articlesystem.entity;

/**
 * @author 云佳
 * @create 2022-11-30 22:30
 * @往之不谏，来者可追。
 */
public class UploadImgInfo {
    private String src;

    private String title;

    public UploadImgInfo() {
    }

    public UploadImgInfo(String src, String title) {
        this.src = src;
        this.title = title;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
