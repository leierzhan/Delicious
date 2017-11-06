package com.zz.ccy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
	
	public Connection start(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {
				 Connection conn = DriverManager.getConnection("jdbc:sqlserver://10.1.0.100:1433;databaseName=Ddb","sa","!@#QWEASDZXCmasterkey");
				System.out.println("=========数据库连接成功==========");
				return conn;
			} catch (SQLException e) {
				System.out.println("==========数据库未连接成功=======");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("驱动加载失败");
			System.out.println("catch::" + e.toString());
		}
		return null;
	}
	public void end(ResultSet rs,Statement stmt,Connection conn) throws SQLException{
		if (rs != null) {
			rs.close();
			rs = null;
		}
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
		if (conn != null) {
			conn.close();
			conn = null;
		}
	}
}
