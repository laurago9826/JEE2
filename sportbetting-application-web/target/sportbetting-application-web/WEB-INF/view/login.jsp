<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />"
	rel="stylesheet">
<title> ${sportsbetting} </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<div class="jumbotron main-container">
		<div class="container text-container">
			<h1 class="display-4 welcome-text">${welcome}</h1>
			<p class="lead motto-text">${motto}</p>
		</div>
	</div>
	<div class="login-message">
		<span class="login-message-component blue-title"> ${login} </span>
		<span class="login-message-component"> ${orr} </span>
		<span class="login-message-component blue-title"> ${register} </span>
		<span class="login-message-component"> ${toStart} </span>
	</div>

	<div class="card border-info mb-3 login-div" style="max-width: 18rem;">
		<div class="card-header header-item">
			<span class="card-title"> ${login}</span>
		</div>
		<div class="card-body text-info">
			<form:form name='f' action="login" method='POST'
				modelAttribute="user">
				<div class="form-group">
					<form:input type="email" class="form-control"
						placeholder="${email}" path="email" />
				</div>
				<div class="form-group">
					<form:input type="password" class="form-control"
						id="exampleInputPassword1" placeholder="${password}"
						path="password" />
				</div>
				<input type="submit" class="btn btn-primary general-button"
					value="${login}" />
			</form:form>
		</div>
	</div>
</body>
</html>