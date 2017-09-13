package com.shashayang.ibatisTest.entity;
/**
 * Created by Administrator on 2016-11-18.
 */

public class IbatisTestBean {
    private String id;
    private String name;
    public IbatisTestBean(){}
    public String getId(){return this.id;}
    public String getName(){return this.name;}
    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
}
