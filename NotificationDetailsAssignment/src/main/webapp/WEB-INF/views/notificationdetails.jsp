<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<title>Notification details</title>
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
	<div class="Container">
		<h1>Notification Details</h1>
		<h2>Add a notifications</h2>
		<form method="post" action="notificationdetails">
			<div class="Table">
				<div class="Row">
					<div class="CellNoBorder">Notification type:</div>
					<div class="CellNoBorder">
						<form:select path="notificationdetails" name="notificationTypeID">
							<form:options items="${NotificationTypeList}" />
						</form:select>
					</div>
				</div>
				<div class="Row">
					<div class="CellNoBorder">Organisation details:</div>
					<div class="CellNoBorder">
						<form:select path="notificationdetails"
							name="organisationdetailsID">
							<form:options items="${OrganisationdetailsList}" />
						</form:select>
					</div>
				</div>
				<div class="Row">
					<div class="CellNoBorder">Details:</div>
					<div class="CellNoBorder">
						<input name="details" type="text" value="" />
					</div>
				</div>
				<div class="Row">
					<div class="CellNoBorder">
						<input name="create" type="submit" value="Create" />
					</div>

				</div>
			</div>
		</form>
	</div>
	<div class="Container">
		<div style="display: block; float: left; margin: 5px; padding: 5px;">
			<div class="Container">
				<h2>Notification details history</h2>

				<div class="Table">
					<div class="Heading">
						<div class="Cell">
							<!-- Column 1 -->
						</div>
						<div class="Cell">
							<!-- Column 2 -->
						</div>
						<div class="Cell">Sl. No.</div>
						<div class="Cell">Notification code</div>
						<div class="Cell">Organisation details</div>
						<div class="Cell">Details</div>
					</div>

					<c:forEach items="${notificationdetails}" var="notificationdetail"
						varStatus="row">
						<div class="Row">
							<div class="Cell">
								<form method="post"
									action="notificationdetails/${notificationdetail.notificationDetailsID}">
									<input name="_method" type="hidden" value="delete"> <input
										name="delete" type="submit" value="Delete">
								</form>
							</div>
							<div class="Cell">
								<a class="edit-link"
									href='notificationdetails/edit/${notificationdetail.notificationDetailsID}'>Edit</a>
							</div>
							<div class="Cell">${row.index+1}.</div>
							<div class="Cell">${notificationdetail.notificationTypeCode}</div>

							<div class="Cell">${notificationdetail.organisationdetailsName}</div>
							<div class="Cell">${notificationdetail.details}</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div
			style="display: block; float: right; margin: 5px; padding: 5px; width: 20%">
			
				<div class="right-pane-widget--container">
				<ul style="display: block; list-style: none;">
					<li><a href="notificationdetails">Notification details</a></li>
					<c:if test="${user.username=='admin'}">
						<li><a href="notificationtypes">Notification types</a></li>
						<li><a href="organisationdetails">Organisation details</a></li>
						<li><a href="users">User details</a></li>
					</c:if>
				</ul>
			</div>
			
		</div>
	</div>

</body>
</html>