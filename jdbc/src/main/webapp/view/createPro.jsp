<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="dao.TodoDao" %>
<%@ page import="dto.TodoDto" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

 <%
 // 한글처리 (get/post 방식은 한글처리 해준다)
 request.setCharacterEncoding("utf-8");
// 사용자가 입력한 todo 가져오기
String title = request.getParameter("title");
String description = request.getParameter("description");

// DB 작업
// 사용자가 입력한 title, description 담기
// no 는 디비버 시퀀스 생성으로 자동으로 번호가 생기게 해놨음
TodoDao dao = new TodoDao();

TodoDto inserDto = new TodoDto();
inserDto.setTitle(title);
inserDto.setDescription(description);

int result = dao.insert(inserDto);

// 화면이동(list)
response.sendRedirect("list.jsp");

 %>