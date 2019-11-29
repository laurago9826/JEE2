<!DOCTYPE HTML>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<link href="<c:url value="/resources/css/styles.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<title>Getting Started: Serving Web Content</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<p class="test">"Hello, ${name} !"</p>
	<div class="alert alert-primary" role="alert">A simple primary
		alert—check it out!</div>
</body>
</html>