package com.minxinloan.util;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;


import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import java.util.*;

/**
 * 联机交易接口
 */
public class JixinOnlineTransUtil {



    public Map testCommon(Map<String, String> reqMap) throws Exception{
    	
    	 reqMap.put("BANKCODE", JixinPropertyUtil.getMessage("BANKCODE"));
	     reqMap.put("COINSTCODE", JixinPropertyUtil.getMessage("COINSTCODE"));
	     reqMap.put("COINSTCHANNEL",JixinPropertyUtil.getMessage("COINSTCHANNEL"));
	     reqMap.put("SOURCE", JixinPropertyUtil.getMessage("SOURCE"));
    	//HttpMessageConverter 内容转换器
    	List list = new ArrayList<HttpMessageConverter<?>>(){
    		{
    			add(new FastJsonHttpMessageConverter());
    		}
    		};

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(list);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestMapMerged = mergeMap(reqMap);
        System.out.println("requestMapMerged:  "+ requestMapMerged);
		
		//处理签名
		String sign = SignUtil_lj.sign(requestMapMerged);
		System.out.println("签名:" + sign);
		reqMap.put("SIGN", sign);
        System.out.println("签名成功");
//==============================================
        
        headers.set("Accept-Charset", "UTF-8");
        headers.set("contentType","application/json");
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);

        HttpEntity entity = new HttpEntity(reqMap,headers);

        System.out.println("发送请求至："+JixinPropertyUtil.getMessage("uri"));

        ResponseEntity response  = restTemplate.exchange(JixinPropertyUtil.getMessage("uri"),HttpMethod.POST, entity, Map.class);

      //响应报文
        System.out.println("返回："+response.getBody());
        Map responseMap = (Map)response.getBody();
        
        //JSONObject  paramsJson = JSONObject.fromObject(responseMap);

        //验签
        String responseSign = (String) responseMap.get("SIGN");
        responseMap.remove("SIGN");
        
        String responseMapMerged = mergeMap(new TreeMap(responseMap));

        System.out.println("responseMerged: "+responseMapMerged.toString());

        if(responseSign==null){
        	System.out.println("error");
        	return null;
        }
        boolean verifyResult = SignUtil_lj.verify(responseSign.replaceAll("[\\t\\n\\r]", ""), responseMapMerged.toString());
        if (!verifyResult){
            System.out.println("验证签名失败...");
            return null;
        } else {
            System.out.println("验证签名成功");
        }

        if ("00000000".equals(responseMap.get("RETCODE"))){
            System.out.println("操作成功");
        } else {
            System.out.println("操作失败,错误代码："+responseMap.get("RETCODE"));
        }

        return responseMap;
    }

  
    

    HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                    + session.getPeerHost());
            return true;
        }
    };

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

    static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

    }
    
    public static String mergeMap(Map map ) {
    	Map reqMap = new TreeMap(map);
    	
		String requestMerged = "";
		StringBuffer buff = new StringBuffer();
		Iterator<Map.Entry<String, String>> iter = reqMap.entrySet().iterator();
		Map.Entry<String, String> entry;
		while (iter.hasNext()) {
			entry = iter.next();
			if (!"SIGN".equals(entry.getKey())) {
				if(entry.getValue()==null){
					entry.setValue("");
					buff.append("");
				}else{
					if("SUBPACKS".equals(entry.getKey()) ){
						StringBuffer substr = null;
						if(reqMap.get("SUBPACKS") != null){
							substr  = new StringBuffer();
				    		
				    		List<Map> submapList = (List<Map>)reqMap.get("SUBPACKS");
							for(Map submap : submapList){
								substr.append(mergeMap(submap));
							}
						}
						buff.append(String.valueOf(substr.toString()).trim());
					}else{
						buff.append(String.valueOf(entry.getValue()).trim());
					}
				}
			}
		}
		requestMerged = buff.toString();
		return requestMerged;
	}
    
    }
