package com.gaea.utls.publicTool;

import com.gaea.utls.publicTool.FileDone;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ImgDone {


    public static void main(String[] args) throws Exception {

        String xpath = "C:\\Users\\Administrator\\Desktop\\新建文件夹 (2)\\svg";
        File[] fileArr = FileDone.getFiles(xpath);
        String svgPath, pngPath;
        for (File file : fileArr) {
            svgPath = file.getPath();
            pngPath = svgPath.replace("新建文件夹 (2)\\svg", "新建文件夹 (2)\\png1").replace(".svg", ".png");
            String value = FileDone.readToString(svgPath);
            convertToPng(value, pngPath);
        }

    }

    //SVG转PNG
    public static void convertToPng(String svgCode, String pngFilePath) throws Exception {

        File file = new File(pngFilePath);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            convertToPng(svgCode, outputStream);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void convertToPng(String svgCode, OutputStream outputStream) throws Exception {
        try {
            byte[] bytes = svgCode.getBytes(StandardCharsets.UTF_8);
            PNGTranscoder t = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(new ByteArrayInputStream(bytes));
            TranscoderOutput output = new TranscoderOutput(outputStream);
            t.transcode(input, output);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
