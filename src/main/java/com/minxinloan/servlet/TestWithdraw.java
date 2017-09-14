package com.minxinloan.servlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016-5-20.
 */
public class TestWithdraw extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        System.out.println("get请求");
        resp.getWriter().write("hello");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
     /*   String amount = req.getParameter("amount");
       String fee =  req.getParameter("fee");
        //Post方式
        HttpClient httpClient = new DefaultHttpClient();
        try {
            //HttpPost httpPost = new HttpPost("http://localhost:8082/minxincustserve/welcome/cust/fortuneCtrl/registerService");
            HttpPost httpPost = new HttpPost("http://10.10.95.35:8082/minxincustserve/welcome/jindunCommon/meApp");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = new JSONObject();
           *//* json.put("cardNo","6227001217450010000");
            json.put("mobile","18612811333");
            json.put("surename","史戈雅");
            json.put("cardid", "451025197308146588");*//*
            json.put("requestType", "withdraw");
            json.put("cardNbr", "6212461300000008715");
            json.put("cardNo", "6227001217450010000");
            json.put("mobile", "18612811338");
            json.put("amount", amount);
            json.put("fee", fee);
            json.put("transaction_url", "http://www.baidu.com");
            json.put("rout_flag", "N");
            json.put("bank_cnaps", "");

            json.put("cardid", "451025197909152801");
            json.put("surename", "钱云蔚");
            json.put("backurl", "http://www.baidu.com");


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
           // System.out.println("s===" + s);

       // resp.setContentType("text/xml; charset=UTF-8");
        resp.setContentType("text/javascript;charset=UTF-8");
       // resp.setContentType("text/plain; charset=utf-8");
        PrintWriter out   = resp.getWriter();
        out.write(s);


        //resp.sendRedirect("testWithdraw.jsp");
      //  resp.sendRedirect("http://www.baidu.com");
        out.flush();
        out.close();
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }
}
