package com.ctvit.integralmall.action;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import com.ctvit.util.FileTools;
import com.ctvit.util.ResourceLoader;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class GoodsUploadAction extends ActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> map;
	private FileTools filetool;
	private File fileInput;
	private String fileInputFileName;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public FileTools getFiletool() {
		return filetool;
	}

	public void setFiletool(FileTools filetool) {
		this.filetool = filetool;
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public String upload() {
		try {
			String v = getFileInputFileName();
			String vv = v.substring(v.indexOf(".")).toLowerCase();
			Calendar cal = Calendar.getInstance();
			String year = String.valueOf(cal.get(Calendar.YEAR));
			String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
			String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
			String m = String.valueOf(System.currentTimeMillis());
			int rand = (new Random()).nextInt(1000);
			String values = File.separator + year + month + day + File.separator;
			// String values = "/";
			String fileName = "GOODS" + m + "_" + rand + vv;
			String path = ResourceLoader.getInstance().getConfig().getProperty("goods_imageFile") + values + fileName;
			String urlPath = ResourceLoader.getInstance().getConfig().getProperty("goods_imageUrl") + values + fileName;

			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				File file = new File(path);
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();
				if (!file.exists())
					file.createNewFile();
				fos = new FileOutputStream(path);
				// 建立文件上传流
				fis = new FileInputStream(fileInput);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				// 图片压缩
				int width = Integer.parseInt(ResourceLoader.getInstance().getConfig().getProperty("goods_image_width"));
				int height = Integer.parseInt(ResourceLoader.getInstance().getConfig().getProperty("goods_image_height"));
				this.compressPic(path, path, "", "", width, height, false);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				fis.close();
				fos.close();
			}
			map = new HashMap<String, Object>();
			map.put("webUrl", urlPath);
			map.put("image", urlPath);
			map.put("status", 0);
		} catch (Exception e) {
			map.put("status", 1);
		}

		return "dft";
	}

	public String compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean proportion) {
		try {
			//
			File infile = new File(inputDir + inputFileName);
			if (!infile.exists()) {
				return "";
			}
			Image img = ImageIO.read(infile);
			//
			if (img.getWidth(null) == -1) {
				System.out.println(" can't read,retry!" + "<BR>");
				return "no";
			} else {
				int newWidth;
				int newHeight;
				//
				if (proportion == true) {
					//
					double rate1 = ((double) img.getWidth(null)) / (double) width + 0.1;
					double rate2 = ((double) img.getHeight(null)) / (double) height + 0.1;
					//
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {
					newWidth = width; //
					newHeight = height; //
				}
				BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
				File outDir = new File(outputDir);
				if (!outDir.exists()) {
					outDir.mkdirs();
				}

				/*
				 * Image.SCALE_SMOOTH
				 */
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
				FileOutputStream out = new FileOutputStream(outDir.getPath() + File.separator + outputFileName);
				// JPEGImageEncoder
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				out.close();
			}
			return "ok";
		} catch (IOException ex) {
			ex.printStackTrace();
			return "error";
		}
	}

}
