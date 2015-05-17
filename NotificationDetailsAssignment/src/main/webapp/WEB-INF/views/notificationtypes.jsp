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
	<div style="display: inline;">
		User name:
		<sec:authentication property="principal" var="user" />
		${user.username}<a href="../logout">Logout</a>

	</div>
	<h1>Notification Types</h1>
	<div class="Container">
		<h2>Add a Notification type</h2>
		<form method="post" action="notificationtypes">
			<div class="Table">
				<div class="Row">
					<div class="CellNoBorder">Code:</div>
					<div class="CellNoBorder">
						<input name="code" type="text" value="" />
					</div>
				</div>
				<div class="Row">
					<div class="CellNoBorder">Name:</div>
					<div class="CellNoBorder">
						<input name="name" type="text" value="" />
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
		<h2>Notification types</h2>
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
				<div class="Cell">Name</div>

			</div>
			<c:forEach items="${notificationtypes}" var="notificationtype"
				varStatus="row">
				<div class="Row">
					<div class="Cell">
						<form method="post"
							action="${notificationtype.notificationTypeID}">
							<input name="_method" type="hidden" value="delete" /> <input
								name="delete" type="submit" value="Delete" />
						</form>
					</div>
					<div class="Cell">
						<a
							href='notificationtypes/edit/${notificationtype.notificationTypeID}'>Edit</a>
					</div>
					<div class="Cell">${row.index+1}.</div>
					<div class="Cell">${notificationtype.code}</div>
					<div class="Cell">${notificationtype.name }</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>