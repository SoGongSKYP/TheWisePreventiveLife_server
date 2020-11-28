<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@page import="patient.*"%>
<%@ page import="java.io.PrintWriter"%>


<%


%>
<%
ConnectDB connectDB = ConnectDB.getInstance();
String returns = "";

request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DAO확인</title>
</head>
<body>
	<%

		String arr=connectDB.bringManInfo();
	
	%>
	전체 매니저불러오기<%=arr%><br>
	<br>
	
	<%

		arr=connectDB.bringPatientInfo();
	
	%>
	전체 환자ㅏ불러오기<%=arr%><br>
	<br>
	<%

		arr=connectDB.bringPmovingInfo();
	
	%>
	전체 환자동선불러오기<%=arr%><br>

</body>
</html>