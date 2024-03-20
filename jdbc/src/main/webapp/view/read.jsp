<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ include file ="../include/header.jsp" %>
<%@ page import="dto.TodoDto" %>
<%
// ${} 이방식만 사용한다면 TodoDto todo = (TodoDto)request.getAttribute("todo"); 생략가능
TodoDto todo = (TodoDto)request.getAttribute("todo");
%>
<h1 class="mt-5">Todo View</h1>
<form action="" method="post">
 <div class="mb-3">
   <label for="title" class="form-label">title</label>
   <%-- <input type="text" class="form-control" id="title" placeholder="title" name="title" value="<%=todo.getTitle()%>"> --%>
   <%-- readonly : 읽는거만 가능 수정x --%>
   <%-- ${todo.title} : EL --%>
   <input type="text" class="form-control" id="title" placeholder="title" name="title" value="${todo.title}" readonly>
 </div>
  <div class="mb-3">
   <label for="createdAt" class="form-label">createdAt</label>
   <%-- <input type="text" class="form-control" id="createdAt" placeholder="createdAt" name="createdAt" value="<%=todo.getCreatedAt()%>"> --%>
   <input type="text" class="form-control" id="createdAt" placeholder="createdAt" name="createdAt" value="${todo.createdAt}" readonly>
 </div>
   <div class="mb-3">
   <label for="completed" class="form-check-label">completed</label>
   <%-- completed 가 true 면 check 표시 --%>
   <%-- disabled : 수정x --%>
   <input type="checkbox" name="completed" id="completed" class="form-check-input" name="completed" disabled <c:out value="${todo.completed?'checked':''}" />>
 </div>
 <div class="mb-3">
   <label for="description" class="form-label">description</label>
   <%-- <textarea class="form-control" id="description" rows="3" name="description"><%=todo.getDescription()%></textarea> --%>
   <textarea class="form-control" id="description" rows="3" name="description" readonly>${todo.description}</textarea>
 </div>
 <div>
   <%-- <a class="btn btn-primary" href="modifyPro.jsp?no=${todo.no}">수정</a> --%>
   <a class="btn btn-primary" href="<c:url value="/modify?no=${todo.no}"/>">수정</a>
   <%-- a 태그에 클래스명을 버튼식으로 주면 버튼 모양으로 a 태그를 만들수있다 --%>
   <%-- <a class="btn btn-success" href="<c:url value="/view/list.jsp"/>">목록</a> --%>
   <a class="btn btn-success" href="<c:url value="/list"/>">목록</a>
 </div>
</form>
<%@ include file ="../include/footer.jsp" %>