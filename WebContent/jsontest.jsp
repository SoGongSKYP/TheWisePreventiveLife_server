<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@page import="patient.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.*" %>

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
JSONArray jarr=connectDB.bringManInfo();
	
	%>
	전체 매니저불러오기<%=jarr%><br>
	out.print(jarr);
	<br>
	
	<%
String arr=" ";
		jarr=connectDB.bringPatientInfo();
	
	%>
	전체 환자ㅏ불러오기<%=jarr%><br>
	<br>
	<%

		jarr=connectDB.bringPmovingInfo();
	
	%>
	전체 환자동선불러오기<%=jarr%><br>

</body>
</html>