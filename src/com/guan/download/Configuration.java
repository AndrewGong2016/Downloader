package com.guan.download;

import static com.guan.download.PrintUtil.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configuration {
	public static String CONFIGPATH = "D:\\Pacs\\filepath.txt"; 
	
	private static Logger logger = LogManager.getLogger(Configuration.class);
	
	//test in main method
	public static void main (String[] args) {	
		List<String>  list = getUrls();
		for(String lineString : list) {
			println(lineString);
		}
	}
	
	public static List<String> getUrls() {
		ArrayList<String> paths = new ArrayList<String>();

        BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader(CONFIGPATH));
	        String line = null;        
	        while( (line = bf.readLine()) != null) {
	        	line = createAccessablePath(line);
	            paths.add(line);
	        }
			
		} catch (IOException e) {
			// print exception both in stdout and logger
			e.printStackTrace();
			logger.error("error:",e);
		}
		return paths;
	}
	
	private static String createAccessablePath(String leagcyPathInWindows) {
		return leagcyPathInWindows.replace("\\", "\\\\");
	}
		
}
