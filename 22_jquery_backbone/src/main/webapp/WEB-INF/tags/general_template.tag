<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="sidebar_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl" />
<c:url value="/js/highlight_advise.js" var="jsUrl" />
<c:url value="/js/${jsForPage}.js" var="jsAuxiliry" />
<html>

<head>
<title>${title}</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="${jsUrl}"></script>
<script type="text/javascript" src="${jsAuxiliry}"></script>
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
<jsp:invoke fragment="head_area" />
</head>

<body>
	<div id="main">
		<form action="<c:url value="/logout" />" method="POST" class="bye">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input type="submit" value="Logout"
				name="signoff">
		</form>
		<div id="header">
			<div id="logo">
				<h3>CAR SERVICE STATION</h3>
			</div>
			<div id="menubar">
				<form action="navigation" method="POST">
					<table>
						<tr>
							<td><input type="submit" name="destination" value="Users"></td>
							<td><input type="submit" name="destination"
								value="Customers"></td>
							<td><input type="submit" name="destination" value="Cars"></td>
							<td><input type="submit" name="destination" value="BackBone"></td>
							<td><input type="submit" name="destination" value="Parts"></td>
							<td><input type="submit" name="destination" value="Workers"></td>
							<td><input type="submit" name="destination" value="Orders"></td>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="site_content">
			<div id="content">
				<jsp:invoke fragment="content_area" />
			</div>
		</div>
		<div id="footer">pit stop reserved</div>
	</div>
</body>
</html>