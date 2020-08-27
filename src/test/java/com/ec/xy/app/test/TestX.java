package com.ec.xy.app.test;

import org.junit.Test;

import com.xiuye.sharp.X;

public class TestX {
	
	@Test
	public void testBeanManager() {
		X.beanS("key",String.class,"DDD",true).register().getBean().end().ln();
		X.beanS(String.class).getBean().end().ln();
		X.beanS(String.class, "ABC", true).register().getBean().end().ln();
		X.beanS(String.class).getBean().end().ln();
		X.beanS("key").getBean().end().ln();
		X.beanS("key",String.class,"FFFFF",true).register().getBean().end().ln();
	}
	
	

}
