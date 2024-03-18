<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- index 이름으로 된 파일은 폴더명을 따로 안적어도 된다 --%>
<%-- http://localhost:8080/ --%>
<%-- webapp 폴더에는 html, jsp --%>
<%-- java 폴더에는 java --%>
<%@ page import="member.MemberDTO" %>
<%
// 세션 scope 에 담긴 속성 사져오기
MemberDTO loginDto = (MemberDTO)session.getAttribute("loginDto");
%>
<html>
<body>
<h2>Hello World!</h2>
<h3>안녕하세요</h3>
<ul>
<%-- <% if(loginDto.getUserId()==null) {%> --%>
<%-- NullPointerException: Cannot invoke "member.MemberDTO.getUserId()" because "loginDto" is null --%>
<%-- "loginDto" is null 이라서 .getUserId() 을 부를수 없다 --%>
<% if(loginDto==null) {%>
<li><a href="/member/login.jsp">로그인</a></li>
<% } else { %>
<li><a href="/logout">로그아웃</a></li>
<% } %>
<li><a href="/basic/add.jsp">계산기(덧셈)</a></li>
<li><a href="/basic/add2.jsp">계산기(사칙연산)</a></li>
</ul>
</body>
</html>
