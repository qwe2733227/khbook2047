package common.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcTemplate {
	public JdbcTemplate() {}
	public static Connection getConnection() {
		Connection con = null;
		Properties prop = new Properties();
		try {
			String currentPath = JdbcTemplate.class.getResource("/").getPath();
			System.out.println(currentPath);
			prop.load(new BufferedReader(new FileReader(currentPath+"driver.properties" )));
			
			Class.forName(prop.getProperty("driver"));
			con = DriverManager.getConnection(prop.getProperty("url"), 
					prop.getProperty("user"), 
					prop.getProperty("db.pwd"));
			if(con != null) {
				System.out.println("DB연결성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public static void close(Connection con) {
		try {
			if(con!=null && !con.isClosed()) con.close();
		} catch (SQLException e) {			e.printStackTrace();		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt!=null && !stmt.isClosed()) stmt.close();
		} catch (SQLException e ) {			e.printStackTrace();		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed()) rs.close();
		} catch (SQLException e ) {			e.printStackTrace();		}
	}
	
	public static void commit(Connection con) {
		try {
			if(con!=null && !con.isClosed()) con.commit();
		} catch (SQLException e ) {			e.printStackTrace();		}
	}
	public static void rollback(Connection con) {
		try {
			if(con!=null && !con.isClosed()) con.rollback();
		} catch (SQLException e) {			e.printStackTrace();		}
	}
}
