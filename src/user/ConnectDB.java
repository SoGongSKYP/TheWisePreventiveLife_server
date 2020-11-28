package user;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
	private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }
    public ConnectDB() { }

    // oracle 계정
    private static final String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String userId = "system";
    private static final String userPw = "oracle";
    Statement stmt = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;

    String sql = "";
    String sql2 = "";
    String returns = "error_a";
    String returns2 = "error_b";

    public String testJoin(String id, String pwd) {
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
          
            sql = "SELECT id FROM userTBL WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                returns = "이미 존재하는 아이디 입니다.";
            } else {
                sql2 = "INSERT INTO userTBL VALUES(?,?)";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, id);
                pstmt2.setString(2, pwd);
                pstmt2.executeUpdate();
                returns = "회원 가입 성공 !";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns;
    }
    
    public String loginDB(String id, String pwd) {
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
          
           sql = "SELECT PWD FROM userTBL WHERE ID = ?";
            //sql="select * from manager A where A.managerID = "+id+" and A.managerPW = "+pwd;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
            	if(rs.getString(1).equals(pwd))
					return "success";	
				else
					return "failed";	
            } else {return "noId";}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns2;
    }
    
    public String bringManInfo() {
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
          
           sql = "SELECT * FROM userTBL";
           pstmt = conn.prepareStatement(sql);
           rs = pstmt.executeQuery();
           returns="";
        
            while (rs.next()) {
            returns+="id: ";
            returns+=rs.getString(1);
            returns+="& pwd: ";
            returns+=rs.getString(2);
            returns+="/ / /";
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } 
        System.out.println(returns);
        return returns;
    }
}