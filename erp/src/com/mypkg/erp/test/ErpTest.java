package com.mypkg.erp.test;

import java.util.Date;

import com.mypkg.erp.util.ErpNumber;

public class ErpTest {

	public static void main(String[] args) {
		ErpNumber erp=new ErpNumber();
		System.out.println("编号号码测试结果如下");
		//根据产品编号和时间来进行编号
		System.out.println(erp.createNumber(200,new Date()));
		
	}

	
	
}
