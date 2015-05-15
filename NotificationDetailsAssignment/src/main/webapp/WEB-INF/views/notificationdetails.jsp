<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
	User name:
	<sec:authentication property="principal" var="user" />
	${user.username }
	<a href="../logout">Logout</a>
	<h1>Notification Details</h1>

	<form method="post" action="notificationdetails">
		<div class="Table">
			<div class="Row">
				<div class="CellNoBorder">
					Notification type:
				</div>
				<div class="CellNoBorder">
					<form:select path="notificationdetails"
					name="notificationTypeID">
					<form:options items="${NotificationTypeList}" />
				</form:select>
				</div>
			</div>
			<div class="Row">
				<div class="CellNoBorder">
					Organisation details:
				</div>
				<div class="CellNoBorder">
				<form:select
					path="notificationdetails" name="organisationdetailsID">
					<form:options items="${OrganisationdetailsList}" />
				</form:select>
				</div>
			</div>
			<div class="Row">
				<div class="CellNoBorder">
					Details:
				</div>
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

	<div class="Table">
		<div class="Heading">
			<div class="Cell"><!-- Column 1 --></div>
			<div class="Cell">ID#</div>
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

				<div class="Cell">${row.index+1}.</div>
				<div class="Cell">${notificationdetail.notificationTypeCode}</div>

				<div class="Cell">${notificationdetail.organisationdetailsName}</div>
				<div class="Cell">${notificationdetail.details}</div>


			</div>
		</c:forEach>
	</div>

</body>
</html>