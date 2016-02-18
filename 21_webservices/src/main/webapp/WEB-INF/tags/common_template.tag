<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="sidebar_area" fragment="true"%>
<c:url value="/style/common.css" var="cssUrl" />
<c:url value="/js/highlight_advise.js" var="jsUrl" />
<c:url value="/js/login.js" var="jsAuxiliry" />

<html>

<head>
<title>${title}</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${jsUrl}"></script>
<script type="text/javascript" src="${jsAuxiliry}"></script>
<jsp:invoke fragment="head_area" />
</head>

<body>
	<div id="main">
		<div id="header">
		</div>
		 <div id="logo">
        <div id="logo_text">
          <h2>Car service station</h2>
        </div>
      </div>
		<div id="site_content">
			<div class="sidebar">
				<jsp:invoke fragment="sidebar_area" />
			</div>
			<div id="content">
				<jsp:invoke fragment="content_area" />
			</div>
		</div>
		<div id="footer">pit stop reserved</div>
	</div>
</body>
</html>