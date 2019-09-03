package com.qf.book.utlis;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName UpUtils
 * @Description 文件上传工具类
 */
public class UpUtils {

	public static String upfile(MultipartFile file, HttpServletRequest request) {
		//获取我们项目在tomcat上路径
		String realPath = request.getSession().getServletContext().getRealPath("/");
		//获取本地路径
		String basePath="D:\\java\\workSpace\\book\\src\\main\\webapp\\";
		// 设置文件上传后的保存路径
		String savePath = "images/upFlie/";
		File file1 = new File(realPath + savePath);
		// 判断 路径是否存在 若不存在 则创建目录
		if (!file1.exists()) {
			file1.mkdirs();
		}
		File file2 = new File(basePath+savePath);
		if(!file2.exists()) {
             file2.mkdirs();			
		}
		//获取上传文件的名字
		String orgName = file.getOriginalFilename();
		String imgPath = savePath + orgName;
		try {
			FileOutputStream fos = new FileOutputStream(realPath+imgPath,true);
			FileOutputStream fos1 = new FileOutputStream(basePath+imgPath,true);
			fos.write(file.getBytes());
			fos1.write(file.getBytes());
			fos1.flush();
			fos.flush();
			fos1.close();
			fos.close();
			return imgPath;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String upload(MultipartFile file, HttpServletRequest request) {
		try{
			System.out.println("my upload");
				//使用fileupload组件进行上传
				//获取当前项目路径下的images/upFile路径
				String path = "images/upFlie/";
				System.out.println(path);
				File filePath = new File(path);
				//判断路径是否存在，没有就创建
				if(!filePath.exists()) {
					filePath.mkdirs();
			}
			//获取上传文件名称
			String fileName = file.getOriginalFilename();
			file.transferTo(new File(path, fileName));
			return path+fileName;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	

}
