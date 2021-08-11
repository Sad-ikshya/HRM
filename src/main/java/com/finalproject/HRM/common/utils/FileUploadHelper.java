package com.finalproject.HRM.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
//	private final String LOCATION = "/home/rukesh/Documents/spring/HRM/src/main/resources/static/image/upload/";
	
	@Value("${image.uploadPath}")
	private String LOCATION;
//	private String LOCATION= null;
	
	public FileUploadHelper() throws IOException
	{
		
		/*
		 * code to create new folder if folder doesn't already exist
		 */	    
	    
//		LOCATION = new ClassPathResource("static/image/upload/").getFile().getAbsolutePath();
		
		System.out.println(LOCATION);
		
		File directory = new File(LOCATION);
	    if (! directory.exists()){
//	        directory.mkdir();
	        directory.mkdirs();
	    }
	}
	
	public String upload(MultipartFile file) throws Exception
	{
		/*
		 * replace image original name with something unique
		 */
		String uniqueName = generateUniqueName(file.getOriginalFilename());
//		String path = LOCATION+"/"+file.getOriginalFilename(); 
		
		String path = LOCATION+"/"+uniqueName;

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
		return uniqueName;
	}
	
	public String generateUniqueName(String filename)
	{
		String name[] = filename.split("\\.");
		
		String newName = name[0]+(new Date().getTime() / 1000);
		byte[] bytesOfString;
		byte[] encoded = null;
		try {
			
			bytesOfString = newName.getBytes("UTF-8");

			MessageDigest md = MessageDigest.getInstance("MD5");
			encoded = md.digest(bytesOfString);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String encodedName = encoded + "." + name[1];
		
		System.out.println(filename);
		System.out.println("newName : "+newName);
		System.out.println("encoded : "+ encodedName);
		return encodedName;
	}
}
