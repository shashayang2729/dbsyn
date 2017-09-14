package com.minxinloan.controller;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-9-13.
 */
//Controller专用
public class ResultBean<T> implements Serializable {
    private T data;
    private static final int SUCCESS = 0;
    private static final int FAIL = 1;
    private String msg = "success";
    private int code = SUCCESS;
    public ResultBean(){
        super();
    }

    public ResultBean(T data){
        super();
        this.data = data;
    }
    public ResultBean(Throwable e ){
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
}
