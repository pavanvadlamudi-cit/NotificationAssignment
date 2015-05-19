<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<title>Edit Users</title>
</head>
<body>
<sec:authentication property="principal" var="loginuser" />
	<div class="navbar-inner">
		
		<div class="container-fluid">
			
			<ul class="nav pull-right">
				<li class="navbar-link">User name: ${loginuser.username }</li>
				<li class="navbar-link"><a href="../../../logout">Logout</a></li>
			</ul>
		</div>
	</div>
	
	
	<c:url var="saveUrl"
		value="/Notification/users/save/${users.username}" />
<div class="Container">
		<div style="display: block; float: left; margin: 5px; padding: 5px;">
	<div class="Container">
		<form:form id="editusersForm"
			modelAttribute="users" method="post"
			action="${saveUrl}">

			<h2>Update notification</h2>

			<div class="Table">
				<div class="Row">
					<div class="CellNoBorder">Username:</div>
					<div class="CellNoBorder">

						${users.username}</div>
				</div>
				<div class="Row">
					<div class="CellNoBorder">Password:</div>
					<div class="CellNoBorder">
						<form:password path="password"/>
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
	</div>
	<div
			style="display: block; float: right; margin: 5px; padding: 5px; width: 20%">
			
				<div class="right-pane-widget--container">
					
				<ul style="display: block; list-style: none;">
					<li><a href="../../notificationdetails">Notification
							details</a></li>
					<c:if test="${loginuser.username=='admin'}">
						<li><a href="../../notificationtypes">Notification types</a></li>
						<li><a href="../../organisationdetails">Organisation
								details</a></li>
						<li><a href="../../users">User details</a></li>
					</c:if>
				</ul>
			
				</div>
			
		</div>
	</div>
</body>
</html>