package com.example.amirausfelbaradei.gps2;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class FileUtil {
    public void writeInFile(String filename,String content,String filePath)
    {
        try{
            // Create file
            File destDir = new File(filePath);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            File file=new File(destDir,filename);
            FileWriter fstream = new FileWriter(file,true);

            BufferedWriter out = new BufferedWriter(fstream);
            out.write(content);
            out.write("\r\n");
            //Close the output stream
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
