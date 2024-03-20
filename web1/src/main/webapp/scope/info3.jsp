<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- info3.jsp 페이지 --%>

<%-- 자바 코드 --%>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String name = request.getParameter("name");
String age = request.getParameter("age");
%>

<%-- 브라우저에 보여줄값 html 형태로 작성 --%>
<%-- <h2>info3.jsp 페이지</h2>
<h3>id : <%=id%></h3>
<h3>name : <%=name%></h3>
<h3>age : <%=age%></h3> --%>

<%
request.setAttribute("id", id);
request.setAttribute("name", name);
request.setAttribute("age", age);

pageContext.forward("next.jsp");

// session scope 사용
// session.setAttribute("id", id);
// session.setAttribute("name", name);
// session.setAttribute("age", age);
%>
<%-- <h2>info3.jsp 페이지</h2>
<h3>id : <%=id%></h3>
<h3>name : <%=name%></h3>
<h3>age : <%=age%></h3> --%>
<%-- post 방식으로 다음 페이지에 데이터 전달 방법 --%>
    <%-- <form action="next.jsp" method="post">
      <div>
        <label for="id">id</label>
        <input type="text" name="id" id="id" value="<%=id%>" />
      </div>
      <div>
        <label for="name">name</label>
        <input type="text" name="name" id="name" value="<%=name%>" />
      </div>
      <div>
        <label for="age">age</label>
        <input type="text" name="age" id="age" value="<%=age%>" />
      </div>
      <div>
        <button type="submit">전송</button>
      </div>
    </form> --%>

<%-- get 방식으로 다음 페이지에 데이터 전달 방법  --%>
<%-- <h3>
<a href="next.jsp?id=<%=id%>&name=<%=name%>&age=<%=age%>">다음 페이지</a>
</h3>  --%>

<h3>
<a href="next.jsp">다음 페이지</a>
</h3>