package com.articlesystem.servlet;


import com.articlesystem.Utils.MyUtils;
import com.articlesystem.entity.FileInfo;
import com.articlesystem.entity.Msg;
import com.articlesystem.exception.InvalidFileSuffixException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * @author 云佳
 * @create 2022-10-31 13:44
 * @只管耕耘，莫问收获。
 */
@WebServlet(urlPatterns = "/manager/upload/img")
public class UploadFileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public final String allowSuffix = ".jpg.jpeg.png";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 存储返回信息。
        Msg msg;
        // 获取servletFileUpload对象。
        ServletFileUpload servletFileUpload = getServletFileUpload();
        try {
        //把需要上传的 FileItem 都放入到该 Map 中
        //键: 文件的待存放的路径, 值: 对应的 FileItem 对象
        HashMap<String, FileItem> uploadFiles = new HashMap<>();
        // 解析请求，获取FileItem集合
        List<FileItem> items = servletFileUpload.parseRequest(request);
        // 构建 FileUploadBean 的集合, 同时填充 uploadFiles
        List<FileInfo> beans = buildFileUploadBeans(items,uploadFiles);
        // 校验文件后缀名
        FileInfo fileInfo = beans.get(0);
        String fileName = fileInfo.getFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        if (allowSuffix.indexOf(suffix) == -1){
            throw new InvalidFileSuffixException("文件后缀名不合法");
        }

        // 将文件写入到本地服务器。
        upload(uploadFiles);
        // 完整的图片url
        String fileUrl  = fileInfo.getFilepath();
        msg = Msg.success().add("fileUrl",fileUrl);


        } catch (InvalidFileSuffixException e) {
            e.printStackTrace();
            msg = Msg.fail().add("message",e.getMessage());
        } catch (org.apache.commons.fileupload.FileUploadException e) {
            e.printStackTrace();
            msg = Msg.fail().add("message",e.getMessage());
        }
        // 写数据给前端
        MyUtils.JsonResultToWrite(msg,response.getWriter());

    }

    /**
     * 构建 ServletFileUpload 对象
     * 从配置文件中读取了部分属性, 用户设置约束.
     * 该方法代码来源于文档.
     *
     * @return
     */
    private ServletFileUpload getServletFileUpload() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 超出临界值，存放文件到临时目录。
        String TempDirectory = "D:\\ArticleData\\TempDirectory";
        factory.setSizeThreshold(5242880);
        File tempDirectory = new File(TempDirectory);
        factory.setRepository(tempDirectory);

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(104857600);
        upload.setFileSizeMax(20971520);
        return upload;
    }

    private List<FileInfo> buildFileUploadBeans(List<FileItem> items,Map<String, FileItem> uploadFiles) throws UnsupportedEncodingException {
        List<FileInfo> beans = new ArrayList<>();

        for (FileItem item : items) {
            FileInfo bean = null;
            if(!item.isFormField()){
                //对应文件名
                String originalName = item.getName();

                String FilePathUrl = getTempFilePath(originalName);
                bean = new FileInfo(originalName,FilePathUrl);
                beans.add(bean);
                //得到一个存放资源的磁盘的绝对路径
                String absoluteFilePath = getabsoluteFilePath(FilePathUrl);
                uploadFiles.put(absoluteFilePath, item);
            }

        }
        return beans;
    }

    /**
     * 根据跟定的文件名构建一个随机的文件名
     * 1. 构建的文件的文件名的扩展名和给定的文件的扩展名一致
     * 2. 获取一个tomcat的虚拟路径,将虚拟路径存放在数据库中，以便于用浏览器访问资源
     * 3. 利用了 Random 和 当前的系统时间构建随机的文件的名字
     *
     * @param fileName
     * @return
     */
    private String getTempFilePath(String fileName) {
        //文件后缀,如.jpeg
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        Random random = new Random();
        // tomcat虚拟文件夹
        String TomcatVirtualDirectoty = "http://localhost:8080/ArticleSystem/ArticleSystemImg/";

        String filePath = TomcatVirtualDirectoty+ System.currentTimeMillis() + random.nextInt(100000) + suffix;
        return filePath;
    }
    /**
     *  构建一个用与存放资源的绝对路径（带盘符）
     *
     */
    private String getabsoluteFilePath(String tempFilePath) {
        String extName = tempFilePath.substring(tempFilePath.lastIndexOf("/")+1);
        String UpLoadDirectory = "D:\\ArticleSystem\\ArticleSystemImg\\";
        // 如果目标文件不存在，创建目录。
        File file = new File(UpLoadDirectory);
        if (!file.exists()){
            boolean mkdirs = file.mkdirs();
        }

        String absoluteFilePath = UpLoadDirectory+extName;
        return absoluteFilePath;
    }

    /**
     * 文件上传前的准备工作. 得到 filePath 和 InputStream
     * @param uploadFiles
     * @throws IOException
     */

    private void upload(Map<String, FileItem> uploadFiles) throws IOException {
        for(Map.Entry<String, FileItem> uploadFile: uploadFiles.entrySet()){
            String filePath = uploadFile.getKey();
            FileItem item = uploadFile.getValue();

            upload(filePath, item.getInputStream());
        }
    }
    /**
     * 文件上传的 IO 方法.
     *
     * @param filePath
     * @param inputStream
     * @throws IOException
     */
    private void upload(String filePath, InputStream inputStream) throws IOException {


        OutputStream out = new FileOutputStream(filePath);

        byte [] buffer = new byte[1024];
        int len = 0;

        while((len = inputStream.read(buffer)) != -1){
            out.write(buffer, 0, len);
        }

        inputStream.close();
        out.close();

    }
}
