package com.gaea.server.LFSAPI;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.security.MessageDigest;
import java.util.*;

//验签生成工具
public class BuildSign {

    public static void main(String[] args) throws Exception {


        ArrayList<NameValuePair> p = new ArrayList<>();
        p.add(new BasicNameValuePair("money", "2"));
        p.add(new BasicNameValuePair("page", "1"));
        p.add(new BasicNameValuePair("page_size", "20"));
        p.add(new BasicNameValuePair("timestamp", "1635767746"));


        //测试环境1
//        String key = "c3d23ec5e3ee66e56a831012ec47d1a4";
        //测试环境2
        String key = "3be7f964336bdc8bfee1a0d2b11e398f";
        //审核平台
//        String key = "e7ed20337850a4b85bfb00c70c62846a";

        Map<String, Object> map = new HashMap<String, Object>();
        map = BuildSign.pToMap(p);
        System.out.println(getSign(map, key));


    }

    public static Map<String, Object> pToMap(List<NameValuePair> p) {

        Map<String, Object> map = new HashMap<String, Object>();

        for (NameValuePair nameValuePair : p) {
            String[] arr = nameValuePair.toString().split("=");
            map.put(arr[0], arr[1]);
        }
        return map;
    }


    //map按Key升序
    public static String getSign(Map<String, Object> map, String keyValue) throws Exception {
        Set mapKey = map.keySet();
        Object[] arr = mapKey.toArray();
        Arrays.sort(arr);
        String beforeSign = "";
        for (Object str : arr) {
            beforeSign += str + "=" + map.get(str.toString()) + "&";
        }

        beforeSign += "key=" + keyValue;
//        System.out.println(beforeSign);
        //        System.out.println("beforeSign：" + beforeSign);
//        System.out.println("MD5加密后字符串：" + signStr);
        return getMD5(beforeSign, 32);
    }

    //对字符串进行MD5加密
    public static String getMD5(String plainText, int length) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte[] digest = md.digest();
        int i;
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            i = b;
            if (i < 0)
                i += 256;
            if (i < 16)
                sb.append(0);
            sb.append(Integer.toHexString(i));
        }
        return sb.substring(0, length);
    }
}
