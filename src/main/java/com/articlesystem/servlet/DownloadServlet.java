package com.articlesystem.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * Servlet implementation class DownloadServlet
 */

@WebServlet(urlPatterns = "/manager/attachmentDownload")
public class DownloadServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("application/x-msdownload");

    String fileNameNow = request.getParameter("attachmentPath");
    response.setHeader("Content-Disposition",
        "attachment;filename=" + URLEncoder.encode(fileNameNow, "UTF-8"));

    String attachemntPath = "D:\\files\\" + fileNameNow;

    OutputStream out = response.getOutputStream();

    //D:\files\1671951553784202211590072付云佳-第十四周实验.doc
    InputStream in = new FileInputStream(attachemntPath);

    byte[] buffer = new byte[1024];
    int len = 0;

    while ((len = in.read(buffer)) != -1) {
      out.write(buffer, 0, len);
    }
    in.close();

  }

}
