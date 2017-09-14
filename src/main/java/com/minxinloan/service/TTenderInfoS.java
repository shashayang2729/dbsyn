package com.minxinloan.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("tTenderInfoS")
//@Component("tTenderInfoS")
//@Resource(name="tTenderInfoS")
public class TTenderInfoS {
	
	public TTenderInfoS(){
		
	}
	
	@Autowired 
	protected JdbcTemplate jdbcTemplate;
	
	@Transactional
	public boolean setTTenderInfo(){
		String st = "xx";
		try {
			int i = jdbcTemplate.queryForInt("select count(*) from t_tender_info ");
			jdbcTemplate.execute(" update t_tender_info t set t.creator_name = '"+ st +"' where t.loan_id=7153 ");
		} catch (Exception e) {
			return false;
		}
		return true; 
	}
	
	
	
	@Autowired(required=true)
	private TestComponent testComponent;
	public void p(){
		testComponent.print();
	}

}
