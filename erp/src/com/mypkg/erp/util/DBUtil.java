package com.mypkg.erp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * 普通数据库连接
 */
public class DBUtil {
	private static final String DRIVER="oracle.jdbc.OracleDriver";
	private static final String URL="jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USERNAME="scott";
	private static final String PASSWORD="tiger";
	
	/*
	 *加载并注册驱动
	 */
	static{
		try{
			Class.forName(DRIVER);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	/*
	 * 连接Oracle数据库
	 */
	public static Connection getConnection(){
		Connection conn=null;
		try{
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * 关闭数据库所有连接
	 */
	public static void closeAll(Connection conn,Statement st,ResultSet rs){
		try{
			if(rs!=null){
				rs.close();//关闭结果集对象
			}
			if(st!=null){
				st.close();//关闭执行命令对象
			}
			if(conn!=null){
				conn.close();//关闭连接
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
