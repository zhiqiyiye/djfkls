package com.mypkg.erp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ErpNumber {
	
	/**
	 * 生成ERP系统编号
	 * @return
	 */
    public String createNumber(Integer productNo,Date date){
    	
    	String str=""+productNo;
    	//输出数据库中返回的值
    	String data=function(productNo);
    	//System.out.println(data);
    	int d=0;
    	if(data!=null&&!data.equals("")){
    		//将字符串数据转换成整形\
        	try {
        		d=Integer.parseInt(data);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else{
    		d=1;
    	}
    	String str1="";
    	if(d<10){
    		str1="000"+d;
    	}else if(d<100&&d>=10){
    		str1="00"+d;
    	}else if(d<1000&&d>=100){
    		str1="0"+d;
    	}else if(d<9999){
    		str1=""+d;
    	}else{
    		System.out.println("这是个错误数据");
    	}
         
    	str=str+dateParseString(date)+str1;
    	
    	return str;
    }
    /**
     * 将java.util.Date数据转化为字符串形式
     * @param date
     * @return
     */
    public String dateParseString(Date date){
    	DateFormat df=new SimpleDateFormat("yyyyMMdd");
    	String str=df.format(date);
    	return str;
    }
    /**
     * 通过查询数据库中的当前ERP编号来查询最后四位数据
     * 
     */
    public String function(Integer number){
		//获取数据库的连接
	    Connection conn=DBUtil.getConnection();
        String sql="select max(substr(trim(erpno),-4))+1 from numberCreate where productNo=?";
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
        	//获取语句执行对象
			pst=conn.prepareStatement(sql);
		    pst.setInt(1, number);
		    //执行获得结果集
		    rs=pst.executeQuery();
		    while(rs.next()){
		    	return rs.getString(1);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//关闭数据库对象
			DBUtil.closeAll(conn, pst, rs);
		}
		return null;
	}
}
