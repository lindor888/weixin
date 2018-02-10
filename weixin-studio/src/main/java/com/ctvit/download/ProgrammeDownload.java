package com.ctvit.download;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ctvit.bean.ChakanBean;
import com.ctvit.dao.InteractMapper;
import com.ctvit.service.InteractService;

public class ProgrammeDownload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InteractMapper interactMapper;

	// private InteractService interactService = new InteractService();
	@SuppressWarnings({ "deprecation", "deprecation" })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			InteractService interactService = (InteractService) WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("interactService");
			String programme_id = request.getParameter("programme_id");
			ChakanBean chakanBean2 = new ChakanBean();
			chakanBean2.setProgramme_save_id(programme_id);
			System.out.println("programme_id  " + programme_id);
			// RandomUtil ru = new RandomUtil();
			// List<ChakanBean> list = ru.chakanbean(programme_id);
			List<ChakanBean> list = new ArrayList<ChakanBean>();
			list = interactService.programmeChankanUtil(programme_id, 1);
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("节目单内容");
			// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

			sheet.setColumnWidth(0, 10 * 256);
			sheet.setColumnWidth(5, 30 * 256);
			sheet.setColumnWidth(6, 30 * 256);
			sheet.setColumnWidth(7, 10 * 256);
			sheet.setColumnWidth(8, 30 * 256);
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

			HSSFCell cell = row.createCell((short) 0);
			cell.setCellValue("昵称");
			cell.setCellStyle(style);
			cell = row.createCell((short) 1);
			cell.setCellValue("性别");
			cell.setCellStyle(style);
			cell = row.createCell((short) 2);
			cell.setCellValue("地区");
			cell.setCellStyle(style);
			cell = row.createCell((short) 3);
			cell.setCellValue("城市");
			cell.setCellStyle(style);
			cell = row.createCell((short) 4);
			cell.setCellValue("国家");
			cell.setCellStyle(style);
			cell = row.createCell((short) 5);
			cell.setCellValue("内容");
			cell.setCellStyle(style);
			cell = row.createCell((short) 6);
			cell.setCellValue("时间");
			cell.setCellStyle(style);
			cell = row.createCell((short) 7);
			cell.setCellValue("处理状态");
			cell.setCellStyle(style);
			// cell = row.createCell((short) 8);
			// cell.setCellValue("操作");
			cell.setCellStyle(style);

			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow((int) i + 1);
				ChakanBean bean = list.get(i);
				row.createCell((short) 0).setCellValue(bean.getNickname());
				cell.setCellStyle(style);

				row.createCell((short) 1).setCellValue(bean.getSex());
				cell.setCellStyle(style);
				row.createCell((short) 2).setCellValue(bean.getCity());
				cell.setCellStyle(style);
				row.createCell((short) 3).setCellValue(bean.getProvince());
				cell.setCellStyle(style);
				row.createCell((short) 4).setCellValue(bean.getCountry());
				cell.setCellStyle(style);
				row.createCell((short) 5).setCellValue(bean.getContent());
				cell.setCellStyle(style);
				row.createCell((short) 6).setCellValue(myFmt.format(bean.getUpdateTime()));
				cell.setCellStyle(style);
				if (bean.getFlag() == 0) {
					row.createCell((short) 7).setCellValue("未处理");
					cell.setCellStyle(style);
				} else if (bean.getFlag() == 1) {
					row.createCell((short) 7).setCellValue("审核通过");
					cell.setCellStyle(style);
				} else {
					row.createCell((short) 7).setCellValue("审核未通过");
					cell.setCellStyle(style);
				}
				// row.createCell((short) 8).setCellValue("修改   通过   拒绝   删除");
				cell.setCellStyle(style);

			}
			response.addHeader("Content-Disposition", "attachment;filename=" + "sheet.xls");
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			wb.write(os);
			os.flush();
			os.close();

		} catch (Exception e) {

			e.printStackTrace();

		}
		// PrintWriter pw = response.getWriter();
		// pw.write(queryJson.toString());

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

}
