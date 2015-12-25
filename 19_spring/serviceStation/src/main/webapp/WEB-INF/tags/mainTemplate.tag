<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="m"%>
<%@attribute name="title"%>
<%@attribute name="content_area" fragment="true"%>
<%-- <m:url value="style/mainStyle.css" var="cssUrl" /> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<m:url value="/resources/style/mainStyle.css" />"
	rel="stylesheet">
<%-- <link href="${cssUrl}" type="text/css" rel="stylesheet" /> --%>
<title>${title}</title>
</head>
<body>

	<table class="nav-menu">
		<tr>
			<td class="nav-menu" colspan="5"><form action="<m:url value="/logout"></m:url>"
					method="GET">
					<button type="submit" class="btn btn-primary btn-block btn-large"
						value="Logout">Logout</button>
				</form></td>
		</tr>
		<tr>
			<td class="nav-menu">
			<form action="<m:url value="/admin/adminPage"></m:url>" method="GET">
					<button type="submit" class="btn btn-primary btn-block btn-large">Orders</button>
				</form></td>
			<td class="nav-menu"><form action="<m:url value="/admin/carPage"></m:url>" method="GET">
					<button type="submit" class="btn btn-primary btn-block btn-large">Cars</button>
				</form></td>
			<td class="nav-menu"><form action="<m:url value="/admin/customerPage"></m:url>" method="GET">
					<button type="submit" class="btn btn-primary btn-block btn-large">Customers</button>
				</form></td>
			<td class="nav-menu"><form action="<m:url value="/admin/workerPage"></m:url>" method="GET">
					<button type="submit" class="btn btn-primary btn-block btn-large">Workers</button>
				</form></td>
			<td class="nav-menu"><form action="<m:url value="/admin/partPage"></m:url>" method="GET">
					<button type="submit" class="btn btn-primary btn-block btn-large">Parts</button>
				</form></td>
		</tr>
	</table>


	<div id="content">
		<jsp:invoke fragment="content_area" />
	</div>

</body>
</html>