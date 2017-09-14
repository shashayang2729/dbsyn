package com.minxinloan;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017-8-8.
 */

public class TestRedis {
  /*  public static void main(String[] args) {
        Jedis j = new Jedis("192.168.200.133",6379);
        j.auth("123");
        System.out.println(j.ping());
    }*/
  private Jedis j;
  @org.junit.Before
    public void setup(){
        j = new Jedis("192.168.200.133",6379);
    //    j.auth("123");
        System.out.println(j.ping());
    }

    @Test
    public void testString(){
        j.set("name","xinxin");
        System.out.println(j.get("name"));
    }
}
