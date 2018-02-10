package com.ctvit.download;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ctvit.util.ResourceLoader;

public class DownloadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1186937759536619898L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应头文件，通知浏览器做下载
		// 这是针对非中文的response.setHeader("Content-Disposition",
		// "attachment;filename=1.jpg");
		// 中文下载才需要+URLEncoder.encode();
		String name = "WEIXIN" + request.getParameter("imgname");
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
		response.setHeader("Content-Type", "application/octet-stream");
		String downloadurl = ResourceLoader.getInstance().getConfig().getProperty("QRimgPath") + name;
		// 获取图片的真实地址。
		String realPath = downloadurl;
		// 新建一个文件输入流
		InputStream is = new FileInputStream(realPath);
		// 得到一个文件输出流，可以向浏览器输出数据
		OutputStream os = response.getOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = is.read(buffer)) != -1) {
			os.write(buffer, 0, len);
		}
		is.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}