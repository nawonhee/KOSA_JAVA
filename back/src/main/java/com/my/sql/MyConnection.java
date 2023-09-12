package com.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	/**
	 * JDBC드라이버 로드 및 DB와 연결한다
	 * @return Connection 객체를 반환한다
	 * @throws Exception 드라이버 클래스 찾지 못하거나 DB연결 실패 시 예외 발생한다
	 */
	public static Connection getConnection() throws Exception{
		//JDBC드라이버로드
		Class.forName("oracle.jdbc.OracleDriver");
		
		//DB연결
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //아이피,포트번호,서비스아이디
		String user = "hr";
		String password = "hr";
		return DriverManager.getConnection(url,user,password);
	}
	/**
	 * 
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		//DB연결 해제
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
}
