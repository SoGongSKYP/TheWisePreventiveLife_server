package user;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
    
  //3.아이디 중복체크------------------------------------------------------------------------------------------------------------------------------------
  	public String patientCheck(String pnum, String plocnum) {
        // SELECT * FROM patient WHERE patientnum='11' and patientlocnum='1111';
  		try {
  			Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            String SQL="SELECT * FROM patient WHERE patientnum=? and patientlocnum=?";
  			pstmt=conn.prepareStatement(SQL);
  			pstmt.setString(1, pnum);
  			pstmt.setString(2, plocnum);
  			rs=pstmt.executeQuery();
  			if(rs.next()) {
  				return "exist";
  			}
  			else {
  				return "newP";//사용 가능한 id
  			}
  		}catch(Exception e) {
  			e.printStackTrace();
  		}finally {
  			try {
  				if(rs!=null) rs.close();
  				if(pstmt!=null) pstmt.close();
  			}catch(Exception e) {
  				e.printStackTrace();
  			}
  		}
  		return "error"; 	
  	}
  	//확진자 삽입
  	public String pmovingInsert(String pnum, String plocnum, String visitdate, double PointX, double PointY,String address) {
  		try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
          //insert into pmoving values('21','2234',DATE '2020-12-15',1.24253,3.44234,'서울시 중구 필동');
           sql = "insert into pmoving values(?,?,?,?,?,?)";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, pnum);
           pstmt.setString(2, plocnum);
           pstmt.setDate(3, java.sql.Date.valueOf(visitdate));
           pstmt.setDouble(4, PointX);
           pstmt.setDouble(5, PointY);
           pstmt.setNString(6, address);
           if(pstmt.executeUpdate()==1) {
               return "success";}else return "query_fail";
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
        System.out.println(returns);
        return "fail";
  	}
  	
  	//확진자 동선 삽입
  	public String patientInsert(String pnum, String plocnum, String confirmdate) {
  		try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
          //insert into patient values('21', '2234', DATE '2020-11-15');
           sql = "insert into patient values(?,?,?)";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, pnum);
           pstmt.setString(2, plocnum);
           pstmt.setDate(3, java.sql.Date.valueOf(confirmdate));
           if(pstmt.executeUpdate()==1) {
               return "success";}else return "query_fail";
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
        System.out.println(returns);
        return "fail";
  	}
  	
  	public String patientDelete(String pnum, String plocnum) {
  		try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
          //delete from patient where patientnum='01' and patientlocnum='1234';
           sql = "delete from patient where patientnum=? and patientlocnum=?";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, pnum);
           pstmt.setString(2, plocnum);
           if(pstmt.executeUpdate()==1) {
           return "success";}else return "query_fail";
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
        System.out.println(returns);
        return "fail";
  	}
  	
  	//확진자 동선 삭제
  	public String pmovingDelete(String pnum, String plocnum, String visitdate, double PointX, double PointY) {
  		//  delete from pmoving where patientnum='11' and patientlocnum='1111' and visitdate=DATE '2020-9-16' and pointx=6.24253 and pointy=3.42334;
  		try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
         
           sql = "delete from pmoving where patientnum=? and patientlocnum=? and visitdate=? and pointx=? and pointy=?";
           pstmt = conn.prepareStatement(sql);
           pstmt.setString(1, pnum);
           pstmt.setString(2, plocnum);
           pstmt.setDate(3, java.sql.Date.valueOf(visitdate));
           pstmt.setDouble(4, PointX);
           pstmt.setDouble(5, PointY);
           if(pstmt.executeUpdate()==1) {
               return "success";}else return "query_fail";
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
        System.out.println(returns);
        return "fail";
  	}
  	
  	public String bringPmovingInfo() {
  		returns="";
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
          
           sql = "Select * from pmoving";
           pstmt = conn.prepareStatement(sql);
           rs = pstmt.executeQuery();
           
        
            while (rs.next()) {
            returns+="pnum: ";
            returns+=rs.getString(1);
            returns+="  &   plocnum: ";
            returns+=rs.getString(2);
            returns+="  &   visitmdate: ";
            DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
            returns+=df3.format(rs.getDate(3));
            returns+="  &   pointx: ";
            returns+=Double.toString(rs.getDouble(4));
            returns+="  &   pointy: ";
            returns+=Double.toString(rs.getDouble(5));
            returns+="  &   address: ";
            returns+=rs.getString(6);
            returns+="        ////        ";
            System.out.println(returns);
            } 
            return returns;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return "error";
    }
  	
  	
	public String bringPatientInfo() {
  		returns="";
        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
          
           sql = "Select * from patient";
           pstmt = conn.prepareStatement(sql);
           rs = pstmt.executeQuery();
           
        
            while (rs.next()) {
            returns+="pnum: ";
            returns+=rs.getString(1);
            returns+="  &   plocnum: ";
            returns+=rs.getString(2);
            returns+="  &   confirmdate: ";
            DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
            returns+=df3.format(rs.getDate(3));
            
            returns+="        ///        ";
            System.out.println(returns);
            } 
            return returns;
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return "error";
    }
  	
  	
  	
 //----------------------------------------------------------------------------------------------------------------------------------------------------------------
  	

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