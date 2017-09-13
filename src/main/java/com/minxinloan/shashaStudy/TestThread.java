package com.minxinloan.shashaStudy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016-9-26.
 */
public class TestThread {

    public static int count = 0;
    public static void inc(){
        try {
            Thread.sleep(1);
        }catch(Exception e){}

        count++;
    }

    public static void main(String[] args){
       /* for (int i=0 ;i<1000;i++){
             new Thread(new Runnable() {
                 public void run() {
                    inc();
                 }
             }).start();
        }

        System.out.println("count:"+count);*/

        Map map = new HashMap<String,String>();
        map.put("","ss");
        map.put(null,"tt");
        System.out.print(map.get(""));
        System.out.print(map.get(null));
    }
}
