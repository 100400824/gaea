package com.gaea.server.lfsAPI;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//验签生成工具
public class BuildSign {

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("password", "123123a");
        map.put("timestamp", 1626951229);
        map.put("type", "password");
        map.put("username", "86_18612977127");
        getSign(map);
    }

    //map按Key升序
    public static void getSign(Map<String, Object> map) throws Exception{
        Set mapKey = map.keySet();
        Object[] arr = mapKey.toArray();
        Arrays.sort(arr);
        String beforeSign = "";
        for (Object str : arr) {
            beforeSign += str + "=" + map.get(str.toString()) + "&";
        }
        //线上环境 线上Key貌似错误
//        beforeSign += "key=" + "2d90bc1c110635cb8d80c84852f8c3f8";
        //测试环境
        beforeSign += "key=" + "c3d23ec5e3ee66e56a831012ec47d1a4";
        System.out.println("beforeSign：" + beforeSign);
        System.out.println("MD5加密后字符串：" +getMD5(beforeSign,32));
    }

    //对字符串进行MD5加密
    public static String getMD5(String plainText, int length)throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte[] digest = md.digest();
        int i;
        StringBuilder sb = new StringBuilder();
        for (int offset = 0; offset < digest.length; offset++) {
            i = digest[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                sb.append(0);
            sb.append(Integer.toHexString(i));
        }
        return sb.toString().substring(0, length);
    }
}
