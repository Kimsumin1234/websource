<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/include/header.jsp"%>
<!-- Main content -->
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">Board Modify</h3>
		</div>
		<div style="height:20px"></div>
		<form action="<c:url value="/qUpdate.do"/>" method="post" role="form">
			<div class="box-body">
				<div class="form-group row">
					<label for="name" class="col-sm-2 col-form-label">글쓴이</label>
					<div class="col-sm-10" >
					<input type="text" name="name" size="10" class="form-control" readonly maxlength='10' value="${dto.name}">
					</div>
				</div>
				<div class="form-group row">
					<label for="title" class="col-sm-2 col-form-label">제목</label>
					<div class="col-sm-10">
						<input type="text" name="title" size="50" class="form-control"	maxlength='100' value="${dto.title}">
					</div>
				</div>
				<div class="form-group row">
					<label for="content" class="col-sm-2 col-form-label">내용</label>
					<div class="col-sm-10">
						<textarea name='content' cols='60' class="form-control" rows='15'>${dto.content}</textarea>
					</div>
				</div>
				<div class="form-group row">
					<label for="name" class="col-sm-2 col-form-label">비밀번호</label>
					<div class="col-sm-10">
						<input type="password" name="password" class="form-control" size="10" maxlength='10'>
					</div>
				</div>
				<div class="form-group row">
					<label for="filename" class="col-sm-2 col-form-label">파일첨부</label>
					<div class="col-sm-10">
					  <a href="<c:url value="/view/download.jsp?fileName=${dto.attach}"/>">${dto.attach}</a>
					</div>
				</div>
				<div style="height:20px"></div>
				<div class="box-footer text-center">
					<button type="submit" class="btn btn-primary">수정</button>
					<button type="button" class="btn btn-danger">목록</button>
				</div>
				<div style="height:20px"></div>
			</div>
			<input type="hidden" name="bno" value="${dto.bno}">
			<input type="hidden" name="page" value="${searchDto.page}">
            <input type="hidden" name="amount" value="${searchDto.amount}">
            <input type="hidden" name="criteria" value="${searchDto.criteria}">
            <input type="hidden" name="keyword" value="${searchDto.keyword}">
		</form>
	</div>
</section>
<%-- 수정도 목록 버튼이 실행되기 위한 값들 추가 --%>
<%-- 위에 있는 input 이랑 동일한걸로 복사 붙여넣기 해준다 --%>
<form action="<c:url value="/qList.do"/>" method="get" id="actionForm">
			<input type="hidden" name="page" value="${searchDto.page}">
            <input type="hidden" name="amount" value="${searchDto.amount}">
            <input type="hidden" name="criteria" value="${searchDto.criteria}">
            <input type="hidden" name="keyword" value="${searchDto.keyword}">
</form>
<script src="<c:url value="/js/modify.js"/>"></script>
<%@include file="/include/footer.jsp"%>
