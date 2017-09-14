package com.minxinloan;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//Post方式
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost httpPost = new HttpPost("http://10.10.18.21:8088/minxinmatch/api/loanApply");
			//List<NameValuePair> params = new ArrayList<NameValuePair>();
			JSONObject json=new JSONObject();
			json.put("name","杨莎莎");
			json.put("tel","1455555");
			json.put("cardNum","1122222");
			String jsonStr = json.toJSONString();
			StringEntity entity = new StringEntity(jsonStr,"UTF-8");
			httpPost.setEntity(entity);
			HttpResponse res = httpClient.execute(httpPost);
			HttpEntity entity2 = res.getEntity();
			String s = EntityUtils.toString(entity2);
			System.out.println("s==="+s);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
