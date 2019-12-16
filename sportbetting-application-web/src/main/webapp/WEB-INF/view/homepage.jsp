<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />"
	rel="stylesheet">
<title><spring:message code="sportsBetting" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#"> <spring:message
				code="sportsBetting" />
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"> <spring:message
							code="home" />
				</a></li>
				<li class="nav-item"><a class="nav-link"> <spring:message
							code="events" />
				</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" role="button"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<spring:message code="language" />
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="?lang=en"> <spring:message
								code="english" />
						</a> <a class="dropdown-item" href="?lang=hu"> <spring:message
								code="hungarian" />
						</a>
					</div></li>
			</ul>
			<div class="btn btn-outline-dark my-2 my-sm-0 logout-btn" >
				<a href="<c:url value="/logout" />"> <spring:message
						code="logout" />
				</a>
			</div>
		</div>
	</nav>

	<div class="general-container">
		<div class="card border-info mb-3 itemContainer">
			<div class="card-header header-item my-header">
				<span class="card-title my-card-title"> <spring:message
						code="playerDetails" />
				</span>
			</div>
			<div class="card-body my-card-body">
				<form:form name='f' action="save" method='POST'
					modelAttribute="player">
					<form:hidden path="id" value="${id}"/>
					<form:hidden path="email" value="${email}"/>
					<form:hidden path="password" value="${password}"/>
					<div class="col-sm-12 my-1 input-field">
						<label class="sr-only" for="inlineFormInputGroupUsername">
							<spring:message code="name" />
						</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<spring:message code="name" />
								</div>
							</div>
							<form:input type="text" class="form-control" path="name"
								value="${name}"></form:input>
						</div>
					</div>
					<div class="col-sm-12 my-1 input-field">
						<label class="sr-only" for="inlineFormInputGroupUsername">
							<spring:message code="birth" />
						</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<spring:message code="birth" />
								</div>
							</div>
							<form:input type="date" class="form-control" path="birth"
								value="${birth}"></form:input>
						</div>
					</div>
					<div class="col-sm-12 my-1 input-field">
						<label class="sr-only" for="inlineFormInputGroupUsername">
							<spring:message code="accountNumber" />
						</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<spring:message code="accountNumber" />
								</div>
							</div>
							<form:input type="number" class="form-control"
								path="accountNumber" value="${accountNumber}"></form:input>
						</div>
					</div>
					<div class="col-sm-12 my-1 input-field">
						<label class="sr-only" for="inlineFormInputGroupUsername">
							<spring:message code="currency" />
						</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<spring:message code="currency" />
								</div>
							</div>
							<form:select class="custom-select" id="inputGroupSelect01"
								path="currency">
								<form:options items="${curopts}" />
							</form:select>
						</div>
					</div>
					<div class="col-sm-12 my-1 input-field">
						<label class="sr-only" for="inlineFormInputGroupUsername">
							<spring:message code="balance" />
						</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">
									<spring:message code="balance" />
								</div>
							</div>
							<form:input type="number" class="form-control" path="balance"
								value="${balance}"></form:input>
						</div>
					</div>
					<spring:message code="save" var="save" />
					<input name="submit"
						class="btn btn-primary general-button form-button" type="submit"
						value="${save}" />
				</form:form>
			</div>
		</div>
	</div>

	<div class="general-container">
		<div class="card border-info mb-3 itemContainer">
			<div class="card-header header-item my-header">
				<span class="card-title my-card-title"> <spring:message
						code="wagerDetails" />
				</span>
			</div>
			<div class="card-body my-card-body">
				<table class="table">
					<tr>
						<td></td>
						<th class="text-left">#</th>
						<th class="text-left"><spring:message code="eventTitle" /></th>
						<th class="text-left"><spring:message code="eventType" /></th>
						<th class="text-left"><spring:message code="betType" /></th>
						<th class="text-left"><spring:message code="outcomeValue" />
						</th>
						<th class="text-left"><spring:message code="outcomeOdd" /></th>
						<th class="text-left"><spring:message code="wagerAmount" />
						</th>
						<th class="text-left"><spring:message code="winner" /></th>
						<th class="text-left"><spring:message code="processed" /></th>
					</tr>
					<c:forEach var="row" items="${tableData.tableData}" varStatus="vs">
						<tr scope="col">
							<td>
								<div class=" ${row.classNameOnButton}">
									<form:form name='f ' action="remove" method='POST'
										modelAttribute="tableData">
										<form:hidden path="rowToDelete" value="${row.wagerId}" />
										<spring:message code="remove" var="remove" />
										<input type="submit" class="btn btn-primary general-button"
											value="${remove}" />
									</form:form>
								</div>
							</td>
							<th scope="row"><c:out value="${row.index}" /></th>
							<td><c:out value="${row.eventTitle}" /></td>
							<td><spring:message code="${row.eventType}" var="eventType" />
								<c:out value="${eventType}" /></td>
							<td><spring:message code="${row.betType}" var="betType" />
								<c:out value="${betType}" /></td>
							<td><c:out value="${row.outcome}" /></td>
							<td><c:out value="${row.outcomeOdd}" /></td>
							<td><c:out value="${row.wagerAmount}" /></td>
							<td><spring:message code="${row.win}" var="win" /> <c:out
									value="${win}" /></td>
							<td><spring:message code="${row.processed}" var="processed" />
								<c:out value="${processed}" /></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
