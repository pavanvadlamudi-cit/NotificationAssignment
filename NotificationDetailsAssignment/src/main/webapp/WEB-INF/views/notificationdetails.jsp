<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<sec:authentication property="principal" var="user"/>
${user.username }
<h1>Notification Details</h1>

<form method="post" action="notificationdetails">
	<ul style="list-style: none">
		<li>Notification type: <input name="notificationTypeID"
			type="text" value="" />
		</li>
		<li>Organisation details: <input name="organisationdetailsID"
			type="text" value="" />
		</li>
		<li>Details: <input name="details" type="text" value="" />
		</li>
	</ul>
	<input name="create" type="submit" value="Create">
</form>


<c:forEach items="${notificationdetails}" var="notificationdetail"
	varStatus="row">
	<form method="post"
		action="notificationdetails/${notificationdetail.notificationDetailsID}">
		<input name="_method" type="hidden" value="delete"> <input
			name="delete" type="submit" value="Delete"> ${row.index}.
		${notificationdetail.notificationTypeID}
		${notificationdetail.organisationdetailsID}
		${notificationdetail.details}
	</form>

</c:forEach>
</html>