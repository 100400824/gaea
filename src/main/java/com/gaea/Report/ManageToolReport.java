package com.gaea.Report;

import com.gaea.utls.FileManage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;

public class ManageToolReport {


    public static void main(String[] args) {

        System.out.println(returnReport());
    }


    public static String returnReport() {

        String reportStr = "当前自动化测试环境为  ->  http://172.23.5.160:50080";

        String reportPath = FileManage.reportPath;

        String reportTxt = "";

        String[] reportArray = readFiles(reportPath);

        String rowFlag = "-----";

        String caseFlag = "------";

        String hh1 = "<br>";

        String hh2 = "</br>";

        for (String dd : reportArray) {

            if (dd.equals("")) {

                reportTxt = reportTxt + caseFlag;

            } else {

                reportTxt = reportTxt + dd + rowFlag;
            }
        }

        reportArray = reportTxt.split(caseFlag);

        int caseNum = reportArray.length;

        String[] caseArray;

        for (int i = 0; i < caseNum; i++) {

            caseArray = reportArray[i].split(rowFlag);

            if (i != 0) {

                try {
                    if (caseArray[1].contains("当日新增。")) {

                        String kk = caseArray[1].replace("当日新增。", "包含断言如下：");

                        reportStr = reportStr + hh1 + kk + hh2;

                    }

                } catch (Exception e) {


                }
            }

            int assertNum = 1;

            for (String dd : caseArray) {

                if (dd.contains("断言")) {

                    try {

                        String[] assertArray = dd.split("、");

                        reportStr = reportStr + hh1 + "" + assertNum + "、" + assertArray[1] + hh2;

                        assertNum = assertNum + 1;

                    } catch (Exception e) {

                        reportStr = reportStr + hh1 + "<font color=\"red\">" + "" + (assertNum + "、") + dd + "</font>" + hh2;
                    }

//                    System.out.println( dd);

                }

                if (dd.contains("Sheet") || dd.contains("报错原因如下") || dd.contains("Exception")) {

                    reportStr = reportStr + hh1 + "<font color=\"red\">" + dd + "</font>" + hh2;

//                    System.out.println( dd);

                }
            }

            reportStr = hh1 + reportStr + hh2;

//            System.out.println();

        }

        return reportStr.replace("<br><br>", "");
    }

    //读取txt中的内容
    public static String getTXT(String filePath) {

        String txtStr = "";

        String[] txtArray = readFiles(filePath);

        for (String dd : txtArray) {

            txtStr = txtStr + dd;
        }

        return txtStr;
    }


    //将txt文档中内容按行存放到数组中等待调用
    public static String[] readFiles(String filePath) {

        int lineNum = fileLine(filePath);

        String[] fileValue = new String[lineNum];

        int lineNumIndex = 0;

        try {

            File file = new File(filePath);

            if (file.isFile() && file.exists()) {

                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");

                BufferedReader br = new BufferedReader(isr);

                String lineTxt;

                while ((lineTxt = br.readLine()) != null) {

                    fileValue[lineNumIndex] = lineTxt;

                    lineNumIndex++;
                }

                br.close();

            } else {

                System.out.println("文件不存在!");
            }
        } catch (Exception e) {

            System.out.println("文件读取错误!");
        }

        return fileValue;
    }


    //计算txt中总行数
    private static int fileLine(String filePath) {

        int linenumber = 0;

        try {

            File file = new File(filePath);

            if (file.exists()) {

                FileReader fr = new FileReader(file);

                LineNumberReader lnr = new LineNumberReader(fr);

                while (lnr.readLine() != null) {

                    linenumber++;
                }

                lnr.close();

            } else {

                System.out.println("文件不存在!");
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return linenumber;
    }

    public static void screentShot(TakesScreenshot drivername, String filename) {

        File scrFile = drivername.getScreenshotAs(OutputType.FILE);

        try {

            FileUtils.copyFile(scrFile, new File(FileManage.screenShot + FileManage.xx + filename.replace("/","") + ".png"));

        } catch (IOException e) {

            // TODO Auto-generated catch block
            System.out.println("Can't save screenshot");
            e.printStackTrace();
        }
    }


}
