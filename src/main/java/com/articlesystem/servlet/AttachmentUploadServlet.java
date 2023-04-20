package com.articlesystem.servlet;

import com.articlesystem.Utils.MyUtils;
import com.articlesystem.entity.Msg;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Random;

/**
 * @author 云佳
 * @create 2022-12-25 10:15
 * @往之不谏，来者可追。
 */
@WebServlet(urlPatterns = "/manager/attachmentUpload")
public class AttachmentUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 存储返回信息。
        Msg msg = null;

        // 获取servletFileUpload对象。
        ServletFileUpload servletFileUpload = getServletFileUpload();

        try {
        // 解析请求，获取FileItem集合
            List<FileItem> items = servletFileUpload.parseRequest(request);
            //
            // 2. 遍历 items:
            for (FileItem item : items) {
                // 若是文件域
                if (!item.isFormField()){
                    Random random = new Random();
                    String fieldName = item.getFieldName();
                    String fileName = System.currentTimeMillis() + random.nextInt(100000)+item.getName();
                    String contentType = item.getContentType();
                    long sizeInBytes = item.getSize();


                    InputStream in = item.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;

                    String filePath = "D:\\files\\" + fileName;
                    System.out.println(filePath);

                    msg = Msg.success().add("filePath",filePath);
                    OutputStream out = new FileOutputStream(filePath);

                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }

                    out.close();
                    in.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
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
        factory.setSizeThreshold(104857600);
        File tempDirectory = new File(TempDirectory);
        factory.setRepository(tempDirectory);

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(1048576000);
        upload.setFileSizeMax(629145600);
        return upload;
    }


}
