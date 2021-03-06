package user;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

	public ConnectDB() {}

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
	

	// 관리자 관련 메소드----------------------------------------------------------------------------------------------------------------------------------------------------------------

	/* 안드로이드 요청1: 관리자 로그인 */
	public String loginDB(String id, String pwd) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			sql = "SELECT PWD FROM userTBL WHERE ID = ?";
			// sql="select * from manager A where A.managerID = "+id+" and A.managerPW =
			// "+pwd;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(pwd)) {
					return "success";
				} else
					return "failed";
			} else {
				return "noId";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt2 != null)
				try {
					pstmt2.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
		return "error";
	}

	// 확진자 관련 메소드------------------------------------------------------------------------------------------------------------------------------------

	/* 안드로이드 요청8: 확진자 중복 확인 */
	public String patientCheck(String pnum, String plocnum) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			String SQL = "SELECT * FROM patient WHERE patientnum=? and patientlocnum=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, pnum);
			pstmt.setString(2, plocnum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return "exist";//기존에 존재하는 환
			} else {
				return "newP";// 사용 가능한 id
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "error";
	}

	/* 안드로이드 요청5:동선 추가 */
	public String pmovingInsert(String pnum, String plocnum, String visitdate, double PointX, double PointY,
			String address) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
			sql = "insert into pmoving values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pnum);
			pstmt.setString(2, plocnum);
			pstmt.setDate(3, java.sql.Date.valueOf(visitdate));
			pstmt.setDouble(4, PointX);
			pstmt.setDouble(5, PointY);
			pstmt.setNString(6, address);
			pstmt.executeUpdate();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	/* 안드로이드 요청4:확진자 추가 */
	public String patientInsert(String pnum, String plocnum, String confirmdate) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
			sql = "insert into patient values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pnum);
			pstmt.setString(2, plocnum);
			pstmt.setDate(3, java.sql.Date.valueOf(confirmdate));
			pstmt.executeUpdate();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	/* 안드로이드 요청6: 확진자 삭제 */
	public String patientDelete(String pnum, String plocnum) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
			// delete from patient where patientnum='01' and patientlocnum='1234';
			sql = "delete from patient where patientnum=? and patientlocnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pnum);
			pstmt.setString(2, plocnum);
			pstmt.executeUpdate();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	/* 안드로이드 요청7 동선 삭제 */
	public String pmovingDelete(String pnum, String plocnum, String visitdate, double PointX, double PointY) {
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
			pstmt.executeUpdate();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	/* 안드로이드 요청3: 모든 동선 정보 */
	public JSONArray bringPmovingInfo() {
		JSONArray arr = new JSONArray();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			sql = "Select * from pmoving";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if(rs != null){
			while (rs.next()) {
				JSONObject obj = new JSONObject();

				obj.put("pnum", rs.getString(1));
				obj.put("plocnum", rs.getString(2));
				DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
				obj.put("visitdate", df3.format(rs.getDate(3)));
				obj.put("pointx", Double.toString(rs.getDouble(4)));
				obj.put("pointy", Double.toString(rs.getDouble(5)));
				obj.put("address", rs.getString(6));
				if (obj != null) arr.add(obj);
			}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(arr);
		return arr;
	}

	/* 안드로이드 요청2: 모든 환자 정보 */
	public JSONArray bringPatientInfo() {
		JSONArray arr = new JSONArray();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

			sql = "Select * from patient";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			


			if(rs != null){
			while (rs.next()) {
				JSONObject obj = new JSONObject();

				obj.put("pnum", rs.getString(1));
				obj.put("plocnum", rs.getString(2));
				DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
				obj.put("confirmdate", df3.format(rs.getDate(3)));
				if (obj != null)arr.add(obj);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(arr);
		return arr;
	}

}
