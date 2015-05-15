<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<h1>Notification Type Items</h1>

<form method="post" action="notificationtypes">
	<ul style="list-style: none">
		<li>Code: <input name="code" type="text" value="">
		</li>
		<li>Name: <input name="name" type="text" value="">
		</li>
	</ul>

	<input name="create" type="submit" value="Create">
</form>


<c:forEach items="${notificationtypes}" var="notificationtype"
	varStatus="row">
	<form method="post" action="${notificationtype.notificationTypeID}">
		<input name="_method" type="hidden" value="delete"> <input
			name="delete" type="submit" value="Delete"> ${row.index}.
		${notificationtype.code} ${notificationtype.name }
	</form>

</c:forEach>
</html>