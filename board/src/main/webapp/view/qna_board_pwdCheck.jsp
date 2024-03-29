<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/include/header.jsp"%>
<!-- Main content -->
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">Password Check</h3>
		</div>
		<div style="height:20px"></div>
		<form name="pwdCheck" method="post" action="<c:url value="/qDelete.do"/>">
			<div class="box-body">
				<div class="form-group">
					<input type="password" name="password" class="form-control" size="10" maxlength='10'>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">확인</button>
				</div>
			</div>
			<%-- setAttribute() 한것만 ${} 로 가져오고 아닌애들은 <%=request.getParameter()%> 를 사용 --%>
			<%-- 페이지 개념 추가후 요소 추가 --%>
			<input type="hidden" name="bno" value="<%=request.getParameter("bno")%>">
			<input type="hidden" name="page" value="<%=request.getParameter("page")%>">
			<input type="hidden" name="amount" value="<%=request.getParameter("amount")%>">
			<input type="hidden" name="criteria" value="<%=request.getParameter("criteria")%>">
			<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
		</form>
	</div>
</section>
<%@include file="/include/footer.jsp"%>
