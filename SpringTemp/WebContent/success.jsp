<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	this is page success
	<br/>
	Form request Scope name:${requestScope.name}
	<br/>
	Form request Scope age:${requestScope.age}
	<br/>
	Form request Scope email:${requestScope.email}
	<br/>
	Form request Scope city:${requestScope.city}
	<br/>
	Form Session Scope city:${sessionScope.city}
	<a href="testResult">testResult</a>
</body>
</html>