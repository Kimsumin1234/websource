<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ include file ="../include/header.jsp" %>
<%@ page import="dto.TodoDto" %>
<%
TodoDto todo = (TodoDto)request.getAttribute("todo");
%>
<h1 class="mt-5">Todo Modify</h1>
<%-- <form action="updatePro.jsp" method="post"> --%>
<form action="/update.do" method="post">
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
   <%-- checkbox 나 radio 같은경우 초기에 value 가 있어야 변경이 가능하다 --%>
   <%-- value 가 없는 경우 checkbox, radio 의 경우에는 on 값을 가지고 오게 됨 (true,false 가 아니라) --%>
   <%-- 그래서 value="true" 를 같이 지정을 해준다 --%>
   <input type="checkbox" name="completed" id="completed" class="form-check-input" name="completed" value="true" <c:out value="${todo.completed?'checked':''}" />>
 </div>
 <div class="mb-3">
   <label for="description" class="form-label">description</label>
   <%-- <textarea class="form-control" id="description" rows="3" name="description"><%=todo.getDescription()%></textarea> --%>
   <textarea class="form-control" id="description" rows="3" name="description" >${todo.description}</textarea>
 </div>
 <div>
   <button class="btn btn-primary" type="submit">수정</button>
   <%-- <a class="btn btn-danger" href="<c:url value="/view/deletePro.jsp?no=${todo.no}"/>">삭제</a> --%>
   <a class="btn btn-danger" href="<c:url value="/delete.do?no=${todo.no}"/>">삭제</a>
   <%-- a 태그에 클래스명을 버튼식으로 주면 버튼 모양으로 a 태그를 만들수있다 --%>
   <%-- <a class="btn btn-success" href="<c:url value="/view/list.jsp"/>">목록</a> --%>
   <a class="btn btn-success" href="<c:url value="/list.do"/>">목록</a>
 </div>
 <input type="hidden" name="no" value="${todo.no}">
</form>
<%@ include file ="../include/footer.jsp" %>