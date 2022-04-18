package com.gaea.utls.publicTool;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
public class ImgChange {
    private Image img;
    private final static int WIDTH = 400;
    private final static int HEIGHT = 400;

    public static void main(String[] args) {
        try {
            LinkedHashMap<String,String> files = getFiles("C:\\Users\\Administrator\\Desktop\\图片\\一番赏\\图片\\D");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date beginDate = new Date();
            System.out.println("开始："+sdf.format(beginDate));
            for (String fileName : files.keySet()) {
                System.out.println(fileName);
                InputStream is = new FileInputStream(new File(files.get(fileName)));
                OutputStream os = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\图片\\一番赏\\图片\\D\\400\\"+fileName));
                resizeImage(is, os, 400, "jpg");
            }
            Date endDate = new Date();
            System.out.println("结束："+sdf.format(endDate));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 改变图片的大小到宽为size，然后高随着宽等比例变化
     * @param is 上传的图片的输入流
     * @param os 改变了图片的大小后，把图片的流输出到目标OutputStream
     * @param size 新图片的宽
     * @param format 新图片的格式
     * @throws IOException
     */
    public static OutputStream resizeImage(InputStream is, OutputStream os, int size, String format) throws IOException {
        BufferedImage prevImage = ImageIO.read(is);
        int newWidth = 400;
        int newHeight = 400;
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
        ImageIO.write(image, format, os);
        os.flush();
        is.close();
        os.close();
        //ByteArrayOutputStream b = (ByteArrayOutputStream) os;
        return os;
    }

    /**
     * 读取某个目录下所有文件、文件夹
     * @param path
     * @return LinkedHashMap<String,String>
     */
    public static LinkedHashMap<String,String> getFiles(String path) {
        LinkedHashMap<String,String> files = new LinkedHashMap<String,String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (!tempList[i].isDirectory()) {
                files.put(tempList[i].getName(),tempList[i].getPath());
            }
        }
        return files;
    }


}
