package com.dtr.oas.util;

import java.io.*;

/**
 * Created by safayat on 12/12/15.
 */
public class FileManager {

    public void writeFile(String fileName, String data){
        try {
            Writer writer = null;

            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(fileName), "utf-8"));
                writer.write(data);
            } catch (IOException ex) {
                // report

            } finally {
                try {writer.close();} catch (Exception ex) {
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String readFile(String fileName){
        String textFile = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine = "";


            while ((sCurrentLine = br.readLine()) != null) {
//				System.out.println(sCurrentLine);
                textFile =  textFile + sCurrentLine+"\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return textFile;

    }

    public void mkdir(String dirName){
        try {
            File file = new File(dirName);
            if(!file.exists()){
                file.mkdir();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void modifyFile(String fileName,String temporaryFileName, String fileContent){
        try {

            File tmpFile = new File(temporaryFileName);
            File oldFile = new File(fileName);
            writeFile(temporaryFileName,fileContent);
            oldFile.delete();
            tmpFile.renameTo(oldFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        String dir = "/sss/sssd/f/f/r/s.js";
        System.out.println(dir.substring(0,dir.lastIndexOf("/")));
    }

}
