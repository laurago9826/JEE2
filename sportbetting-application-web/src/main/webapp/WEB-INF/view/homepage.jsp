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
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Sportsbetting</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="#">
						Home
						<span class="sr-only">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Events</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Language </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Action</a>
						<a class="dropdown-item" href="#">Another action</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div>
				</li>
			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
	<div class="itemContainer">
		<form:form name='f' action="save" method='POST'
			modelAttribute="player">
			<form:hidden path="id" value="${id}" />
			<div class="col-sm-12 my-1">
				<label class="sr-only" for="inlineFormInputGroupUsername">
					${namelbl} </label>
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">${namelbl}</div>
					</div>
					<form:input type="text" class="form-control" path="name"
						value="${name}"></form:input>
				</div>
			</div>
			<div class="col-sm-12 my-1">
				<label class="sr-only" for="inlineFormInputGroupUsername">${dateofblbl}</label>
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">${dateofblbl}</div>
					</div>
					<form:input type="text" class="form-control" path="birth"
						value="${birth}"></form:input>
				</div>
			</div>
			<div class="col-sm-12 my-1">
				<label class="sr-only" for="inlineFormInputGroupUsername">
					${accnumlbl} </label>
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">${accnumlbl}</div>
					</div>
					<form:input type="text" class="form-control" path="accountNumber"
						value="${accountNumber}"></form:input>
				</div>
			</div>
			<div class="col-sm-12 my-1">
				<label class="sr-only" for="inlineFormInputGroupUsername">
					${currencylbl} </label>
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">${currencylbl}</div>
					</div>
					<form:select class="custom-select" id="inputGroupSelect01"
						path="currency">
						<form:options items="${curopts}" />
					</form:select>
				</div>
			</div>
			<div class="col-sm-12 my-1">
				<label class="sr-only" for="inlineFormInputGroupUsername">
					${balancelbl} </label>
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">${balancelbl}</div>
					</div>
					<form:input type="text" class="form-control" path="balance"
						value="${balance}"></form:input>
				</div>
			</div>
			<!-- 			<button type="button" class="btn btn-primary">Primary</button> -->
			<input name="submit" class="btn btn-primary" type="submit"
				value="Login" />
		</form:form>
	</div>


	<table class="table">
		<tr>
			<td></td>
			<th class="text-left">#</th>
			<th class="text-left">Event title</th>
			<th class="text-left">Event type</th>
			<th class="text-left">Bet type</th>
			<th class="text-left">Outcome value</th>
			<th class="text-left">Outcome odd</th>
			<th class="text-left">Wager amount</th>
			<th class="text-left">Winner</th>
			<td class="text-left">Processed</td>
		</tr>
		<c:forEach var="row" items="${tableData}">
			<tr scope="col">
				<td>
					<c:out value="" />
				</td>
				<th scope="row">
					<c:out value="${row.index}" />
				</th>
								<td>
					<c:out value="${row.eventTitle}" />
				</td>
				<td>
					<c:out value="${row.eventType}" />
				</td>
				<td>
					<c:out value="${row.betType}" />
				</td>
				<td>
					<c:out value="${row.outcome}" />
				</td>
				<td>
					<c:out value="${row.outcomeOdd}" />
				</td>
				<td>
					<c:out value="${row.wagerAmount}" />
				</td>
				<td>
					<c:out value="${row.win}" />
				</td>
				<td>
					<c:out value="${row.processed}" />
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
