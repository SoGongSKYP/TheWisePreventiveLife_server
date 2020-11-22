<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%
   request.setCharacterEncoding("utf-8");
   String id = request.getParameter("id");
   String pw = request.getParameter("pw");
   String type = request.getParameter("type");
   String returns="";
   
   ConnectDB connectDB = ConnectDB.getInstance();
   
   if (type == null) {
	   return;
   }else if(type.equals("test_write")){
	   System.out.println("값을 받았습니다."+id+" "+pw);
	   
	   
	   returns = connectDB.connectionDB(id, pw);
	   
	   System.out.println(returns);
	   out.println(returns);// 안드로이드로 전송
	   
   }
   
   else if(type.equals("login")){
	   System.out.println("값을 받았습니다."+id+" "+pw);
	   System.out.println("관리자 로그인을 진행합니다.");
	   
	   returns = connectDB.loginDB(id, pw);
	   
	   System.out.println(returns);
	   out.println(returns);// 안드로이드로 전송
   }else if(type.equals("patients_info")){
	   System.out.println("확진자 정보를 모두 불러옵니다");
	   
   }else if(type.equals("insert_patient")){
	   System.out.println("관리자가 확진자 정보를 추가합니다");
	   
   }else if(type.equals("update_patient")){
	   System.out.println("관리자가 확진자 정보를 수정합니다");
	   
   }else if(type.equals("delete_patient")){
	   System.out.println("관리자가 확진자 정보를 삭제합니다");
   }
   else if(type.equals("check_patient")){
	   System.out.println("이미 존재하는 확진자 정보인지 확합니다");
   }
   else{
	   System.out.println("에러입니");
   }
   
%>