package com.gaea.utls.publicTool;

import java.io.*;
/*
* 1.向文件写入指定内容
* 2.获取文件夹下的所有文件名称或地址
* 3.读取制定文件的内容
* 4.清空指定文件
* */
public class FileDone {

    public static void main(String[] args) throws IOException {
        String xpath = "C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)\\svg";
        System.out.println(getFiles(xpath)[2].getName());

    }

    //向文件写入指定内容
    public static void write(String path, String value) throws IOException {
        String newline = "\r\n";
        //将写入转化为流的形式
        FileWriter fw = new FileWriter(path, true);
        fw.write(value + newline);//windows中的换行为\r\n    unix下为\r。 
        fw.close();
    }

    //获取文件夹下的所有文件名称或地址
    public static File[] getFiles(String path) {
        File file = new File(path);
        return file.listFiles();
    }

    //获取指定文件内容
    public static String getFileValue(String filePath) throws Exception {

        File file = new File(filePath);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();

        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    //清空指定文件
    public static void clearFile(String path) throws Exception {
        File log = new File(path);
        FileWriter fileWriter =new FileWriter(log);
        fileWriter.write("");
        fileWriter.flush();
        fileWriter.close();
    }

}
