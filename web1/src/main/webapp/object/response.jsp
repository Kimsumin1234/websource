<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%
// JSP 내장객체
// - 변수명이 고정이다 servlet 처럼 변수명을 변경할수없다
//  1. HttpServletRequest request(변수명)
//  2. HttpServletResponse response(변수명)
//  3. JspWriter out(변수명) = (servlet)PrintWriter out
//  4. PageContext pageContext : jsp 페이지에 대한 정보를 저장하고 있는 객체
//                1) forward()
//                2) include("포함할 페이지 경로") : ex) 디자인 탬플릿 구성 시 사용

// ★★★ sendRedirect() vs forward() 구별 잘하기 ★★★
// - sendRedirect() : 주소변경
// - forward() : 주소변경 x

// sendRedirect()
// - http://localhost:8080/object/response.jsp 이걸 보여줘 하고 요청을 했는데
// - sendRedirect("경로") 로 인해서 "경로" 이 경로로 응답해준다
// response.sendRedirect("/basic/input.html");
// url 도 이동된 주소로 변경됨
response.sendRedirect("https://www.naver.com");
%>