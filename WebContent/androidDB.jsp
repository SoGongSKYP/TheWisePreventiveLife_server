<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@page import="patient.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.*" %>
<%
request.setCharacterEncoding("utf-8");
String type = request.getParameter("type");

String id = request.getParameter("id");
String pw = request.getParameter("pw");

String pnum = request.getParameter("p_id");
String plocnum= request.getParameter("p_id");
String confirmdate = request.getParameter("p_pw");

String visitdate = request.getParameter("visitdate");
String pointx=request.getParameter("pointx");
String pointy=request.getParameter("pointy");
String address=request.getParameter("address");

String returns = "";

ConnectDB connectDB = ConnectDB.getInstance();

if (type == null) { return;
} else if (type.equals("test_write")) {
	System.out.println("값을 받았습니다." + id + " " + pw);
	//returns = connectDB.connectionDB(id, pw);
	System.out.println(returns);
	out.println(returns);// 안드로이드로 전송
}
else if (type.equals("login")) {
	System.out.print("값을 받았습니다." + id + " " + pw+" "+type);
	System.out.print("관리자 로그인을 진행합니다.");
	returns = connectDB.loginDB(id, pw);
	System.out.print(returns);
	out.print(returns);// 안드로이드로 전송
	
} else if (type.equals("manager_info")) {
	System.out.println("관리 정보를 모두 불러옵니다");
	//returns =connectDB.bringManInfo();
	System.out.println(returns);
	out.println(returns);// 안드로이드로 전송
	
}else if (type.equals("patients_info")) {
	System.out.print("환 정보를 모두 불러옵니다");
	System.out.print("값을 받았습니다." + type);
	JSONArray arr = connectDB.bringPatientInfo();
	System.out.print(arr);
	out.println(arr);// 안드로이드로 전송
	System.out.println("성공했습니다");
	
}else if (type.equals("pmoving_info")) {
	System.out.print("환자 동선 정보를 모두 불러옵니다");
	System.out.print("값을 받았습니다." + type);
	JSONArray arr = connectDB.bringPmovingInfo();
	System.out.print(arr);
	out.println(arr);// 안드로이드로 전송
	System.out.println("성공했습니다");
} else if (type.equals("insert_patient")) {
	System.out.println("관리자가 확진자 정보를 추가합니다");
} else if (type.equals("update_patient")) {
	System.out.println("관리자가 확진자 정보를 수정합니다");
} else if (type.equals("delete_patient")) {
	System.out.println("관리자가 확진자 정보를 삭제합니다");
} else if (type.equals("check_patient")) {
	System.out.println("이미 존재하는 확진자 정보인지 확합니다");
	System.out.println("값을 받았습니다." + pnum+"  "+plocnum+"  "+type);
	String returncp=connectDB.patientCheck(pnum,plocnum);
	System.out.println(returncp);
	out.print(returncp);
} else {
	System.out.println("에러입니");
}
%>