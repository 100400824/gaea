package com.gaea.server.opencv;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;
import static org.opencv.imgproc.Imgproc.*;


public class HelloCV {

    public static void main(String[] args) throws Exception {
        testOpencv();
    }

    public static void test1(){


    }

    public static void testOpencv() throws Exception {
        // 解决awt报错问题
        System.setProperty("java.awt.headless", "false");
//        System.out.println(System.getProperty("java.library.path"));
        // 加载动态库
        URL url = ClassLoader.getSystemResource("lib/opencv/opencv_java451.dll");
        System.load(url.getPath());
        // 读取图像
        Mat image = imread("C:\\Users\\Administrator\\Desktop\\opencv\\13.png");
        if (image.empty()) {
            throw new Exception("image is empty");
        }
        imshow("Original Image", image);

        // 创建输出单通道图像
        Mat grayImage = new Mat(image.rows(), image.cols(), CvType.CV_8SC1);
        // 进行图像色彩空间转换
        Imgproc.cvtColor(image, grayImage, COLOR_RGBA2GRAY);

        imshow("Processed Image", grayImage);
        imwrite("C:\\Users\\Administrator\\Desktop\\opencv\\hello.png", grayImage);

        Mat mat1 = Mat.zeros(new Size(8,8),CvType.CV_8UC1);
        System.out.println(mat1);


        HighGui.waitKey();
    }
}