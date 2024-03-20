<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="dao.TodoDao" %>
<%@ page import="dto.TodoDto" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

 <%
 // 한글처리 (get/post 방식은 한글처리 해준다)
 request.setCharacterEncoding("utf-8");

// 삭제 a태그 가 보낸 no 가져오기
String no = request.getParameter("no");


// DB 작업
TodoDao dao = new TodoDao();

int result = dao.delete(no);

// 화면이동(list)
response.sendRedirect("list.jsp");

 %>