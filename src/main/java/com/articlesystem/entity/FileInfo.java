package com.articlesystem.entity;

/**
 * @author 云佳
 * @create 2022-10-31 15:50
 * @只管耕耘，莫问收获。
 */
public class FileInfo {
    private Integer id;

    private String filename;

    private String filepath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }
    public FileInfo() {
        // TODO Auto-generated constructor stub
    }



    public FileInfo(String filename, String filepath) {
        super();
        this.filename = filename;
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return "FileInfo [id=" + id + ", filename=" + filename + ", filepath=" + filepath + "]";
    }



}
