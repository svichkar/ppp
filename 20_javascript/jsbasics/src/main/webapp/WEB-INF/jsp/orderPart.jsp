<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:url value="/js/basic.js" var="jsbasicUrl"/>
<c:set var="title" value="${action == 'add' ? 'Add New Part To Order' : 'Edit Worker For Part'}" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${jsbasicUrl}"></script>
</head>
<body>
	<div class="head1"></div>
	<div align="center">
		<p><c:out value="${title}" /></p>
		<div class="head1"></div>
		<form action="<c:url value="/admin/orderPartPost.do"></c:url>" method="POST" class="focusableForm">
			<input type="hidden" name="order_id" value="${part.orderId}" />
			<p>Part Name: </p>
			<select name="part_id">
			<c:forEach var="item" items="${parts}">
			<option value="${item.partId}"<c:if test="${part.partId eq item.partId}"> selected</c:if>><c:out value="${item.partName}" /></option>
			</c:forEach>
			</select><br>
			<div class="buffer"></div>
			<p>Used Amount: </p>
			<input type="text" name="used_amount" value="${part.usedAmount}" />
			<div class="hidden">
				<p> Amount used in the order </p>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div class="buffer"></div>
			<input type="submit" value="Submit" class="input_add"/>
		</form>
	</div>
</body>
</html>