package com.minxinloan.service;

import com.minxinloan.util.JixinOnlineTransUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2016-4-25.
 */
@Service("customerService")
//@Transactional
public class CustomerService {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
    public String updateTel(String ntel,String otel,String seqNo,String cardNum) throws Exception {
        try{
            jdbcTemplate.execute("update t_customer set tel ='" + ntel + "'  where tel = '" + otel + "'");
            //江西银行绑定手机号更改
            Map<String, String> reqMap =new TreeMap<String, String>();
            reqMap.put("TRXCODE", "5841");
            reqMap.put("TRXDATE", seqNo.substring(0, 8));
            reqMap.put("TRXTIME", seqNo.substring(8, 14));
            reqMap.put("SEQNO", seqNo);
            reqMap.put("RETCODE", "");
            reqMap.put("RETMSG", "");
            reqMap.put("HEADRESERVED", "");
            reqMap.put("CARDNBR", cardNum);
            reqMap.put("PINFLAG", "0");
            reqMap.put("PIN", "");
            reqMap.put("OPTION", "1");//修改
            reqMap.put("MOPHONE", ntel);
            JixinOnlineTransUtil util = new JixinOnlineTransUtil();
            Map res= util.testCommon(reqMap);
            //System.out.println("===");
            if("00000000".equals(res.get("RETCODE"))){
                return "{\"result\":\"0\"}";
            }else{
                System.out.print("error:"+res.get("RETCODE")+"=="+res.get("RETMSG"));
                throw new Exception(res.get("RETCODE")+"=="+res.get("RETMSG"));
                //s = "{\"result\":\"1\",\"msg\":\""+res.get("RETCODE")+"=="+res.get("RETMSG")+"\"}";
            }
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
