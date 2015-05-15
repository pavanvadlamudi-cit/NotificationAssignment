<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<style type="text/css">
.Table {
	display: table;
}

.Title {
	display: table-caption;
	text-align: center;
	font-weight: bold;
	font-size: larger;
}

.Heading {
	display: table-row;
	font-weight: bold;
	text-align: center;
}

.Row {
	display: table-row;
}

.Cell {
	display: table-cell;
	border: solid;
	border-width: thin;
	padding-left: 5px;
	padding-right: 5px;
}
</style>
</head>
<body>
	<sec:authentication property="principal" var="user" />
	${user.username }
	<a href="../logout">Logout</a>
	<h1>Notification Details</h1>

	<form method="post" action="notificationdetails">
		<ul style="list-style: none">
			<!-- 			<li>Notification type: <input name="notificationTypeID" -->
			<!-- 				type="text" value="" /> -->
			<!-- 			</li> -->
			<li>Notification type: 
			<form:select path="notificationdetails" name="notificationTypeID">
					<form:options items="${NotificationTypeList}" />
			</form:select>
			</li>
			<li>Organisation details: <input name="organisationdetailsID"
				type="text" value="" />
			</li>
			<li>Details: <input name="details" type="text" value="" />
			</li>
		</ul>

		<input name="create" type="submit" value="Create">
	</form>

	<div class="Table">

		<c:forEach items="${notificationdetails}" var="notificationdetail"
			varStatus="row">
			<div class="Row">
				<form method="post"
					action="notificationdetails/${notificationdetail.notificationDetailsID}">
					<div class="Cell">
						<input name="_method" type="hidden" value="delete">
					</div>
					<div class="Cell">
						<input name="delete" type="submit" value="Delete">
						${row.index}. ${notificationdetail.notificationTypeID}
					</div>

					<div class="Cell">${notificationdetail.organisationdetailsID}</div>
					<div class="Cell">${notificationdetail.details}</div>
				</form>
			</div>
		</c:forEach>
	</div>

</body>
</html>