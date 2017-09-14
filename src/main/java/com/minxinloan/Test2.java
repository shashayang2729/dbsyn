package com.minxinloan;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016-5-26.
 */
public class Test2 {
    public String getPageContent(String strUrl, String strPostRequest,
                                 int maxLength) {
        // 读取结果网页
        StringBuffer buffer = new StringBuffer();
        System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
        System.setProperty("sun.net.client.defaultReadTimeout", "5000");
        try {
            URL newUrl = new URL(strUrl);
            HttpURLConnection hConnect = (HttpURLConnection) newUrl
                    .openConnection();
            // POST方式的额外数据
            if (strPostRequest.length() > 0) {
                hConnect.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(hConnect
                        .getOutputStream());
                out.write(strPostRequest);
                out.flush();
                out.close();
            }
            // 读取内容

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    hConnect.getInputStream()));
            int ch;
            for (int length = 0; (ch = rd.read()) > -1
                    && (maxLength <= 0 || length < maxLength); length++)
                buffer.append((char) ch);
            String s = buffer.toString();
            s.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
            System.out.println(s);

            rd.close();
            hConnect.disconnect();
            return buffer.toString().trim();
        } catch (Exception e) {
            // return "错误:读取网页失败！";
            //
            return null;


        }
    }


    public String test2(String strUrl){
        try{
            URL url=new URL(strUrl);
            BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
            String s="";
            StringBuffer sb=new StringBuffer("");
            while((s=br.readLine())!=null)
            {
                sb.append(s+"\r\n");
            }
            br.close();
            return sb.toString();
        }
        catch(Exception e){
            return "error open url" + strUrl;

        }

    }


    public void test3() throws Exception{
       URL url = new URL("http://www.renren.com");
        byte bytes[] = new byte[1024 * 1000];
        int index = 0;
        InputStream is = url.openStream();
        int count = is.read(bytes, index, 1024 * 100);
        while (count != -1) {
            index += count;
            count = is.read(bytes, index, 1);
        }

        FileOutputStream fos = new FileOutputStream("test.html");
        System.out.println(index);
        fos.write(bytes, 0, index);
        is.close();
        fos.close();

    }

    public static void main(String[] args) throws Exception{

        String url = "http://www.renren.com";
        String keyword = "人人";
        Test2 p = new Test2();
        //String response = p.createhttpClient(url, keyword); // 第一种方法
        // p.getPageContent(url, "post", 100500);//第二种方法
      //System.out.println(p.test2(url)) ;
        p.test3();
    }
}
