package edu.vinaenter.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import edu.vinaenter.constants.GlobalConstant;

public class FileUtil {

	// create method to upload file
	public static String upload(MultipartFile file, HttpServletRequest request) {
		if(file != null) {
			
		}
		if(!GlobalConstant.EMPTY.equals( file.getOriginalFilename())) {
			//String userDir = System.getProperty("user.dir");
			
			String userDir = request.getServletContext().getRealPath("")+"WEB-INF/resources";
			//String userDir = System.getenv(GlobalConstant.ENV_PATH_PROJECT); // biến môi trường
			//System.out.println(userDir);
			String dirPath = userDir + File.separator + GlobalConstant.DIR_UPLOAD;
			
			File saveDir = new File(dirPath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			String fileName = rename(file.getOriginalFilename());
			String filePath = dirPath + File.separator + fileName;
			System.out.println(filePath);
			try {
				file.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			return fileName;
		}
		return GlobalConstant.EMPTY;
	}

	public static String rename(String fileName) {
		if (!GlobalConstant.EMPTY.equals(fileName)) {
			StringBuilder sb = new StringBuilder(); // nối chuỗi thế dấu +
			sb.append(FilenameUtils.getBaseName(fileName)).append("-").append(System.nanoTime()).append(".")
					.append(FilenameUtils.getExtension(fileName));
			return sb.toString();
		}
		return GlobalConstant.EMPTY;
	}
	public static String getRootDir() {

		return GlobalConstant.ENV_PATH_PROJECT;
	}
	public static String reUpload(MultipartFile file,String fileNameOld, HttpServletRequest request) {
		if(file != null) {
			
		}
		if(!GlobalConstant.EMPTY.equals( file.getOriginalFilename())) {
			if(file.getOriginalFilename().equals(fileNameOld)) {
				return fileNameOld;
			}
			//String userDir = System.getProperty("user.dir");
			
			String userDir = request.getServletContext().getRealPath("")+"WEB-INF/resources";
			//String userDir = System.getenv(GlobalConstant.ENV_PATH_PROJECT); // biến môi trường
			//System.out.println(userDir);
			String dirPath = userDir + File.separator + GlobalConstant.DIR_UPLOAD;
			
			File saveDir = new File(dirPath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			String fileName = rename(file.getOriginalFilename());
			String filePath = dirPath + File.separator + fileName;
			String oldFilePath = dirPath + File.separator + fileNameOld;
			
			try {
				
				File oldFile = new File(oldFilePath);
		        if(oldFile.delete()){
			             System.out.println(oldFile.getName() + " is deleted!");
			          }else{
			             System.out.println("Delete failed: File didn't delete");
			           }
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			try {
				file.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			return fileName;
		}
		return fileNameOld;
	}
}
