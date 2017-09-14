package com.minxinloan.util;

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016-5-26.
 */
public class Test4 {

public static void main(String[] args){


    //Post方式
    HttpClient httpClient = new DefaultHttpClient();
    try {
        //HttpPost httpPost = new HttpPost("http://localhost:8082/minxincustserve/welcome/cust/fortuneCtrl/registerService");
        //HttpPost httpPost = new HttpPost("http://10.10.95.34:8022/minxincustserve/welcome/jindunCommon/meApp");
        HttpPost httpPost = new HttpPost("http://localhost:8088/minxinmatch/welcome/me/mePushPaymentPlanCtrl/pushActualReceivedPay");
        //HttpPost httpPost = new HttpPost("http://localhost:8088/minxinmatch/welcome/mxd/mxdPublishCtrl/pushActualReceivedPay");
        //HttpPost httpPost = new HttpPost("http://10.10.95.35:8088/minxinmatch/welcome/mxd/mxdPublishCtrl/pushReceivedPayPlan");


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        JSONObject json=new JSONObject();
        //http://124.193.145.146:8022/minxin    custserve/welcome/jindunCommon/meApp/reSetPwdBackUrl
        json.put("backurl", "http://114.251.170.140:8081/website/userAccount/noticeTransPwd.mxd");
        json.put("cardid", "640302200005310900");//640302200005310900
        json.put("surename", "皮夏");
        json.put("requestType", "rechargepc");//drawbackdeliveryredenvelope redenvelope

        json.put("cardNbr", "6212461300000014861");//6212461300000010448
        json.put("mobile", "13504067001");
//    json.put("failureUrl", "http://www.mxd.com/account/profile/password");
//    json.put("successUrl", "http://www.mxd.com/account/profile/password");
        json.put("cardNo", "6227001217450018751");

        //params.add(new BasicNameValuePair("param", json.toJSONString()));
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
        System.out.println("s==="+s);
    }catch (Exception e){
        e.printStackTrace();
    }
}
}
