package com.springbook.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void close(ResultSet rs, Statement stmt, Connection conn) {//Statement는부모, PreparedStatement는 자식타입이라 Statement로하면 부모,자식 다 대입이 가능
		if(rs!=null);
		try {	
			   if(!rs.isClosed())rs.close();
		}catch(Exception e) {
				e.printStackTrace();
	    }finally {rs = null;}
		
		if(stmt!=null);
	    try {	
		   if(!stmt.isClosed())stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {stmt = null;}
	   
	   if(conn!=null);
	   try {	
		   if(!conn.isClosed())conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {conn = null; stmt=null; rs=null;}
	}
	
	public static void close(Statement stmt, Connection conn) {//Statement는부모, PreparedStatement는 자식타입이라 Statement로하면 부모,자식 다 대입이 가능
		if(stmt!=null);
	    try {	
		   if(!stmt.isClosed())stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {stmt = null;}
	   
	   if(conn!=null);
	   try {	
		   if(!conn.isClosed())conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {conn = null; stmt=null;}
	}
}
