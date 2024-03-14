<%@ page import="java.time.LocalTime"%>
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>현재 시간</h1>
    <%-- 1. 스크립트 쓰는 방법  2. 자바코드 --%>
    <%-- 
    HTTP 상태
    200 : ok
    404 : Not Found, servlet 인식 x (해결방법: 변수명 확인 하거나 서버 열고닫기 등등 해본다)
    500 : 개발자가 코드 잘못짠것
     --%>
     <%-- 
     그냥 Date, LocalTime 을 사용할 경우 HTTP 500 상태가 뜬다
     자바 코드 작성할때는 import 구문이 자동으로 완성되는데
     jsp 코드 작성할때는 자동완성이 안되서 수동으로 넣어 줘야한다
     맨 위에 <%@ page %> 형태로 추가해 주면된다
      --%>
    <%
    Date date = new Date();
    out.print(date+"<br>");

    LocalTime currTime = LocalTime.now();
    out.print(currTime+"<br>");
    %>
</body>
</html>