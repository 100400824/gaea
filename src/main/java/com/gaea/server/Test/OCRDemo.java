package com.gaea.server.Test;

import net.sourceforge.tess4j.ITesseract;

import net.sourceforge.tess4j.Tesseract;

import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**

 * 类说明 ：图片转文字功能

 */

public class OCRDemo {

    public static void main(String[] args)throws TesseractException {

        ITesseract instance =new Tesseract();
        //如果未将tessdata放在根目录下需要指定绝对路径在·
//        instance.setDatapath("D:\\识别\\tessdata");
        //如果需要识别英文之外的语种，需要指定识别语种，并且需要将对应的语言包放进项目中
        instance.setLanguage("chi_sim");

        // 指定识别图片
        File imgDir =new File("D:\\识别\\img\\9.png");
        long startTime = System.currentTimeMillis();
        String ocrResult = instance.doOCR(imgDir);

        // 输出识别结果
        System.out.println("OCR Result: \n" + ocrResult +"\n 耗时：" + (System.currentTimeMillis() - startTime) +"ms");
    }

}

