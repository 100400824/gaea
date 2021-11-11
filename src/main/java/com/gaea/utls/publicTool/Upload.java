package com.gaea.utls.publicTool;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Upload {

    public static Object upload(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
       /* long size = file.getSize();
        System.out.println(size);
        // 1kb == 1024b
        // 限制文件大小 10兆  10485760   43054875springboot
        if(size >= 104857600){
            return "文件太大了，不允许上传";
        }*/

        InputStream in = new ByteArrayInputStream(bytes);
        FileOutputStream fos = new FileOutputStream("D://"+file.getOriginalFilename());
        byte[] b = new byte[1024];
        int nRead = 0;
        while ((nRead = in.read(b)) != -1) {
            fos.write(b, 0, nRead);
        }
        fos.flush();
        fos.close();
        in.close();
        System.out.println("上传成功");
        return "上传成功";
    }

}
