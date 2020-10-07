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
	<form action="springmvc/testPojo" method="post">
		id:	<input type="text" name="id"/>	<br/>
		name:<input type="text" name="name"/>	<br/>
		city:	<input type="text" name="adress.city"/>	<br/>
		catt:<input type="text" name="adress.catt"/>	<br/>
		<input type="submit" value="提交"/> 
	</form>
	<a href="springmvc/testRequestHander">testRequestHander</a><br/>
	<a href="springmvc/testCookieValue">testCookieValue</a><br/>
	<a href="springmvc/testServletAPI">testServletAPI</a><br/>
	<a href="springmvc/testModelAndView">testModelAndView</a><br/>
	<a href="springmvc/testMap">testMap</a><br/>
	<a href="springmvc/testModel">testModel</a><br/>
	<a href="springmvc/testModelMap">testModelMap</a><br/>
	
	<form action="springmvc/testModelAttribute">
		id:	<input type="text" name="id" value="101"/>	<br/>
		name:<input type="text" name="name" value="tom"/>	<br/>
		<input type="submit" value="UPDATE"/> 
	</form>
	<br/>
	<a href="springmvc/testViewAndViewResolver">testViewAndViewResolver</a>
	<br/>
	<a href="springmvc/testABC">testABC</a>
</body>
</html>