package com.minxinloan.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 签名工具类
 * 
 * @author jack
 *
 */
public class SignUtil_lj {
	
	private static Logger log = LoggerFactory.getLogger(SignUtil_lj.class);
	
	/**
	 * 签名算法
	 */
	private static String keys  = JixinPropertyUtil.getMessage("keys");//私钥文件
	private static String pass =  JixinPropertyUtil.getMessage("pass") ; //私钥密码
	private static String  crt =  JixinPropertyUtil.getMessage("crt"); ; //服务端证书文件
	
	public SignUtil_lj() {

	}

	/**
	 * 获取签名
	 * 
	 * @param signStr  待签名字符串
	 * @return
	 * @throws Exception
	 */
	public static String sign(String signStr){
		
		log.info((new StringBuilder()).append("待签名字符串：signStr=").append(signStr)
				.toString());
		String sign = null;
		RSAHelper signer = null;
		try {
			//Signature sig = Signature.getInstance(SIGN_ALGORITHMS);
			
			//System.out.println("获取签名私钥");
			
			RSAKeyUtil rsaKey = new RSAKeyUtil(keys, pass);
			signer = new RSAHelper(rsaKey.getPrivateKey());
			
		    sign = signer.sign(signStr);
		} catch (Exception e) {
			log.error("获取签名异常", e.getMessage());
		}
		log.info((new StringBuilder()).append("签名：sign=").append(sign)
				.toString());
		return sign;
	}
	
	/**
	 *  签名校验
	 * @param signText  待验的签名
	 * @param dataText  待签名字符串
	 * @return
	 */
	public static boolean verify(String signText,String dataText){
		signText = signText.replaceAll("[\\t\\n\\r]", "");
		log.info((new StringBuilder()).append("待签名字符串：signStr=").append(dataText)
				.toString());
		log.info((new StringBuilder()).append("签名：sign=").append(signText)
				.toString());
		
		
		boolean b = false;
		try {
			RSAKeyUtil ru = new RSAKeyUtil(crt);
			RSAHelper signHelper = new RSAHelper(ru.getPublicKey()); 
			b = signHelper.verify(dataText , signText);
		} catch (Exception e) {
			log.error("签名校验异常", e.getMessage());
		}
		log.info((new StringBuilder()).append("验证平台签名是否成功").append(b)
				.toString());
		return b;
	}

	
	public static String mergeMap(@SuppressWarnings("rawtypes") Map map) {
		map = new TreeMap(map);
		String requestMerged = "";
		StringBuffer buff = new StringBuffer();
		Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
		Map.Entry<String, String> entry;
		while (iter.hasNext()) {
			entry = iter.next();
			if (!"SIGN".equalsIgnoreCase(entry.getKey())) {
				if(entry.getValue()==null){
					entry.setValue("");
					buff.append("");
				}else{
						buff.append(String.valueOf(entry.getValue()).trim());
				}
			}
		}
		requestMerged = buff.toString();
		return requestMerged;
	}
	
	
	public static void main(String[] args){
		Map<String, String> reqMap =new TreeMap<String, String>();
		reqMap.put("ORDERNO","20150803001");
		reqMap.put("CARDNBR","6236640010100168854");
		reqMap.put("CARD_BIND","6217001630009564771");
		reqMap.put("NAME","朱亮");
		reqMap.put("IDNO","342921199012053612");
		reqMap.put("PHONE","15209862728");
		reqMap.put("AMOUNT","10.01");
		reqMap.put("FEE","0.01");
		reqMap.put("TRXDATE","20151013");
		reqMap.put("FORGERPWD_URL","http://www.baidu.com");
		reqMap.put("TRANSACTION_URL","http://www.iqianbang.com");
		reqMap.put("BACKGROUND_URL","");
		reqMap.put("BANKCODE","30050000");
		reqMap.put("COINSTCODE","000044");
		reqMap.put("COINSTCHANNEL","000002");
		reqMap.put("ROUT_FLAG","N");
		reqMap.put("ROUT_CODE","CI");
		String requestMapMerged = mergeMap(reqMap);
		String sign = SignUtil_lj.sign(requestMapMerged);
		System.out.print("==="+sign);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
