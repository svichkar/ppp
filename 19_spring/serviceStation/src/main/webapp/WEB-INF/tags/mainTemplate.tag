<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="m"%>
<%@attribute name="title"%>
<%@attribute name="content_area" fragment="true"%>
<%-- <m:url value="style/mainStyle.css" var="cssUrl" /> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<m:url value="/resources/style/mainStyle.css" />" rel="stylesheet">
<%-- <link href="${cssUrl}" type="text/css" rel="stylesheet" /> --%>
<title>${title}</title>
</head>
<body>

	<table class="nav-menu">
		<tr>
			<td class="nav-menu"><form action="adminPage" method="GET">
					<button type="submit" name="target"
						class="btn btn-primary btn-block btn-large" value="orders">Orders</button>
				</form></td>
			<td class="nav-menu"><form action="carPage" method="GET">
					<button type="submit" name="target"
						class="btn btn-primary btn-block btn-large" value="cars">Cars</button>
				</form></td>
			<td class="nav-menu"><form action="customerPage" method="GET">
					<button type="submit" name="target"
						class="btn btn-primary btn-block btn-large" value="customers">Customers</button>
				</form></td>
			<td class="nav-menu"><form action="workerPage" method="GET">
					<button type="submit" name="target"
						class="btn btn-primary btn-block btn-large" value="workers">Workers</button>
				</form></td>
			<td class="nav-menu"><form action="partPage" method="GET">
					<button type="submit" name="target"
						class="btn btn-primary btn-block btn-large" value="parts">Parts</button>
				</form></td>
		</tr>
	</table>


	<div id="content">
		<jsp:invoke fragment="content_area" />
	</div>

</body>
</html>