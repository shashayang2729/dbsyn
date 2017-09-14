package com.minxinloan.service;

import org.springframework.stereotype.Component;

@Component("testComponent")
public class TestComponent {

	public void print(){
		System.out.println("testComponent has injected! ");
	}
	
	
	
}
