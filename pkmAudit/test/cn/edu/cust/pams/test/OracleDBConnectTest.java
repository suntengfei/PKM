package cn.edu.cust.pams.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleDBConnectTest {
	
	public static final String driver = "oracle.jdbc.OracleDriver";
	
	public static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:cust";
	
	public static final String user = "scott";
	
	public static final String pwd = "tiger";
	
	
	public static void main(String[] args) {
		
		Connection conn = null;
		
		try {
			
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pwd);
			
			String sql = "select * from emp";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
