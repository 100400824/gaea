package com.gaea.server.LFSAPI;

import org.apache.http.NameValuePair;

import java.security.MessageDigest;
import java.util.*;

//验签生成工具
public class BuildSign {

    public static void main(String[] args) throws Exception {
        String str = "{\"img\":\"https:\\/\\/pic.iyingdi.com\\/yingdiapp\\/202108\\/12371153\\/1628239446601_1628239446524244_w_1939_h_1065.png\",\"content\":\"\\u6d4b\\u8bd5\\u4e00\\u4e0b\\u65b0\\u903b\\u8f9110\",\"rich\":\"\\u6d4b\\u8bd5\\u4e00\\u4e0b\\u65b0\\u903b\\u8f9120https:\\/\\/pic.iyingdi.com\\/yingdiapp\\/202108\\/12371153\\/1628239446601_1628239446524244_w_1939_h_1065.png\"}";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("app_id", 41);
        map.put("event_id", 48);
        map.put("event_context", str);
        map.put("ticket_id", "zpw001");
        getSign(map);



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
    public static String getSign(Map<String, Object> map) throws Exception {
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
        //beforeSign += "key=" + "c3d23ec5e3ee66e56a831012ec47d1a4";
        //审核平台
        //beforeSign += "key=" + "e7ed20337850a4b85bfb00c70c62846a"
        beforeSign += "key=" + "e7ed20337850a4b85bfb00c70c62846a";

        String signStr = getMD5(beforeSign, 32);
//        System.out.println("beforeSign：" + beforeSign);
//        System.out.println("MD5加密后字符串：" +signStr);
        return signStr;
    }

    //对字符串进行MD5加密
    public static String getMD5(String plainText, int length) throws Exception {
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
