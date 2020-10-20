package com.baidu.test;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class APITest {
    //设置APPID/AK/SK
    public static final String APP_ID = "22174740";
    public static final String API_KEY = "aG7wx8p4cuMISQUNcQbXMoTG";
    public static final String SECRET_KEY = "qLqOFzOOC6F1Rwee0tUvjkGndmAB27UD";

    public static void main(String[] args) {

        AipOcr client = init();

        sample(client);
//        String path ="/Users/yuanmengfan/Desktop/8CC2308C-79FC-4905-84C7-B6553ED10831.png";
//        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
//        System.out.println(res.toString(2));
    }

    // 初始化一个AipOcr
    private static AipOcr init() {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }

    public static void sample(AipOcr client) {
        String path = getImgPath();
        if (path == null) {
            return;
        }
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        // 参数为本地图片路径
//        String image = "/Users/yuanmengfan/Desktop/48D8D818-87AE-4313-B076-A5CB4F2AF0AF.png";
        JSONObject res = client.basicGeneral(path, options);
//        System.out.println(res.toString(2));
//        System.out.println(words_result.toString(2));
        Iterator<Object> iterator = res.getJSONArray("words_result").toList().iterator();
        HashMap<Integer, String> hashMap = new HashMap<>();
        int size = 1;
        while (iterator.hasNext()) {
            HashMap<String, Object> next = (HashMap<String, Object>) iterator.next();
            hashMap.put(size++, next.get("words").toString());
//            System.out.println(words);
        }

        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            System.out.printf("行数：%d == %s\n", entry.getKey(), entry.getValue());
        }


//        // 参数为本地图片二进制数组
//        byte[] file = readIm
//        res = client.basicGeneral(file, options);
//        System.out.println(res.toString(2));


//        // 通用文字识别, 图片参数为远程url图片
//        JSONObject res = client.basicGeneralUrl(url, options);
//        System.out.println(res.toString(2));
    }

    private static String getImgPath() {
        JFileChooser jFileChooser = new JFileChooser("/Users/yuanmengfan/Desktop/");
        jFileChooser.showOpenDialog(null);
        String path = jFileChooser.getSelectedFile().getPath();
        System.out.println(path);
        return path;
    }
}
