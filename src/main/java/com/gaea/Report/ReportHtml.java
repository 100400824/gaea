package com.gaea.Report;

import com.gaea.utls.FileManage;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportHtml {

    String mode1 = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head>\n" +
            "    <title>UI自动化测试报告</title>\n" +
            "<link rel=\"icon\" type=\"image/x-icon\" href=\"http://172.23.5.160:50080/aeplus/favicon.ico\">" +
            "    <meta name=\"generator\" content=\"HTMLTestRunner 0.9.1\">\n" +
            "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
            "\n" +
            "    <link href=\"http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
            "    <script src=\"https://cdn.bootcss.com/echarts/3.8.5/echarts.common.min.js\"></script>\n" +
            "<link data-n-head=\"ssr\" rel=\"icon\" type=\"image/x-icon\" href=\"https://pic.iyingdi.com/yingdi_pc/logo.png\">"+
            "    <!-- <script type=\"text/javascript\" src=\"js/echarts.common.min.js\"></script> -->\n" +
            "\n" +
            "    \n" +
            "<style type=\"text/css\" media=\"screen\">\n" +
            "    body        { font-family: Microsoft YaHei,Consolas,arial,sans-serif; font-size: 80%; }\n" +
            "    table       { font-size: 100%; }\n" +
            "    pre         { white-space: pre-wrap;word-wrap: break-word; }\n" +
            "\n" +
            "    /* -- heading ---------------------------------------------------------------------- */\n" +
            "    h1 {\n" +
            "        font-size: 16pt;\n" +
            "        color: gray;\n" +
            "    }\n" +
            "    .heading {\n" +
            "        margin-top: 0ex;\n" +
            "        margin-bottom: 1ex;\n" +
            "    }\n" +
            "\n" +
            "    .heading .attribute {\n" +
            "        margin-top: 1ex;\n" +
            "        margin-bottom: 0;\n" +
            "    }\n" +
            "\n" +
            "    .heading .description {\n" +
            "        margin-top: 2ex;\n" +
            "        margin-bottom: 3ex;\n" +
            "    }\n" +
            "\n" +
            "    /* -- css div popup ------------------------------------------------------------------------ */\n" +
            "    a.popup_link {\n" +
            "    }\n" +
            "\n" +
            "    a.popup_link:hover {\n" +
            "        color: red;\n" +
            "    }\n" +
            "\n" +
            "    .popup_window {\n" +
            "        display: none;\n" +
            "        position: relative;\n" +
            "        left: 0px;\n" +
            "        top: 0px;\n" +
            "        /*border: solid #627173 1px; */\n" +
            "        padding: 10px;\n" +
            "        /*background-color: #E6E6D6; */\n" +
            "        font-family: \"Lucida Console\", \"Courier New\", Courier, monospace;\n" +
            "        text-align: left;\n" +
            "        font-size: 8pt;\n" +
            "        /* width: 500px;*/\n" +
            "    }\n" +
            "\n" +
            "    }\n" +
            "    /* -- report ------------------------------------------------------------------------ */\n" +
            "    #show_detail_line {\n" +
            "        margin-top: 3ex;\n" +
            "        margin-bottom: 1ex;\n" +
            "    }\n" +
            "    #result_table {\n" +
            "        width: 99%;\n" +
            "    }\n" +
            "    #header_row {\n" +
            "        font-weight: bold;\n" +
            "        color: #303641;\n" +
            "        background-color: #ebebeb;\n" +
            "    }\n" +
            "    #total_row  { font-weight: bold; }\n" +
            "    .passClass  { background-color: #bdedbc; }\n" +
            "    .failClass  { background-color: #ffefa4; }\n" +
            "    .errorClass { background-color: #ffc9c9; }\n" +
            "    .passCase   { color: #6c6; }\n" +
            "    .failCase   { color: #FF6600; font-weight: bold; }\n" +
            "    .errorCase  { color: #c00; font-weight: bold; }\n" +
            "    .hiddenRow  { display: none; }\n" +
            "    .testcase   { margin-left: 2em; }\n" +
            "\n" +
            "\n" +
            "    /* -- ending ---------------------------------------------------------------------- */\n" +
            "    #ending {\n" +
            "    }\n" +
            "\n" +
            "    #div_base {\n" +
            "                position:absolute;\n" +
            "                top:0%;\n" +
            "                left:5%;\n" +
            "                right:5%;\n" +
            "                width: auto;\n" +
            "                height: auto;\n" +
            "                margin: -15px 0 0 0;\n" +
            "    }\n" +
            "</style>\n" +
            "\n" +
            "\n" +
            "</head>\n" +
            "<body>\n" +
            "    <script language=\"javascript\" type=\"text/javascript\"><!--\n" +
            "    output_list = Array();\n" +
            "\n" +
            "    /* level - 0:Summary; 1:Failed; 2:All */\n" +
            "    function showCase(level) {\n" +
            "        trs = document.getElementsByTagName(\"tr\");\n" +
            "        for (var i = 0; i < trs.length; i++) {\n" +
            "            tr = trs[i];\n" +
            "            id = tr.id;\n" +
            "            if (id.substr(0,2) == 'ft') {\n" +
            "                if (level < 1) {\n" +
            "                    tr.className = 'hiddenRow';\n" +
            "                }\n" +
            "                else {\n" +
            "                    tr.className = '';\n" +
            "                }\n" +
            "            }\n" +
            "            if (id.substr(0,2) == 'pt') {\n" +
            "                if (level > 1) {\n" +
            "                    tr.className = '';\n" +
            "                }\n" +
            "                else {\n" +
            "                    tr.className = 'hiddenRow';\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    function showClassDetail(cid, count) {\n" +
            "        var id_list = Array(count);\n" +
            "        var toHide = 1;\n" +
            "        for (var i = 0; i < count; i++) {\n" +
            "            tid0 = 't' + cid.substr(1) + '.' + (i+1);\n" +
            "            tid = 'f' + tid0;\n" +
            "            tr = document.getElementById(tid);\n" +
            "            if (!tr) {\n" +
            "                tid = 'p' + tid0;\n" +
            "                tr = document.getElementById(tid);\n" +
            "            }\n" +
            "            id_list[i] = tid;\n" +
            "            if (tr.className) {\n" +
            "                toHide = 0;\n" +
            "            }\n" +
            "        }\n" +
            "        for (var i = 0; i < count; i++) {\n" +
            "            tid = id_list[i];\n" +
            "            if (toHide) {\n" +
            "                document.getElementById('div_'+tid).style.display = 'none'\n" +
            "                document.getElementById(tid).className = 'hiddenRow';\n" +
            "            }\n" +
            "            else {\n" +
            "                document.getElementById(tid).className = '';\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    function showTestDetail(div_id){\n" +
            "        var details_div = document.getElementById(div_id)\n" +
            "        var displayState = details_div.style.display\n" +
            "        // alert(displayState)\n" +
            "        if (displayState != 'block' ) {\n" +
            "            displayState = 'block'\n" +
            "            details_div.style.display = 'block'\n" +
            "        }\n" +
            "        else {\n" +
            "            details_div.style.display = 'none'\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    function html_escape(s) {\n" +
            "        s = s.replace(/&/g,'&amp;');\n" +
            "        s = s.replace(/</g,'&lt;');\n" +
            "        s = s.replace(/>/g,'&gt;');\n" +
            "        return s;\n" +
            "    }\n" +
            "\n" +
            "    /* obsoleted by detail in <div>\n" +
            "    function showOutput(id, name) {\n" +
            "        var w = window.open(\"\", //url\n" +
            "                        name,\n" +
            "                        \"resizable,scrollbars,status,width=800,height=450\");\n" +
            "        d = w.document;\n" +
            "        d.write(\"<pre>\");\n" +
            "        d.write(html_escape(output_list[id]));\n" +
            "        d.write(\"\\n\");\n" +
            "        d.write(\"<a href='javascript:window.close()'>close</a>\\n\");\n" +
            "        d.write(\"</pre>\\n\");\n" +
            "        d.close();\n" +
            "    }\n" +
            "    */\n" +
            "    --></script>\n" +
            "\n" +
            "    <div id=\"div_base\">\n" +
            "        \n" +
            "    <div class=\"page-header\">\n" +
            "        <h1>UI自动化测试报告</h1>\n" +
            "    <p class=\"attribute\"><strong>报告时间:</strong> nowTime </p>\n" +
//            "<p class=\"attribute\"><strong>运行时长:</strong> 0:01:08.704506</p>\n" +
            "<p class=\"attribute\"><strong>状态:</strong> 通过:" + "passNum" + "  错误:errorNum" +
            "</p>\n" +
            "\n" +
            "    </div>\n" +
            "    <div style=\"float: left;width:50%;\"><p class=\"description\">用例执行情况：</p></div>\n" +
            "    <div id=\"chart\" style=\"width: 50%; height: 400px; float: left; -webkit-tap-highlight-color: transparent; user-select: none; position: relative; background: transparent;\" _echarts_instance_=\"ec_1557026601944\"><div style=\"position: relative; overflow: hidden; width: 857px; height: 400px; padding: 0px; margin: 0px; border-width: 0px;\"><canvas width=\"857\" height=\"400\" data-zr-dom-id=\"zr_0\" style=\"position: absolute; left: 0px; top: 0px; width: 857px; height: 400px; user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0); padding: 0px; margin: 0px; border-width: 0px;\"></canvas></div><div></div></div>\n" +
            "\n" +
            "        \n" +
            "    <div class=\"btn-group btn-group-sm\">\n" +
            "        <button class=\"btn btn-default\" onclick=\"javascript:showCase(0)\">总结</button>\n" +
            "        <button class=\"btn btn-default\" onclick=\"javascript:showCase(1)\">失败</button>\n" +
            "        <button class=\"btn btn-default\" onclick=\"javascript:showCase(2)\">全部</button>\n" +
            "    </div>\n" +
            "    <p></p>\n" +
            "    <table id=\"result_table\" class=\"table table-bordered\">\n" +
            "        <colgroup>\n" +
            "            <col align=\"left\">\n" +
            "            <col align=\"right\">\n" +
            "            <col align=\"right\">\n" +
            "            <col align=\"right\">\n" +
            "            <col align=\"right\">\n" +
            "            <col align=\"right\">\n" +
            "        </colgroup>\n" +
            "        <tbody><tr id=\"header_row\">\n" +
            "            <td colspan=\"2\" align=\"center\">测试套件/测试用例</td>\n" +
            "            <td>总数</td>\n" +
            "            <td>通过</td>\n" +
            "            <td>失败</td>\n" +
            "            <td>错误</td>\n" +
            "            <td>查看</td>\n" +
            "\n" +
            "        </tr>\n" +
            "        \n" +
            "    <tr class=\"passClass\">\n" +
            "        <td colspan=\"2\" align=\"center\">UI自动化测试</td>\n" +
            "        <td>" + "caseNum" + "</td>\n" +
            "        <td>" + "passNum" + "</td>\n" +
            "        <td>0</td>\n" +
            "        <td>errorNum</td>\n" +
            "        <td><a href=\"javascript:showClassDetail('c1'," + "passNum" + ")\">点击下方可查看详情</a></td>\n" +
            "\n" +
            "    </tr>\n" +
            "\n";


    String mode2 = "<tr id=\"" + "idValue0" + "\" class=\"\">\n" +
            "    <td class=\"" + "isSuccess" + "\"><div class=\"testcase\">" + "caseNum1" + "</div></td>\n" +
            "    <td style=\"color:green\" align=\"left\">" + "caseName2" + "</td>\n" +
            "    <td colspan=\"5\" align=\"center\">\n" +
            "\n" +
            "    <!--css div popup start-->\n" +
            "    <a " + "isError" + "class=\"popup_link\" onfocus=\"this.blur();\" href=\"javascript:showTestDetail('div_" + "idValue0" + "')\">\n" +
            "        " + "isPass3" + "</a>\n" +//通过 or 不通过
            "\n" +
            "    <div id=\"div_" + "idValue0" + "\" class=\"popup_window\">\n" +
            "        <pre>" + "caseContent4" +
            "</pre>\n" +
            "    </div>\n" +
            "    <!--css div popup end-->\n" +
            "\n" +
            "    </td>\n" +
            "</tr>";

    String mode3 = "<tr id=\"total_row\">\n" +
            "            <td colspan=\"2\">总计</td>\n" +
            "            <td>" + "caseNum" + "</td>\n" +
            "            <td>" + "passNum" + "</td>\n" +
            "            <td>0</td>\n" +
            "            <td>errorNum</td>\n" +
            "            <td>&nbsp;</td>\n" +
            "\n" +
            "        </tr>\n" +
            "    </tbody></table>\n" +
            "\n" +
            "        <div id=\"ending\">&nbsp;</div>\n" +
            "        \n" +
            "    <script type=\"text/javascript\">\n" +
            "        // 基于准备好的dom，初始化echarts实例\n" +
            "        var myChart = echarts.init(document.getElementById('chart'));\n" +
            "\n" +
            "        // 指定图表的配置项和数据\n" +
            "        var option = {\n" +
            "            title : {\n" +
            "                text: '测试执行情况',\n" +
            "                x:'center'\n" +
            "            },\n" +
            "            tooltip : {\n" +
            "                trigger: 'item',\n" +
            "                formatter: \"{a} <br/>{b} : {c} ({d}%)\"\n" +
            "            },\n" +
            "            color: ['#95b75d', 'grey', '#b64645'],\n" +
            "            legend: {\n" +
            "                orient: 'vertical',\n" +
            "                left: 'left',\n" +
            "                data: ['通过','失败','错误']\n" +
            "            },\n" +
            "            series : [\n" +
            "                {\n" +
            "                    name: '测试执行情况',\n" +
            "                    type: 'pie',\n" +
            "                    radius : '60%',\n" +
            "                    center: ['50%', '60%'],\n" +
            "                    data:[\n" +
            "                        {value:" + "passNum" + ", name:'通过'},\n" +
            "                        {value:0, name:'失败'},\n" +
            "                        {value:errorNum, name:'错误'}\n" +
            "                    ],\n" +
            "                    itemStyle: {\n" +
            "                        emphasis: {\n" +
            "                            shadowBlur: 10,\n" +
            "                            shadowOffsetX: 0,\n" +
            "                            shadowColor: 'rgba(0, 0, 0, 0.5)'\n" +
            "                        }\n" +
            "                    }\n" +
            "                }\n" +
            "            ]\n" +
            "        };\n" +
            "\n" +
            "        // 使用刚指定的配置项和数据显示图表。\n" +
            "        myChart.setOption(option);\n" +
            "    </script>\n" +
            "    \n" +
            "    </div>\n" +
            "\n" +
            "\n" +
            "</body></html>";

    static String rowFlag = "-----";

    static String caseFlag = "------";

    public static void main(String[] args) throws Exception {

        ReportHtml rh = new ReportHtml();

        rh.doHtmlReport(3);

    }

    public int doHtmlReport(int caseNum) throws Exception {

        String caseValue = returnReport();

        String[] caseArray = caseValue.split(caseFlag);

//        int caseNum = UITestCase.caseNum();

        String html2 = "";

        String caseStep = "";

        int passNum = 0;

        try {

            for (int i = 0; i < caseNum; i++) {

                if (i == 0) {
                    caseArray[i] = (caseArray[i].split("。1"))[0].replace("-----","")+caseArray[i];
                }

                String[] htmlContent = caseArray[i].split(rowFlag);

                caseStep = htmlContent[1].replace("。", "。\n");

                if (htmlContent[2].equals("true")) {

                    html2 = html2 + mode2.replace("caseNum1", "" + (i + 1)).replace("caseName2", htmlContent[0]).replace("isPass3", "通过（点击查看详细执行步骤）").
                            replace("caseContent4", caseStep).replace("idValue0", "pt" +
                            (i + 1)).replace("isSuccess", "none").replace("isError", "");

                    passNum++;

                } else {

                    html2 = html2 + mode2.replace("caseNum1", "" + (i + 1)).replace("caseName2", htmlContent[0]).replace("isPass3", "错误（点击查看详细执行步骤）").
                            replace("caseContent4", caseStep).replace("idValue0", "ft" +
                            (i + 1)).replace("isSuccess", "errorCase").replace("isError", "style=\"color:#c00\"");
                }
            }
        } catch (Exception e) {
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        int errorNum = caseNum - passNum;

        mode1 = mode1.replace("passNum", "" + passNum).replace("caseNum", "" + caseNum)
                .replace("errorNum", "" + "" + errorNum).replace("nowTime", df.format(new Date()));

        mode3 = mode3.replace("passNum", "" + passNum).replace("caseNum", "" + caseNum)
                .replace("errorNum", "" + "" + errorNum);

        String kk = mode1 + html2 + mode3;

        ReportHtml.saveFile(FileManage.zipFilePath, kk, "UItestReport.html");

        return passNum;
    }

    public static String returnReport() {

        String reportPath = FileManage.reportPath;

        String reportTxt = "";

        String[] reportArray = ManageToolReport.readFiles(reportPath);

        String isSucces = "true";

        String caseContent = "";

        String caseName = "";

        String caseValue = "";

        for (String dd : reportArray) {

            if (!dd.contains("skipped")) {

                if (dd.equals("")) {

                    reportTxt = reportTxt + caseFlag;

                } else {

                    reportTxt = reportTxt + dd + rowFlag;
                }
            }
        }

        reportArray = reportTxt.split(caseFlag);

        int caseNum = reportArray.length;

        String[] caseArray;

        for (int i = 1; i < caseNum - 1; i++) {

            isSucces = "true";

            caseContent = "";

            caseArray = reportArray[i].split(rowFlag);

            if (caseArray[1].contains("场景")) {

                caseName = caseArray[1];
            }

            for (String dd : caseArray) {

                if (dd.contains("实际值->") || dd.contains("失败") || dd.contains("错误") || dd.contains("Sheet") || dd.contains("报错原因如下") || dd.contains("Exception")) {

                    caseContent = caseContent + "<p style=\"color:#c00\">" + dd + "</p>";

                    isSucces = "false";

                } else {

                    caseContent = caseContent + dd;
                }

                if (dd.contains("失败") || dd.contains("错误") || dd.contains("Sheet") || dd.contains("报错原因如下") || dd.contains("Exception")) {

                    isSucces = "false";

                }
            }

            caseValue = caseValue + caseName + rowFlag + caseContent + rowFlag + isSucces + caseFlag;

//            System.out.println(caseValue);

        }

        return caseValue.substring(0, caseValue.length());
    }


    //将数据保存成文件
    private static void saveFile(String filePath, String jsonStr, String fileName) throws Exception {

        String xx = File.separator;

        File f = new File(filePath + xx + fileName);

        f.delete();

        FileWriter fw = new FileWriter(f, true);

        PrintWriter pw = new PrintWriter(fw);

        pw.println(jsonStr);

        fw.flush();

        pw.close();

        fw.close();

    }

}
