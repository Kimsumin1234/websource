<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%-- 
<% %> : 자바코드 입력
<%= %> : 변수를 화면에 출력(html 코드랑 섞일 때 사용)
<%! %> : 자바코드 작성 => 변수선언, 메소드선언 할때 사용 (jsp 에서는 잘 사용하지않음)
 --%>

<%!
private String getCookieValue(Cookie[] cookies,String name){
    if(cookies==null) return null;
    for(Cookie c:cookies){
        if(c.getName().equals(name)) return c.getValue();
    }
    return null;
}
%>

<%
// 쿠키 가져오기
Cookie[] cookies = request.getCookies();
%>

<h4>이름 : <%=getCookieValue(cookies,"name")%></h4>
<h4>성별 : <%=getCookieValue(cookies,"gender")%></h4>
<h4>나이 : <%=getCookieValue(cookies,"age")%></h4>