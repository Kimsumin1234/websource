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

// forward()
// http://localhost:8080/object/forward.jsp 로 요청
// forward("content.jsp"); 에 있는 페이지가 화면에 보여짐
// 주소는 여전히 http://localhost:8080/object/forward.jsp 인 상태
// 주소가 가르키는 페이지 != 화면에 나오는 페이지

pageContext.forward("content.jsp");
%>