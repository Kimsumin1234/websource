<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%-- jsp 주석

자바코드는 <% %> 안에 작성(위치는 상관없음,body 쪽이든 head 쪽이든... 상관 x)
자바코드를 화면 출력 할때 : <%= %> (= <% out.print(); %> )
 --%>

<%-- 브라우저는 자바코드를 해석을 못해서 톰캣이 해석을 해줘서 브라우저에 보내준다 F12 Elements 띄워보면 코드가 안나오고 해석한 결과만 나옴 --%>

<%--
 <%
 for(int i=1;i<11;i++){
out.print(i);
 }
 %>
  --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
<%
// JSP 내장객체
// - 변수명이 고정이다 servlet 처럼 변수명을 변경할수없다
//  1) HttpServletRequest request(변수명)
//  2) HttpServletResponse response(변수명)
//  3) JspWriter out(변수명) = (servlet)PrintWriter out
request.setCharacterEncoding("utf-8");

 String id = request.getParameter("id");
 String name = request.getParameter("name");
 String dogs[] = request.getParameterValues("dog");
 // res.setContentType("text/html;charset=utf-8"); 이 코드는 맨위에 써져있다
%>
<ul>
<li>id : <%=id%></li>
<li>name : <%=name%></li>
<%
 for (String dog : dogs) {
            out.print("<li>dog : " + dog + "</li>");
        }
%>
</ul>
</body>
</html>