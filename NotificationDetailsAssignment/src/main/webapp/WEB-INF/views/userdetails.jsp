<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<h1>User Details</h1>

<form method="post" action="userdetails">
	<ul style="list-style:none">
		<li>User Name: <input name="username" type="text" value=""/> </li>
		<li>Password: <input name="password" type="text" value=""/> </li>
		<li>Organisation Details Id: <input name="organisationDetailsID" type="text" value=""/> </li>
	</ul>
	<input name="create" type="submit" value="Create"/>
</form>


<c:forEach items="${userdetails}" var="userdetail" varStatus="row">
	<form method="post" action="userdetails/${userdetail.userDetailsID}">
		<input name="_method" type="hidden" value="delete"> 
		<input name="delete" type="submit" value="Delete"> ${row.index}.
		${userdetail.username} ${userdetail.organisationDetailsID} 
	</form>

</c:forEach>
</html>