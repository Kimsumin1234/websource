<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%
// JSP 내장객체
// - 변수명이 고정이다 servlet 처럼 변수명을 변경할수없다
//  1. HttpServletRequest request(변수명)
//  2. HttpServletResponse response(변수명)
//  3. JspWriter out(변수명) = (servlet)PrintWriter out(변수명)
//  4. PageContext pageContext : jsp 페이지에 대한 정보를 저장하고 있는 객체
//                1) forward()
//                2) include("포함할 페이지 경로") : ex) 디자인 탬플릿 구성 시 사용

//  5. HttpSession session
//           - 세션 : 특정 서버와 연결된 상태  ex) 내가 네이버사이트에 들어가면 네이버사이트와 세션이 1개 연결되있다
//           - https or http 프로토콜의 특징
//              1) 무상태(stateless) 이다. (반대는 상태(stateful))
//              2) 무상태 : 클라이언트 상태를 저장하지 않음
//                          클라이언트가 요청한걸 응답해주고 이러한 내용을 따로 저장은 않한다

//           - 상태 저장이 필요한 경우 (로그인, 장바구니 ...)
//              1) 서버측 에 저장 - 세션 (동일한 사이트에 세션을 연결했을때 크롬 새탭은 같은세션, 크롬과엣지는 다른세션)
//              2) 클라이언트 측 에 저장 - 쿠키 / 브라우저 저장
//              3) session.setAttribute(), session.getAttribute() 은 상태저장이 필요한 경우에만 사용한다
//                 서버에 부담도 되고 굳이 상태저장이 필요없는데 편하다는 이유로 사용하지는 않는다
%>
<h2>세션 테스트</h2>
<ul>
<%-- 
isNew() : 새로 생성된 세션인지 판별 (true, false 로 알려준다)
크롬의 경우 같은계정으로 열었던 브라우저창은 모두 같은 세션ID 이다
 --%>
<li>isNew() : <%=session.isNew() %></li> 
<li>생성시간 : <%=session.getCreationTime() %></li>
<li>최종접속시간 : <%=session.getLastAccessedTime() %></li>
<li>세션ID : <%=session.getId() %></li>
</ul>
</body>
</html>