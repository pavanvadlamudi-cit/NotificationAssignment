<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<title>Edit Organisation details</title>
</head>
<body>
	<sec:authentication property="principal" var="user" />
	<div class="navbar-inner">

		<div class="container-fluid">

			<ul class="nav pull-right">
				<li class="navbar-link">User name: ${user.username }</li>
				<li class="navbar-link"><a href="../../../logout">Logout</a></li>
			</ul>
		</div>
	</div>


	<c:url var="saveUrl"
		value="/Notification/organisationdetails/save/${organisationdetails.organisationDetailsID}" />
	<br />
	<div class="Container">
		<div style="display: block; float: left; margin: 5px; padding: 5px;">
			<div class="Container">
				<form:form id="editorganisationdetailsForm"
					modelAttribute="organisationdetails" method="post"
					action="${saveUrl}">

					<h2>Update Organisation</h2>

					<div class="Table">
						<div class="Row">
							<div class="CellNoBorder">Organisation name:</div>
							<div class="CellNoBorder">
								<form:input path="name" value="${organisationdetails.name}" />
							</div>
						</div>

						<div class="Row">
							<div class="CellNoBorder">
								<input name="Update" type="submit" value="Save Changes" />
							</div>
							<div class="CellNoBorder">
								<input name="Cancel" type="button" value="Cancel"
									onclick="javascript:history.go(-1);" />
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		<div
			style="display: block; float: right; margin: 5px; padding: 5px; width: 20%">
			<c:if test="${user.username=='admin'}">
				<div class="right-pane-widget--container">
					<div class="right-pane-widget--container">
				<ul style="display: block; list-style: none;">
					<li><a href="../../notificationdetails">Notification
							details</a></li>
					<c:if test="${user.username=='admin'}">
						<li><a href="../../notificationtypes">Notification types</a></li>
						<li><a href="../../organisationdetails">Organisation
								details</a></li>
						<li><a href="../../users">User details</a></li>
					</c:if>
				</ul>
			</div>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>