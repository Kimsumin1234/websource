<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- 숫자 세자리마다 쉼표 찍기위해서 코드 추가 --%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/include/header.jsp"%>
<h3 class="border-bottom mb-3">도서목록</h3>
<table class="table table-bordered">
  <thead>
    <tr class="table-success">
      <th scope="col" class="text-center">code</th>
      <th scope="col" class="text-center">title</th>
      <th scope="col" class="text-center">writer</th>
      <th scope="col" class="text-center">price</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach var="dto" items="${list}">
    <tr>
      <th scope="row" class="text-center">${dto.code}</th>
      <td><a href="<c:url value="/read.do?code=${dto.code}"/>" class="text-decoration-none text-reset">${dto.title}</a></td>
      <td class="text-center">${dto.writer}</td>
      <td class="text-end">
      <%-- 숫자 세자리마다 쉼표 찍기위해서 코드 추가 --%>
       <fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.price}" />
      </td>
    </tr>
   </c:forEach>
  </tbody>
</table>
<%@ include file="/include/section.jsp"%>
<%@ include file="/include/footer.jsp"%>