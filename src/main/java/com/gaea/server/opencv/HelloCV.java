package com.gaea.server.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;


public class HelloCV {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args){
        Mat mat = Imgcodecs.imread("C:\\Users\\Administrator\\Desktop\\opencv\\1122.png");
        System.out.println(mat);
    }
}
