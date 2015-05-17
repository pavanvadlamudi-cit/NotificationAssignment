<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<title>Edit Notification details</title>
</head>
<body>
	User name:
	<sec:authentication property="principal" var="user" />
	${user.username }
	<a href="../logout">Logout</a>
		<c:url var="saveUrl" value="/Notification/notificationdetails/save/${notificationdetails.notificationDetailsID}" />
		<br/>
		${saveUrl}
		id: ${notificationdetails.notificationDetailsID}
		 <form:form id="editnotificationdetailsForm" modelAttribute="notificationdetails" 
		 method="post"  
   action="${saveUrl}">  
   		
		<div class="Table">
			<div class="Row">
				<div class="CellNoBorder">
					Notification type:
				</div>
				<div class="CellNoBorder">
				
					${notificationdetails.notificationTypeCode}
				</div>
			</div>
			<div class="Row">
				<div class="CellNoBorder">
					Organisation details:
				</div>
				<div class="CellNoBorder">
					${notificationdetails.organisationdetailsName}
				</div>
			</div>
			<div class="Row">
				<div class="CellNoBorder">
					Details:
				</div>
				<div class="CellNoBorder">
					<form:input path="details" value="${notificationdetail.details}" />
					${notificationdetail.details}
				</div>
			</div>
			<div class="Row">
				<div class="CellNoBorder">
					<input name="Update" type="submit" value="Save Changes" />
				</div>
				
			</div>
		</div>
			</form:form>
	
</body>
</html>