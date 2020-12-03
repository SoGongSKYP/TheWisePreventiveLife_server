<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="user.*"%>
<%@ page import="java.io.PrintWriter"%>
<%@page import="org.json.simple.*"%>
<%
request.setCharacterEncoding("utf-8");
String type = request.getParameter("type");

String id = request.getParameter("id");
String pw = request.getParameter("pw");

String pnum = request.getParameter("pnum");
String plocnum= request.getParameter("plocnum");
String confirmdate = request.getParameter("confirmdate");

String visitdate = request.getParameter("visitdate");
String pointx=request.getParameter("pointx");
String pointy=request.getParameter("pointy");
String address=request.getParameter("address");

ConnectDB connectDB = ConnectDB.getInstance();

if (type == null) { return;

/*안드로이드 요청1: 관리자 로그인 */
}else if (type.equals("login")) {
	System.out.print("값을 받았습니다." + id + " " + pw);
	System.out.print("관리자 로그인을 진행합니다.");
	String returns = connectDB.loginDB(id, pw);
	System.out.print(returns);
	out.print(returns);// 안드로이드로 전송
	
/*안드로이드 요청2: 모든 환자 정보  */
}else if (type.equals("patients_info")) {
	System.out.println("환자 정보를 모두 불러옵니다");
	System.out.println("값을 받았습니다." + type);
	JSONArray arr = connectDB.bringPatientInfo();
	System.out.println(arr);
	out.println(arr);// 안드로이드로 전송
	System.out.println("성공했습니다");
	
/*안드로이드 요청3: 모든 동선 정보  */	
}else if (type.equals("pmoving_info")) {
	System.out.println("환자 동선 정보를 모두 불러옵니다");
	System.out.println("값을 받았습니다." + type);
	JSONArray arr = connectDB.bringPmovingInfo();
	System.out.println(arr);
	out.println(arr);// 안드로이드로 전송
	System.out.println("성공했습니다");


/*안드로이드 요청4:확진자 추가  */
} else if (type.equals("insert_patient")) {
	System.out.println("관리자가 확진자 정보를 추가합니다"+pnum+" "+plocnum+" "+confirmdate);
	String returns=connectDB.patientInsert(pnum, plocnum, confirmdate);
	System.out.print(returns);
	out.print(returns);
	
/*안드로이드 요청5:동선 추가  */
}else if (type.equals("insert_pmoving")) {
	System.out.println("\n관리자가 동선 정보를 추가합니다"+pnum+" "+plocnum+" "+address);
	String returns=connectDB.pmovingInsert(pnum, plocnum, visitdate, Double.parseDouble(pointx), Double.parseDouble(pointy), address);
	System.out.print(returns);
	out.print(returns);
	
/*안드로이드 요청6: 확진자 삭제 */
}else if (type.equals("delete_patient")) {
	System.out.println("관리자가 확진자 정보를 삭제합니다"+pnum+" "+plocnum);
	String returns=connectDB.patientDelete(pnum,plocnum);
	System.out.print(returns);
	out.print(returns);
	
/*안드로이드 요청7 동선 삭제 */
}else if (type.equals("delete_pmoving")) {
	System.out.println("\n관리자가 동선 정보를 삭제합니다"+pnum+" "+plocnum+" "+pointx+" "+pointy);
	String returns=connectDB.pmovingDelete(pnum, plocnum, visitdate, Double.parseDouble(pointx), Double.parseDouble(pointy));
	System.out.print(returns);
	out.print(returns);
	
/*안드로이드 요청8: 확진자 중복 확인 */	
}else if (type.equals("check_patient")) {
	System.out.println("이미 존재하는 확진자 정보인지 확합니다");
	System.out.println("값을 받았습니다." + pnum+"  "+plocnum);
	String returncp=connectDB.patientCheck(pnum,plocnum);
	System.out.println(returncp);
	out.print(returncp);
	

} else {
	System.out.println("에러입니다");
}
%>