<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- next.jsp --%>
<%
// 사용자가 보낸 값 가져오기
// id , name, age

// get/post 방식 일때 가져오는 방법
String id = request.getParameter("id");
String name = request.getParameter("name");
String age = request.getParameter("age");

// request scope 방식
// 타입 변환
// String id = (String)request.getAttribute("id");
// String name = (String)request.getAttribute("name");
// String age = (String)request.getAttribute("age");

// pageContext.forward("next2.jsp");



// session scope 방식
// 타입변환
// String id = (String)session.getAttribute("id");
// String name = (String)session.getAttribute("name");
// String age = (String)session.getAttribute("age");
%>
<h3>next.jsp 페이지</h3>
<h3>id : <%=id%></h3>
<h3>name : <%=name%></h3>
<h3>age : <%=age%></h3>
<h3>
<a href="next2.jsp">다음 페이지</a>
</h3>