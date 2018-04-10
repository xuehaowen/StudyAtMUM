package edu.mum.cs.mpp.finalexam.part3q2b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Re-implement the code in this class 
 * using try-with-resources construct
 */
public class MyClass {
	
	public void handleFile(File f) {
		FileReader fileReader = null;
		BufferedReader buf = null;
		try{ 
			fileReader = new FileReader(f);
			buf = new BufferedReader(fileReader);
			String line = buf.readLine();
			System.out.println("Line from file: " + line);
		} catch(IOException e) {
			//LOG.warning("1st IOException: " 
			//	+ e.getMessage());
		} finally {
			try {
				if(buf != null) buf.close();
				if(fileReader != null) fileReader.close();
				throw new IOException("Caught 2nd IOException: error closing readers");
			} catch(IOException e) {
				//LOG.warning(e.getMessage());
			}
			
		} 
		
		
	}
	
	public static void main(String[] args) {
		MyClass cl = new MyClass();
		cl.handleFile(new File("textt"));
	}
	
	
}
