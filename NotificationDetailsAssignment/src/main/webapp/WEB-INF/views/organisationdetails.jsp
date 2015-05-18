<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" />
<title>Organisation details</title>
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
	<h1>Organisation Details</h1>

	<form method="post" action="organisationdetails">
		<ul style="list-style: none">
			<li>Organisation Name: <input name="name" type="text" value="" />
			</li>
			<li>
				<input name="create" type="submit" value="Create" />
			</li>
		</ul>
		
	</form>
	<div class="Container">
		<div style="display: block; float: left; margin: 5px; padding: 5px;">
			<div class="Table">
				<div class="Heading">
					<div class="Cell">
						<!-- Column 1 -->
					</div>
					<div class="Cell">
						<!-- Column 2 -->
					</div>
					<div class="Cell">Sl. No.</div>
					<div class="Cell">Organisation details</div>
				</div>
				<c:forEach items="${organisationdetails}" var="organisationdetail"
					varStatus="row">
					<div class="Row">
						<div class="Cell">
							<form method="post"
								action="organisationdetails/${organisationdetail.organisationDetailsID}">
								<input name="_method" type="hidden" value="delete"> <input
									name="delete" type="submit" value="Delete">
							</form>
						</div>
						<div class="Cell">
							<a class="edit-link"
								href='organisationdetails/edit/${organisationdetail.organisationDetailsID}'>Edit</a>
						</div>
						<div class="Cell">${row.index+1}.</div>
						<div class="Cell">${organisationdetail.name}</div>
					</div>
				</c:forEach>
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