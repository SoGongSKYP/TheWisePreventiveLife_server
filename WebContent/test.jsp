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
	
	<hr>테스트<hr>
	<%
	returns = connectDB.bringManInfo();
	%>
	전체 매니저불러오기<%=returns%><br><br>

</body>
</html>