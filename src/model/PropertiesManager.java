package model;
//@author Jef Uytterhoeven 2018
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
	
	Properties prop = new Properties();
    InputStream input = null;
    FileOutputStream out = null;
    
    public PropertiesManager() {

    }
    
    public String getPropertieOf(String propname) {
    	try {
    		input = new FileInputStream("config.properties");
    		prop.load(input);
    		System.out.println(prop.getProperty(propname));
    		return prop.getProperty(propname);
    	}catch  (IOException  ex) {
   		 ex.printStackTrace();
		}finally {
	        if (input != null) {
	            try {
	                input.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}
    	return "not loaded";
    }
    
    public void setPropertie(String propname, String propvalue) {
    	
    	try {
    		Properties prop2 = new Properties();
    		input = new FileInputStream("config.properties");
    		prop2.load(input);
    		
    		input.close();
    		
    		out = new FileOutputStream("config.properties");
    		prop.setProperty("evaluationmode", prop2.getProperty("evaluationmode"));
    		prop.setProperty("testdone", prop2.getProperty("testdone"));
    		prop.setProperty(propname, propvalue);
    		prop.store(out, null);
    		
    	}catch  (IOException  ex) {
   		 ex.printStackTrace();
		}finally {
	        if (input != null) {
	            try {
	                input.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        if (out != null) {
	            try {
	                out.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}
    }
}
