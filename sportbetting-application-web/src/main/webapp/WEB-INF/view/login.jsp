<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />"
	rel="stylesheet">
<title><spring:message code="sportsBetting" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<div class="jumbotron main-container">
		<div class="container text-container">
			<h1 class="display-4 welcome-text">
				<spring:message code="welcome" />
			</h1>
			<p class="lead motto-text">
				<spring:message code="motto" />
			</p>
		</div>
	</div>
	<div class="login-message">
		<span class="login-message-component blue-title"> <spring:message
				code="login_verb" />
		</span> <span class="login-message-component"> <spring:message
				code="or" />
		</span> <span class="login-message-component blue-title"> <spring:message
				code="register" />
		</span> <span class="login-message-component"> <spring:message
				code="toStart" />
		</span>
	</div>

	<div class="card border-info mb-3 login-div" style="max-width: 18rem;">
		<div class="card-header header-item">
			<span class="card-title"> <spring:message code="login" />
			</span>
		</div>
		<div class="card-body text-info">
			<form:form name='f' action="login" method='POST'
				modelAttribute="user">
				<div class="form-group">
					<spring:message code="email" var="email" />
					<form:input type="email" class="form-control"
						placeholder="${email}" path="email" />
				</div>
				<div class="form-group">
					<spring:message code="password" var="password" />
					<form:input type="password" class="form-control"
						id="exampleInputPassword1" placeholder="${password}"
						path="password" />
				</div>
				<spring:message code="login" var="login" />
				<input type="submit" class="btn btn-primary general-button"
					value="${login}" />
<%-- 				<input type="hidden" name="${_csrf.parameterName}" --%>
<%-- 					value="${_csrf.token}" /> --%>
			</form:form>
		</div>
	</div>
</body>
</html>