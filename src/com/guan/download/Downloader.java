package com.guan.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Downloader {
	static final String OUTPUT_DIR = "D:\\PACs";
	static final String URL_PROTOCAL = "file:";
	
	private static Logger logger = LogManager.getLogger(Downloader.class);
	
	public static void main(String[] args) {
		logger.info("Hello,Welcome to my downloader: Guan");
		List<String> fileStrings = Configuration.getUrls();

		for (String file : fileStrings) {
			logger.debug("File path :"+file);
			URL url = buildUrl(URL_PROTOCAL + file);
			if (url != null ) {
				downloadFile(url,OUTPUT_DIR + file);
			}
		}

	}
	
	public static URL buildUrl(String path) {
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

	public static boolean downloadFile(URL uri,String savePath) {
	    FileOutputStream fs = null;
		try (InputStream bufferedStream = new BufferedInputStream(uri.openConnection().getInputStream());){
		    File outPutFile = new File(savePath);
		    createOutPutDirs(savePath);
		    if(!outPutFile.exists()) {
		    	outPutFile.createNewFile();
		    } else {
		    	logger.info("File Existed, Exit download...");
		    	return false;
		    }
		    System.out.println("outPut path:"+outPutFile.getAbsolutePath());
		    fs = new FileOutputStream(outPutFile);
		    byte[] data = new byte[1024*10];
		    
		    int byteread = 0;
	        int bytesum = 0;

	        while ((byteread = bufferedStream.read(data)) != -1) {
	            bytesum += byteread;
	            logger.info("sum (byte):"+bytesum);
	            fs.write(data, 0, byteread);
	        }
	        logger.debug("outPut path:"+outPutFile.getAbsolutePath());
	        
	        return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {	
			if(fs !=null) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public static boolean createOutPutDirs(String filePath) {
		String path = filePath; //所创建文件目录
		File f = new File(path);
		
		boolean isDir = path.endsWith("\\\\");
		String pathString = f.getAbsolutePath();	
		if( isDir && !f.exists()){
			logger.debug("Make the direcotory: "+pathString);
			return f.mkdirs(); //创建目录
		} else {
			int  lastIndex = pathString.lastIndexOf("\\");
			CharSequence dirPath= pathString.subSequence(0, lastIndex);
			File dir = new File(dirPath.toString());
			if(!dir.exists()) {
				logger.debug("Make the direcotory: "+dir.getAbsolutePath());
				return dir.mkdirs();
			} else {
				logger.debug("Local directory already exists ["+dir.getAbsolutePath()+"] ");
			}
		}
		return false;
	}
}
