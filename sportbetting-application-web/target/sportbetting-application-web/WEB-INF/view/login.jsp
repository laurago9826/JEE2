<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />"
	rel="stylesheet">
<title>Getting Started: Serving Web Content</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<div class="jumbotron main-text">
		<div class="container text-container">
			<h1 class="display-4 welcome-text">Welcome to SportsBet!</h1>
			<p class="lead motto-text">Sport betting is the activity of
				predicting sports results and placing a wager on the outcome!</p>
		</div>
	</div>
	
<h1>Login</h1>

	<form:form name='f' action="login" method='POST' modelAttribute="user">
		<table>
			<tr>
				<td>User:</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="password" type="password"/></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="Login" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>