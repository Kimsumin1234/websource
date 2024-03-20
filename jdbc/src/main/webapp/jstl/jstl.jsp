<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file ="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 class="mt-5">JSTL</h1>
<%-- <% %>이거를 걷어내기 위해서 jstl 과 EL 을 사용 자바코드를 태그처럼 사용하기위한 방법  --%>

<%-- 
EL : jsp 에서 $로 표현하는방법
     getAttribute("name") == ${name} (EL 을 사용하면 이렇게 쓸수있다)
     getAttribute("loginDto"), <%=loginDto.getName() %> == ${loginDto.name}
 --%>
 
<%-- jstl : <c:if --%>

<%-- c:if (else 개념은 없고 if 를 두번 써야한다) --%>
<c:if test="${5<10}"> 
<h4>5는 10보다 작다</h4>
</c:if>
<c:if test="${6+3==9}"> 
<h4>6 + 3 은 9 이다</h4>
</c:if>
<%-- c:choose : if~else 개념 --%>
<c:choose>
<c:when test="${5+10==50}">
<h4>5 + 10 은 50 이다</h4>
</c:when>
<c:otherwise>
<h4>5 + 10 은 50 이 아니다</h4>
</c:otherwise>
</c:choose>
<%-- c:forEach --%>
<c:forEach var="test" begin="1" end="10" step="2">
<b>${test}</b>
</c:forEach>

<%-- c:out (out.print 같은것) --%>
<%-- value에 조건을 줘서 화면에 출력 할수있다 --%>
<%-- <c:out value="${flag?"OK":"Error"}"></c:out> --%>
<%-- 태그사이에 내용이 없다면 안에다가 닫는태그 가능 --%>
<%-- <c:out value="${flag?"OK":"Error"}"/> --%>
<%@ include file ="../include/footer.jsp" %>
