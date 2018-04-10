package lesson10.labs.prob2;

import java.io.*;
import java.util.logging.Logger;

public class NewFileIO {
    private static final Logger LOG = Logger.getLogger(NewFileIO.class.getName());
    public final static String FILE_LOCATION = "src/lesson10/labs/prob3/output.txt";
    public final static String TO_PRINT = "This is the string to print to file.";

    void writeText(String filename, String text) {
//        String basePathOfClass = getClass()
//                .getProtectionDomain().getCodeSource().getLocation().getFile();
       try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
           bw.write(text);
       }catch (Exception e){
           LOG.warning("IOException attempting to write a file: " + e.getMessage());
       }
    }

    public static void main(String[] args) {
        NewFileIO newFileIO = new NewFileIO();
        newFileIO.writeText(FILE_LOCATION, TO_PRINT);

    }
}
