package com.minxinloan.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/syndb")
public class DBSynController {
	
	@Autowired 
	protected JdbcTemplate jdbcTemplate;
	
	
	@RequestMapping(value = "insert_customer")
	@ResponseBody
	public String execute(HttpServletRequest request) throws Exception {
		
		System.out.println("xxx");
		return "xxx";
	}

}
