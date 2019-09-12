package com.guan.download;

import static com.guan.download.PrintUtil.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Configuration {
	public static String CONFIGPATH = "D:\\Pacs\\filepath.txt"; 
	
	public static void main (String[] args) {	
		List<String>  list = getConfigLines();
		for(String lineString : list) {
			println(lineString);
		}
	}
	
	public static List<String> getConfigLines() {
		ArrayList<String> paths = new ArrayList<String>();

        BufferedReader bf;
		try {
			bf = new BufferedReader(new FileReader(CONFIGPATH));
	        String line = null;        
	        while( (line = bf.readLine()) != null) {
	            paths.add(line);
	        }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return paths.size() == 0 ? null : paths;
	}
		
}
