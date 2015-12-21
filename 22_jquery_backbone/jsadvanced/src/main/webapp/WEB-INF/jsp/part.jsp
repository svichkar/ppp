<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl" />
<c:url value="/js/basic.js" var="jsbasicUrl" />
<c:set var="title"
	value="${action == 'add' ? 'Add New Part' : 'Edit Part'}" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${jsbasicUrl}"></script>
</head>
<body>
	<div class="head1"></div>
	<div align="center">
		<p>
			<c:out value="${title}" />
		</p>
		<div class="head1"></div>
		<form action="<c:url value="/admin/partPost.do"></c:url>"
			method="POST" class="focusableForm">
			<c:if test="${action=='edit'}">
				<div class="formElement">
					<p>Part ID:</p>
					<input type="text" name="id" value="${part.partId}" readonly />
					<div class="hidden">Id of the car</div>
				</div>
			</c:if>
			<div class="formElement">
				<p>Part Name:</p>
				<input type="text" name="part_name"
					value="${part.partName == null ? '' : part.partName}" />
				<div class="hidden">Name of the part</div>
			</div>
			<div class="formElement">
				<p>Vendor:</p>
				<input type="text" name="vendor"
					value="${part.vendor == null ? '' : part.vendor}" />
				<div class="hidden">Vendor of the part (provider)</div>
			</div>
			<div class="formElement">
				<p>Amount:</p>
				<input type="text" name="amount"
					value="${part.amount == null ? '' : part.amount}" />
				<div class="hidden">Amount of the parts present in the storage
				</div>
			</div>
			<div class="formElement">
				<input type="hidden" name="target" value="Parts" /> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="Submit" class="input_add" />
			</div>
		</form>
	</div>
</body>
</html>