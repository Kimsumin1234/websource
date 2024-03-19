<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="dao.TodoDao" %>
<%@ page import="dto.TodoDto" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

 <%
 // 한글처리 (get/post 방식은 한글처리 해준다)
 request.setCharacterEncoding("utf-8");

String completed = request.getParameter("completed");
String description = request.getParameter("description");
String no = request.getParameter("no");


// DB 작업
TodoDao dao = new TodoDao();
TodoDto updateDto = new TodoDto();
updateDto.setCompleted(Boolean.parseBoolean(completed));
updateDto.setDescription(description);
updateDto.setNo(Integer.parseInt(no));

int result = dao.update(updateDto);

// 화면이동(list)
response.sendRedirect("list.jsp");

 %>