<!DOCTYPE HTML>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/styles.css" />"
	rel="stylesheet">
<title>${sportsbetting}</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">${sportsbetting}</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link"> ${home} </a>
				</li>
				<li class="nav-item">
					<a class="nav-link">${events}</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> ${language} </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item"> ${english} </a>
						<a class="dropdown-item"> ${hungarian} </a>
					</div>
				</li>
			</ul>
			<form:form class="form-inline my-2 my-lg-0" action="logout"
				method='POST'>
				<input type="submit"
					class="btn btn-outline-dark my-2 my-sm-0 logout-btn" type="submit"
					value="Logout" />
			</form:form>
		</div>
	</nav>

	<div class="general-container">
		<div class="card border-info mb-3 itemContainer">
			<div class="card-header header-item my-header">
				<span class="card-title my-card-title"> ${playerDetails} </span>
			</div>
			<div class="card-body my-card-body">
				<form:form name='f' action="save" method='POST'
					modelAttribute="player">
					<form:hidden path="id" value="${id}" />
					<div class="col-sm-12 my-1 input-field">
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
					<div class="col-sm-12 my-1 input-field">
						<label class="sr-only" for="inlineFormInputGroupUsername">${birthlbl}</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">${birthlbl}</div>
							</div>
							<form:input type="date" class="form-control" path="birth"
								value="${birth}"></form:input>
						</div>
					</div>
					<div class="col-sm-12 my-1 input-field">
						<label class="sr-only" for="inlineFormInputGroupUsername">
							${accountNumberlbl} </label>
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">${accountNumberlbl}</div>
							</div>
							<form:input type="number" class="form-control" path="accountNumber"
								value="${accountNumber}"></form:input>
						</div>
					</div>
					<div class="col-sm-12 my-1 input-field">
						<label class="sr-only" for="inlineFormInputGroupUsername">
							${currency} </label>
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">${currency}</div>
							</div>
							<form:select class="custom-select" id="inputGroupSelect01"
								path="currency">
								<form:options items="${curopts}" />
							</form:select>
						</div>
					</div>
					<div class="col-sm-12 my-1 input-field">
						<label class="sr-only" for="inlineFormInputGroupUsername">
							${balancelbl} </label>
						<div class="input-group">
							<div class="input-group-prepend">
								<div class="input-group-text">${balancelbl}</div>
							</div>
							<form:input type="number" class="form-control" path="balance"
								value="${balance}"></form:input>
						</div>
					</div>
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
				<span class="card-title my-card-title"> ${playerDetails} </span>
			</div>
			<div class="card-body my-card-body">
				<table class="table">
					<tr>
						<td></td>
						<th class="text-left">#</th>
						<th class="text-left">${eventTitle}</th>
						<th class="text-left">${eventType}</th>
						<th class="text-left">${betType}</th>
						<th class="text-left">${outcomeValue}</th>
						<th class="text-left">${outcomeOdd}</th>
						<th class="text-left">${wagerAmount}</th>
						<th class="text-left">${winner}</th>
						<th class="text-left">${processedd}</th>
					</tr>
					<c:forEach var="row" items="${tableData.tableData}" varStatus="vs">
						<tr scope="col">
							<td>
								<div class=" ${row.classNameOnButton}">
									<form:form name='f ' action="remove" method='POST'
										modelAttribute="tableData">
										<form:hidden path="rowToDelete" value="${row.wagerId}" />
										<input type="submit"
											class="btn btn-primary general-button"
											value="${remove}" />
									</form:form>
								</div>
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
			</div>
		</div>
	</div>
</body>
</html>
