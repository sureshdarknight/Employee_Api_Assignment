package api.axiom.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtilities {
	public static String getPropertyValue(String hostpath, String key) {
		InputStream file = null;
		Properties prop = new Properties();

		try {
			file = new FileInputStream(hostpath);
			prop.load(file);
			file.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return prop.getProperty(key);
	}
	
	public static FileInputStream loadFile(String filepath) {
		FileInputStream file=null;
		try {
			file=new FileInputStream(new File(filepath));
						
		} catch (FileNotFoundException e) {
		
			e.printStackTrace();
		}
		return file;
	}
	
	
	

}
