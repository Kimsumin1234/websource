<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%
request.setCharacterEncoding("utf-8");
// 사용자가 보낸 값 가져오기
// id , name, age
// jsp 파일 자체에 request, response 가 내장이 되있기 때문에 그냥 사용할수있다
String id = request.getParameter("id");
String name = request.getParameter("name");
String age = request.getParameter("age");
%>

<%-- <h3>id : <%=id%></h3>
<h3>name : <%=name%></h3>
<h3>age : <%=age%></h3> --%>

<%-- HttpServletRequest : 이 객체에 유효범위가 제한적이다
request.getParameter() : 그래서 사용자의 입력값을 가지고 와서 사용 할수있는 범위가 제한되어있다
                         form 태그에 action 속성에 값으로 사용된 페이지까지만 가능 (next.jsp 까지 적용이 안된다)

info.jsp 가 알고있는 값(사용자입력값, DB값) 을 다른 페이지랑 공유
 1) get/post 방식 : a href="next.jsp?id=<%=id%>&name=<%=name%>&age=<%=age%>"  (info.jsp a태그가 보낸 값이다)
                    중요한 정보 (ID,비밀번호) 같은 경우 주소창에 그대로 노출이 되니 이방법을 사용하지 않는다

 2) scope 이용
  - page : 현재 페이지
  - request : HttpServletRequest 유효범위 동일 (forward()와 같이 써야 의미가있다)
  - session : HttpSession 유효범위(브라우저를 닫기 전까지)
  - application : 톰캣 서버 범위
  
  - scope 메소드 : setAttribute(), getAttribute()
  request.setAttribute("key", 값), request.getAttribute("key")
  session.setAttribute(), session.getAttribute() --%>

<%
// request scope 사용
// request 방식으로 데이터를 다른 페이지로 넘길려면 forward() 를 사용해야한다
request.setAttribute("id", id);
request.setAttribute("name", name);
request.setAttribute("age", age);

// info.jsp 에 부여된 request 를 next.jsp 에 넘겨주는것
// info.jsp 에서 할 수 있는 작업들을 next.jsp 에서 할 수 있게 됨
pageContext.forward("next.jsp");



// session scope 사용
// session.setAttribute("id", id);
// session.setAttribute("name", name);
// session.setAttribute("age", age);

%>

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

<%-- <h3>
<a href="next.jsp">다음 페이지</a>
</h3> --%>