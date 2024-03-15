<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<% pageContext.include("top.jsp"); %>

<%-- 
디자인 템플릿
content.jsp 와 login.jsp 는 navbar 디자인을 서로 공유하고있다
동일한 디자인을 불러다가 사용할수있다
상단 navbar 내용을 top.jsp ===> <% pageContext.include("top.jsp"); %>
하단 script 내용은 bottom.jsp ===> <% pageContext.include("bottom.jsp"); %>
이렇게 공유하는 디자인만 따로 만들어서 가운데 내용만 바꾸고 공통디자인은 불러다 쓸수있다
 --%>
<div class="container">
  <div class="mb-3 row">
    <label for="email" class="col-sm-2 col-form-label">Email</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="staticEmail" value="email@example.com" name="email">
    </div>
  </div>
  <div class="mb-3 row">
    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword" name="password">
    </div>
  </div>
</div>

<% pageContext.include("bottom.jsp"); %>
