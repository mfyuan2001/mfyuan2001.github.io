<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="springmvc/hello">hello world</a>
	<br/>
	ANT风格 <a href="springmvc/antStyle">ant</a>
	<br/>
	<a href="springmvc/testPathVar/12">testPathValiable</a>
	<br/>
	获取用户信息
	<a href="springmvc/testGET/620">test get</a>
	<br/>
	<form action="springmvc/testPOST" method="post">
		添加用户信息<input type="submit" value="POST">
	</form>
	<br/>
	<form action="springmvc/testDELETE/620" method="post">
		<input type="hidden" name="_method" value="DELETE"> 
		删除用户信息<input type="submit" value="DELETE">
	</form>
	<br/>
	<form action="springmvc/testPUT/620" method="post">
		<input type="hidden" name="_method" value="PUT"> 
		修改用户信息<input type="submit" value="PUT">
	</form>
	测试获取请求参数内容
	<form action="springmvc/testRequestParam" method="post">
		id:	<input type="text" name="id"/>	<br/>
		name:<input type="text" name="name"/>	<br/>
		<input type="submit" value="提交"/> 
	</form>
</body>
</html>