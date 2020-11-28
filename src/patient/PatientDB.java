package patient;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.ConnectDB;

public class PatientDB {
private static PatientDB pinstance=new PatientDB();
public static PatientDB getInstance() {
    return pinstance;
}
public PatientDB() { }

// oracle 계정
private static final String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
private static final String userId = "system";
private static final String userPw = "oracle";

Connection conn = null;
PreparedStatement pstmt = null;
PreparedStatement pstmt2 = null;
ResultSet rs = null;

String sql = "";
String sql2 = "";
String returns = "error_a";
String returns2 = "error_b";

public String p_connectionDB(String id, String pwd) {
	try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
      
        sql = "SELECT PWD FROM userTBL WHERE ID = ?";
       
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
//patients_info
public String bring_Patient(String id, String pwd) {

    return returns2;
}
}