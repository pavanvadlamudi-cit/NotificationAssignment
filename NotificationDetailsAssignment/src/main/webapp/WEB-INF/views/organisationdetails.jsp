<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<h1>Organisation Details</h1>

<form method="post" action="organisationdetails">
	<ul style="list-style:none">
		<li>Organisation Name: <input name="name" type="text" value=""/> </li>
	</ul>
	<input name="create" type="submit" value="Create"/>
</form>


<c:forEach items="${organisationdetails}" var="organisationdetail" varStatus="row">
	<form method="post" action="organisationdetails/${organisationdetail.organisationDetailsID}">
		<input name="_method" type="hidden" value="delete"> 
		<input name="delete" type="submit" value="Delete"> ${row.index}.
		${organisationdetail.name}	
	</form>

</c:forEach>
</html>