package com.shashayang.ibatisTest.dao;

import com.shashayang.ibatisTest.entity.IbatisTestBean;

import java.util.List;

/**
 * Created by Administrator on 2016-11-18.
 */

public interface IbatisTestDao {
    List<IbatisTestBean> getList();
    IbatisTestBean getByName(String name);
    IbatisTestBean getByID(String id);
    void insert(IbatisTestBean bean);
    void delete(String id);
    void update(IbatisTestBean bean);
}
