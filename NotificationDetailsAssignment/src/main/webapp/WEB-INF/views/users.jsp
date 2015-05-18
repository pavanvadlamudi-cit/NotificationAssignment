<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<html>
<head>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<title>Notification types</title>
</head>
<body>
	<sec:authentication property="principal" var="loginuser" />
	<div class="navbar-inner">

		<div class="container-fluid">

			<ul class="nav pull-right">
				<li class="navbar-link">User name: ${loginuser.username }</li>
				<li class="navbar-link"><a href="../logout">Logout</a></li>
			</ul>
		</div>
	</div>
	<h1>Users</h1>
	<div class="Container">
		<h2>Add an user</h2>
		<form method="post" action="users">
			<div class="Table">
				<div class="Row">
					<div class="CellNoBorder">Username:</div>
					<div class="CellNoBorder">
						<input name="username" type="text" />
					</div>
				</div>
				<div class="Row">
					<div class="CellNoBorder">Password:</div>
					<div class="CellNoBorder">
						<input name="password" type="password" />
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
				<h2>Users</h2>
				<div class="Table">
					<div class="Heading">
						<div class="Cell">
							<!-- Column 1 -->
						</div>
						<div class="Cell">
							<!-- Column 2 -->
						</div>
						<div class="Cell">Sl. No.</div>
						<div class="Cell">Username</div>
					</div>
					<c:forEach items="${users}" var="user" varStatus="row">
						<div class="Row">
							<div class="Cell">
								<form method="post" action="${user.username}">
									<input name="_method" type="hidden" value="delete" /> <input
										name="delete" type="submit" value="Delete" />
								</form>
							</div>
							<div class="Cell">
								<a class="edit-link" href='users/edit/${user.username}'>Edit</a>
							</div>
							<div class="Cell">${row.index+1}.</div>
							<div class="Cell">${user.username}</div>

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
					<c:if test="${loginuser.username=='admin'}">
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