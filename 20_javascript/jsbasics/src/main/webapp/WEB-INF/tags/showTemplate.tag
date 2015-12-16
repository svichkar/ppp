<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="content_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:url value="/js/basic.js" var="jsbasicUrl"/>
<html>
<head>
<title>${title}</title>
<link href="${cssUrl}" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${jsbasicUrl}"></script>
</head>
<body>
	<div id="global">
		<div class="head1" align = "center">
		<div class="buffer"></div>
		<form method="POST" action="<c:url value="/logout" />">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="Logout" class="nav_element">
		</form>
		</div>
		<div id="nav_menu" align="center">
			<div class="buffer"></div>
			<div id="nav_elements">
				<form method="POST" action="<c:url value="/nav.do"></c:url>">
					<input type="submit" name="target" value="Orders" class="nav_element">
					<input type="submit" name="target" value="Cars" class="nav_element">
					<input type="submit" name="target" value="Customers" class="nav_element">
					<input type="submit" name="target" value="Parts" class="nav_element">
					<input type="submit" name="target" value="Workers" class="nav_element">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</div>
		<div id="content">
			<jsp:invoke fragment="content_area" />
		</div>
	</div>
</body>
</html>