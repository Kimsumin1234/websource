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
<%-- http://localhost:8080/basic/add2.jsp --%>
        <div class="container mt-5">
      <form action="result2.jsp" method="post">
        <div class="row mb-3 g-3">
        <div class="col">
        <input type="text" class="form-control" id="num1" placeholder="숫자1" name="num1"/>
        </div>
        <div class="col">
        <select name="op" id="op" class="form-select">
        <option selected>사칙연산 선택</option>
        <option value="+">+</option>
        <option value="-">-</option>
        <option value="*">*</option>
        <option value="/">/</option>
        </select>
        </div>
        <div class="col">
        <input type="text" class="form-control" id="num2" placeholder="숫자2" name="num2"/>
        </div>
                 
        </div>
        <div>
          <button type="submit" class="btn btn-success">사칙연산</button>
        </div>
      </form>
    </div>
</body>
</html>