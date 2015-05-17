<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<title>Edit Notification types</title>
</head>
<body>
	<sec:authentication property="principal" var="user" />
	<div class="navbar-inner">
		
		<div class="container-fluid">
			
			<ul class="nav pull-right">
				<li class="navbar-link">User name: ${user.username }</li>
				<li class="navbar-link"><a href="../logout">Logout</a></li>
			</ul>
		</div>
	</div>
	<c:url var="saveUrl"
		value="/Notification/notificationtypes/save/${notificationtypes.notificationTypeID}" />

	<div class="Container">
		<form:form id="editnotificationtypesForm"
			modelAttribute="notificationtypes" method="post" action="${saveUrl}">

			<h2>Update notification type</h2>
			<div class="Table">
				<div class="Row">
					<div class="CellNoBorder">Code:</div>
					<div class="CellNoBorder">
						<form:input path="code" value="${notificationtypes.code}" />
					</div>
				</div>
				<div class="Row">
					<div class="CellNoBorder">Name:</div>
					<div class="CellNoBorder">
						<form:input path="name" value="${notificationtypes.name}" />
					</div>
				</div>
				<div class="Row">
					<div class="CellNoBorder">
						<input name="Update" type="submit" value="Save Changes" />
					</div>
					<div class="CellNoBorder">
						<input name="Cancel" type="button" value="Cancel" onclick="javascript:history.go(-1);" />
					</div>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>