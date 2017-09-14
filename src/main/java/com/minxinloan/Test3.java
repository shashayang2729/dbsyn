package com.minxinloan;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016-5-26.
 */
public class Test3 {

    public static void main(String[] args) {


        //Post方式
        HttpClient httpClient = new DefaultHttpClient();
        try {
            //HttpPost httpPost = new HttpPost("http://localhost:8082/minxincustserve/welcome/cust/fortuneCtrl/registerService");
            HttpPost httpPost = new HttpPost("http://www.lianhanghao.com/index.php");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = new JSONObject();
            json.put("bank","1");
            json.put("province","1");
            json.put("city","35");

            Set<String> keys=json.keySet();
            Iterator<String> it=keys.iterator();
            while(it.hasNext()){
                String key=it.next();
                params.add(new BasicNameValuePair(key, json.get(key).toString()));
            }


            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse res = httpClient.execute(httpPost);
            HttpEntity entity = res.getEntity();
            String s = EntityUtils.toString(entity);
            //System.out.println("s==="+s);
            Document doc = Jsoup.parse(s);
            Elements tbdata = doc.getElementsByClass("tbdata");
            Elements tbody = tbdata.select("tbody");
            System.out.println(tbody.toString());
            Elements pager = doc.getElementsByClass("pager");
            System.out.println(pager.text());
            String pagerText = pager.text();
            System.out.println(pagerText.substring(1,pagerText.indexOf("记录")));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
