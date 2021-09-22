package com.gaea.utls;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * <p>
 * Title: 使用javamail发送邮件
 * </p>
 */
public class Mail {

    String to = "";// 收件人
    String cc = "";//抄送人
    String from = "";// 发件人
    String host = "";// smtp主机
    String username = "";
    String password = "";
    String filename = "";// 附件文件名
    String subject = "";// 邮件主题
    String content = "";// 邮件正文
    Vector<String> file = new Vector<String>();// 附件文件集合

    public static void main(String[] args) throws GeneralSecurityException {
        Mail sendmail = new Mail();

        sendmail.setHost("smtp.163.com");
        sendmail.setUserName("zpwtest001@163.com");
        sendmail.setPassWord("123123aa");
        sendmail.setTo("100400824@qq.com,zpwtest001@163.com");
        sendmail.setFrom("zpwtest001@163.com");
        sendmail.setSubject("you have a problem");
        sendmail.setContent("发现问题，请相关人员进行验证，附件中含有错误日志。");
        // Mail sendmail = new
        // Mail("dujiang@sricnet.com","du_jiang@sohu.com","smtp.sohu.com","du_jiang","31415926","你好","胃，你好吗？");
        sendmail.attachfile("D:\\work\\talkingData\\1marketing\\report\\UIreport.txt");
//		sendmail.attachfile("d:\\jieguo.txt");

        System.out.println(sendmail.sendMail());

    }

    public static void toEmail(String subject, String content, String sendToEmail) throws GeneralSecurityException {

        Mail sendmail = new Mail();

        sendmail.setHost("smtp.163.com");
        sendmail.setUserName("zpwtest001@163.com");
        sendmail.setPassWord("123123aa");

        //String sendCC = "zhangpeiwei@star-media.cn";

        sendmail.setTo(sendToEmail);
//        sendmail.setCC(sendCC);
        sendmail.setFrom("zpwtest001@163.com");
        sendmail.setSubject(subject);
        sendmail.setContent(content);
//        sendmail.attachfile(FileManage.reportPath);
//        sendmail.attachfile(FileManage.imgFilePath + FileManage.xx + "imgZIP.zip");
//        sendmail.attachfile(FileManage.zipFilePath + FileManage.xx + "UItestReport.html");
//		sendmail.attachfile(FileManage.zipFilePath + FileManage.xx + FileManage.pathValue("zipFileName") + ".zip");
        System.out.println(sendmail.sendMail());
    }


    public Mail() {
    }

    /**
     * <br>
     * 方法说明：构造器，提供直接的参数传入 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public Mail(String to, String from, String smtpServer, String username, String password, String subject,
                String content) {
        this.to = to;
        this.from = from;
        this.host = smtpServer;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }

    /**
     * <br>
     * 方法说明：设置邮件服务器地址 <br>
     * 输入参数：String host 邮件服务器地址名称 <br>
     * 返回类型：
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * <br>
     * 方法说明：设置登录服务器校验密码 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setPassWord(String pwd) {
        this.password = pwd;
    }

    /**
     * <br>
     * 方法说明：设置登录服务器校验用户 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setUserName(String usn) {
        this.username = usn;
    }

    /**
     * <br>
     * 方法说明：设置邮件发送目的邮箱 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setTo(String to) {
        this.to = to;
    }

    public void setCC(String cc) {
        this.cc = cc;
    }

    /**
     * <br>
     * 方法说明：设置邮件发送源邮箱 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * <br>
     * 方法说明：设置邮件主题 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * <br>
     * 方法说明：设置邮件内容 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * <br>
     * 方法说明：把主题转换为中文 <br>
     * 输入参数：String strText <br>
     * 返回类型：
     */
    public String transferChinese(String strText) {
        try {
            strText = MimeUtility.encodeText(new String(strText.getBytes(), "utf-8"), "utf-8", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strText;
    }

    /**
     * <br>
     * 方法说明：往附件组合中添加附件 <br>
     * 输入参数： <br>
     * 返回类型：
     */
    public void attachfile(String fname) {
        file.addElement(fname);
    }

    /**
     * <br>
     * 方法说明：发送邮件 <br>
     * 输入参数： <br>
     * 返回类型：boolean 成功为true，反之为false
     */
    public boolean sendMail() throws GeneralSecurityException {


        // 构造mail session
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");

        //新增改动
    /*    props.put("mail.smtp.socketFactory.port","587");
        props.put("mail.smtp.socketFactory.fallback","false");*/


      /*  MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.socketFactory",sf);*/


        Session session = Session.getDefaultInstance(props, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        // Session session = Session.getDefaultInstance(props);
        // Session session = Session.getDefaultInstance(props, null);

        try {
            // 构造MimeMessage 并设定基本的值
            MimeMessage msg = new MimeMessage(session);
            // MimeMessage msg = new MimeMessage();
            msg.setFrom(new InternetAddress(from));

            // msg.addRecipients(Message.RecipientType.TO, address);
            // //这个只能是给一个人发送email
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            subject = transferChinese(subject);
            // msg.setSubject(subject);
            msg.setSubject(subject, "text/html;charset=UTF-8");

            // 构造Multipart
            Multipart mp = new MimeMultipart();

            // 向Multipart添加正文
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setContent(content, "text/html;charset=gb2312");

            // 向MimeMessage添加（Multipart代表正文）
            mp.addBodyPart(mbpContent);

            // 向Multipart添加附件
            Enumeration<String> efile = file.elements();
            while (efile.hasMoreElements()) {

                MimeBodyPart mbpFile = new MimeBodyPart();
                filename = efile.nextElement().toString();
                FileDataSource fds = new FileDataSource(filename);
                mbpFile.setDataHandler(new DataHandler(fds));
                // <span style="color: #ff0000;">//这个方法可以解决附件乱码问题。</span>
                String filename = new String(fds.getName().getBytes(), "ISO-8859-1");

                mbpFile.setFileName(filename);
                // 向MimeMessage添加（Multipart代表附件）
                mp.addBodyPart(mbpFile);

            }

            file.removeAllElements();
            // 向Multipart添加MimeMessage
            msg.setContent(mp);
            msg.setSentDate(new Date());
            msg.saveChanges();
            // 发送邮件

            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception mex) {
            mex.printStackTrace();
            // Exception ex = null;
            // if ((ex = mex.getNextException()) != null) {
            // ex.printStackTrace();
            // }
            return false;
        }
        return true;
    }

}