<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@page import="patient.*"%>
<%@ page import="java.io.PrintWriter"%>
<%
	//JSONObject jsonMain = new JSONObject();
//JSONArray jArray = new JSONArray();
//JSONObject jObject = new JSONObject();
ConnectDB connectDB = ConnectDB.getInstance();

%>
<%
	request.setCharacterEncoding("UTF-8");
//sresponse.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DAO확인</title>
</head>
<body>
	<hr>확인됨<hr>
	
	<%
	String returns = connectDB.testJoin("i1", "p1");
	%>
	회원가입 테스트1:<%=returns%><br><br>
	
	<%
	returns = connectDB.loginDB("psystar", "99star");
	%>
	회원가입 전 로그인 테스트1:<%=returns%><br><br>
	
	<%
	returns = connectDB.testJoin("psystar", "99star");
	%>
	회원가입 테스트2:<%=returns%><br><br>
	
	<%
	returns = connectDB.loginDB("psystar", "99star");
	%>
	회원가입 후 로그인 테스트2:<%=returns%><br><br>
	
	<%
	returns = connectDB.bringManInfo();
	%>
	전체 매니저불러오기<%=returns%><br><br>
	<hr>환자<hr>
	
	<%
	returns = connectDB.patientCheck("11","1234");
	%>
	중복체크(중복이어야 ): <%=returns%><br><br>
	
	<% 
	returns = connectDB.patientCheck("12","1234");
	%>
	중복체크(중복이 아니어야 ): <%=returns%><br><br>
	
	<%
	returns=connectDB.patientInsert("24","4567","2020-11-11");%>
	환자추가 테스트(성공적) <%=returns%><br><br>
	
	<%
	returns=connectDB.pmovingInsert("11","1234","2020-11-11",4.564,7.2345,"광천");%>
	환자 동선 추가 테스트(성공적) <%=returns%><br><br>
	
	<%
	returns=connectDB.patientInsert("24","4567","2020-10-11");%>
	환자추가 테스트(실패: 이미 있는 환자) <%=returns%><br><br>
	
	<%
	returns=connectDB.pmovingInsert("33","1234","2020-11-11",4.564,7.2345,"광천");%>
	환자 동선 추가 테스트(실패: 없는 환자) <%=returns%><br><br>
	
	<%
	returns=connectDB.patientDelete("24","4567");%>
	환자삭제 테스트(성공) <%=returns%><br><br>
	
	<%
	returns=connectDB.patientDelete("01","1234");%>
	환자삭제 테스트(실패-없는 환자 데이터 삭제) <%=returns%><br><br>
	
	<%
	returns=connectDB.patientDelete("11","1234");%>
	환자삭제 테스트(실패-동선 남아있 환자 데이터 삭제) <%=returns%><br><br>
	
	<%
	returns=connectDB.pmovingDelete("33","1234","2020-11-11",4.564,7.2345);%>
	환자 동선 삭제  테스트(실패: 없는 데이터터) <%=returns%><br><br>
	
	<%
	returns=connectDB.pmovingDelete("11","1234","2020-11-11",4.564,7.2345);%>
	환자 동선 삭제  테스트(실패: 없는 데이터터) <%=returns%><br><br>
	
	<%
	returns = connectDB.bringPatientInfo();
	%>
	전체 확진자  불러오기<%=returns%><br><br>
	
	<%
	returns = connectDB.bringPmovingInfo();
	%>
	전체 동선 불러오기<%=returns%><br><br>
	
</body>
</html>