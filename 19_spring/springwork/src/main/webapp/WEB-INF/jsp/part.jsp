<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:set var="title" value="${action == 'add' ? 'Add New Part' : 'Edit Part'}" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet"/>
</head>
<body>
	<div class="head1"></div>
	<div align="center">
		<p><c:out value="${title}" /></p>
		<div class="head1"></div>
		<form action="<c:url value="/admin/partPost.do"></c:url>" method="POST">
			<c:if test="${action=='edit'}">
			<p>Part ID: </p><input type="text" name="id" value="${part.partId}" /><br>			
			<div class="buffer"></div>
			</c:if>
			<p>Part Name: </p><input type="text" name="part_name" value="${part.partName == null ? '' : part.partName}" /><br>
			<div class="buffer"></div>
			<p>Vendor: </p><input type="text" name="vendor" value="${part.vendor == null ? '' : part.vendor}" /><br>
			<div class="buffer"></div>
			<p>Amount: </p><input type="text" name="amount" value="${part.amount == null ? '' : part.amount}" /><br>
			<div class="buffer"></div>
			<input type="hidden" name="target" value="Parts" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Submit" class="input_add"/>
		</form>
	</div>
</body>
</html>