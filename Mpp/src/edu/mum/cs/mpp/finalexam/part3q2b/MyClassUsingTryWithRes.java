package edu.mum.cs.mpp.finalexam.part3q2b;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MyClassUsingTryWithRes {

	/* Implement your code here */
    public void handleFile(File f) {
        try(BufferedReader buf = new BufferedReader(new FileReader(f))){
            String line = buf.readLine();
            System.out.println("Line from file: " + line);
        }catch(IOException e) {
            //LOG.warning("1st IOException: "
            //	+ e.getMessage());
        }
    }

    public static void main(String[] args) {
        MyClassUsingTryWithRes cl = new MyClassUsingTryWithRes();
        cl.handleFile(new File("textt"));
    }
}
