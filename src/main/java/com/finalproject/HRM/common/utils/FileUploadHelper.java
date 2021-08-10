package com.finalproject.HRM.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
//	private final String LOCATION = "/home/rukesh/Documents/spring/HRM/src/main/resources/static/image/upload/";
	
//	@Value("${image.uploadPath}")
	private String LOCATION= null;
	
	public FileUploadHelper() throws IOException
	{

		/*
		 * code to create new folder if folder doesn't already exist
		 */
		LOCATION = new ClassPathResource("static/image/upload/").getFile().getAbsolutePath();
	}
	
	public String upload(MultipartFile file) throws Exception
	{
		/*
		 * replace image original name with something unique
		 */
		String path = LOCATION+"/"+file.getOriginalFilename(); 
//		String path = LOCATION.concat(file.getOriginalFilename());
		try(InputStream inputStream = file.getInputStream())
		{
			Files.copy(inputStream, Paths.get(path),
					StandardCopyOption.REPLACE_EXISTING);
		}
		catch (Exception e) {
			throw new Exception("fail to upload file "+e);
		}
//		return path;
		System.out.println("File stored inside: " + LOCATION);
		return file.getOriginalFilename();
	}
}
