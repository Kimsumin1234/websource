<%-- jsp 임 을 나타내는 식별 코드 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <title>Document</title>
</head>
<body>
<%-- http://localhost:8080/basic/add.jsp --%>
<%-- 
서버에서 더하기 결과를 연산해서 브라우저로 보여준다 - 백엔드
자바스크립트로도 더하기 연산해서 브라우저로 보여줄수있다 - 프론트엔드
 --%>
        <div class="container">
      <form action="result1.jsp" method="post">
        <div class="mb-3">
          <input type="text" class="form-control" id="num1" placeholder="숫자1" name="num1" size="5"/>
          <input type="text" class="form-control" id="num2" placeholder="숫자2" name="num2" size="5"/>
        </div>
        <div>
          <button type="submit" class="btn btn-success">더하기</button>
        </div>
      </form>
    </div>
</body>
</html>