
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/include/header.jsp"%>
<!-- Main content -->
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">List Board</h3>
		</div>
		<div class="row justify-content-between">				
			<div class="col-md-4">
			  <!--1. 글쓰기 버튼-->
			  <a href="<c:url value="/view/qna_board_write.jsp"/>" class="btn btn-success">새글 작성</a>			
			</div>				
			<div class="col-md-5">
			  <!--2. 검색 들어갈 부분-->
			  <form action="<c:url value="/qList.do"/>" method="post" name="search" class="form-inline">
			  <div class="form-group">
			    <select name="criteria" class="form-control">
				<%-- 3. 검색 조건 남아있게 하기 --%>
				<%-- - pageDto.searchDto : PageDto 안에 SearchDto 객체를 searchDto 이걸로 포함하고 있다 --%>
			      <option value="n" <c:out value="${pageDto.searchDto.criteria == null?'selected':''}"/> >-----</option>
			      <option value="title" <c:out value="${pageDto.searchDto.criteria == 'title'?'selected':''}"/> >title</option>
			      <option value="content" <c:out value="${pageDto.searchDto.criteria == 'content'?'selected':''}"/> >content</option>
			      <option value="name" <c:out value="${pageDto.searchDto.criteria == 'name'?'selected':''}"/> >name</option>
			    </select>			  
			  </div>
			  <div class="form-group">
			    <input type="text" name="keyword" value="${search.keyword}" id="" class="form-control">			  
			  </div>
			  <div class="form-group">
				<input type="submit" value="검색" class="btn btn-primary">			  
			  </div>
			  </form>
			</div>
		</div>
		<br>
		<table class="table table-bordered">
			<tr>
				<th class='text-center' style='width:100px'>번호</th>
				<th class='text-center'>제목</th>
				<th class='text-center'>작성자</th>
				<th class='text-center'>날짜</th>
				<th class='text-center' style='width:100px'>조회수</th>
			</tr>
		  <c:forEach var="dto" items="${list}">
			<tr><!-- 4. 리스트 목록 보여주기 -->
				<td class='text-center'>${dto.bno}</td><!--번호-->
				<td>
				<!-- - 제목 -->
				<c:if test="${dto.reLev!=0}">
				<c:forEach begin="0" end="${dto.reLev+1}">
				<%-- - &nbsp; : 스페이스바 한번 --%>
				&nbsp;
				</c:forEach>
				</c:if>
				<%-- 5. 조회수 업데이트까지 하고 /qRead.do 실행 --%>
				    <a href="<c:url value="/qCount.do?bno=${dto.bno}"/>">${dto.title}</a>
				</td>
				<td class='text-center'>${dto.name}</td><!--작성자-->
				<td class='text-center'>${dto.regDate}</td><!--날짜-->
				<td class='text-center'><span class="badge badge-pill badge-primary">${dto.readCount}</span></td><!-- 조회수 -->
			</tr>
		  </c:forEach>		
		</table>
		<div class="container">
		<%-- 6. 페이지 번호 --%>
			<div class="row  justify-content-md-center">

				<nav aria-label="...">
                  <ul class="pagination">

                    <c:if test="${pageDto.prev}">
                      <li class="page-item">
                        <a class="page-link" href="${pageDto.startPage-1}">Previous</a>
                      </li>
	                </c:if>

                    <%-- idx 라는 변수로 pageDto.startPage 부터 pageDto.endPage 까지 뿌려조 --%>
					<c:forEach begin="${pageDto.startPage}" end="${pageDto.endPage}" var="idx">
					<%-- 부트스트랩 에서 class 명에 active 가 붙으면 파란색으로 활성화 된다 --%>
					<%-- pageDto.searchDto.page(현재페이지) 와 idx 가 같을경우 active --%>
                      <li class="page-item <c:out value="${pageDto.searchDto.page == idx?'active':''}"/>" aria-current="page">
                        <a class="page-link" href="${idx}">${idx}</a>
                      </li>
	                </c:forEach>

                    <c:if test="${pageDto.next}">
                      <li class="page-item">
                        <a class="page-link" href="${pageDto.endPage+1}">Next</a>
                      </li>
	                </c:if>

                  </ul>
				</nav>	

			</div>
		</div>
		<div style="height:20px"></div>
	</div>	
</section>
<form action="" method="get" id="actionForm">
  <input type="hidden" name="page" value="${pageDto.searchDto.page}">
  <input type="hidden" name="amount" value="${pageDto.searchDto.amount}">
  <input type="hidden" name="criteria" value="${pageDto.searchDto.criteria}">
  <input type="hidden" name="keyword" value="${pageDto.searchDto.keyword}">
</form>
<script src="<c:url value="/js/list.js"/>"></script>
<%@include file="/include/footer.jsp"%>
