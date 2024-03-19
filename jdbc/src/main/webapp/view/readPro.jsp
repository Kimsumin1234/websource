<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="dao.TodoDao" %>
<%@ page import="dto.TodoDto" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

 <%
 // 한글처리 (get/post 방식은 한글처리 해준다)
 request.setCharacterEncoding("utf-8");
// 제목 클릭시 no 가져오기
String title = request.getParameter("title");
String no = request.getParameter("no");


// DB 작업
TodoDao dao = new TodoDao();
TodoDto todo = dao.getRow(no);

// todo 를 read.jsp 에 보여주기
request.setAttribute("todo",todo);

// 화면이동(read)
pageContext.forward("read.jsp");

 %>