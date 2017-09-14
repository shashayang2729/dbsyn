package com.minxinloan.servlet;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016-5-20.
 */
public class TestResetPwd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);
        System.out.println("get请求");
       // resp.getWriter().write("hello");
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Post方式
       /* HttpClient httpClient = new DefaultHttpClient();
        try {
            //HttpPost httpPost = new HttpPost("http://localhost:8082/minxincustserve/welcome/cust/fortuneCtrl/registerService");
            HttpPost httpPost = new HttpPost("http://192.168.9.156:8027/minxincustserve/welcome/jindunCommon/meApp");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            JSONObject json = new JSONObject();

            json.put("cardNbr", "6212461300000141037");
            //json.put("CARD_BIND", "6227001217450010000");
            json.put("cardNo", "228481552887309119");
            json.put("mobile", "13521620083");

            json.put("successUrl", "http://www.baidu.com");
            json.put("failureUrl", "http://www.baidu.com");

            json.put("requestType", "resetpassword");
            json.put("cardid", "110101198006230477");
            json.put("surename", "八三");
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
       // resp.setContentType("text/pgeilain; charset=utf-8");
        PrintWriter out   = resp.getWriter();
        out.write(s);
        out.flush();
        out.close();
            //  resp.sendRedirect("http://www.baidu.com");
            *//*RequestDispatcher rd = req.getRequestDispatcher("testResetPwd.jsp");
            req.setAttribute("s", s);//存值
            rd.forward(req, resp);*//*
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
