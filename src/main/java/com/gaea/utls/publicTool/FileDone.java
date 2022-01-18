package com.gaea.utls.publicTool;

import java.io.*;

public class FileDone {

    public static void main(String[] args) throws Exception {

    }

    //获取文件夹下的所有文件
    public static File[] getFiles(String path) {
        File file = new File(path);

        File[] array = file.listFiles();

        return array;
    }

    //读取制定文件的内容
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
