package com.minxinloan.shashaStudy;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016-10-24.
 */
public class Test2 {


    public static double doubleTo2(double d){
        BigDecimal df = new BigDecimal(d);
        double d2 = df.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d2;
    }

    public static void main(String[] args){
        double b = 2356.15;
        double a=2356.15-865-103.8;
        System.out.println(a);
        System.out.println(doubleTo2(1387.3500000000001));
        System.out.println(doubleTo2(418.5499999999999));
    }
}
