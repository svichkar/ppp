<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="sidebar_area" fragment="true"%>

<c:url value="/resources/style/style.css" var="cssUrl" />

<html>

<head>
<title>${title}</title>
<meta name="description" content="website description" />
<meta name="keywords" content="website keywords, website keywords" />
<meta http-equiv="content-type"
	content="text/html; charset=windows-1252" />
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
<script src="<c:url value="/resources/js/main.js" />"></script>
</head>

<body>
	<div id="header">
		<jsp:invoke fragment="head_area" />
	</div>

	<div id="nav">

		<ul>
			<li><a class="active">Hi, <sec:authentication
						property="name" /> <sec:authentication property="authorities" />!
			</a></li>

			<li><a href="homepage">Home</a></li>
			<li><a href="findbook">Find book</a></li>
			<li><a href="addbook">Add book</a></li>
			<li><a href="addreader">Add reader</a></li>
			<li><a href="findreader">Loan book</a></li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="manageusers">Add user</a></li>
				<li><a href="addcategory">Add category</a></li>
			</sec:authorize>
			<li><sec:authorize access="isAuthenticated()">
					<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
				</sec:authorize></li>
		</ul>
	</div>

	<div id="section"
		style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
		<jsp:invoke fragment="content_area" />
	</div>
</body>

<script type="text/javascript">
	//document.addEventListener("DOMContentLoaded", addRowHandlers());
	document.onclick = function(event) {
		var target = event.target;
		var parent = target.parentElement;
		if (parent.nodeName != "TR")
			return;
		if (parent.style.backgroundColor == "") {
			parent.style.backgroundColor = 'red';
		} else {
			parent.style.backgroundColor = "";
		}
	};
	
	var showingTooltip;

    document.onmouseover = function(e) {
      var target = e.target;

      var tooltip = target.getAttribute('data-tooltip');
      if (!tooltip) return;

      var tooltipElem = document.createElement('div');
      tooltipElem.className = 'tooltip';
      tooltipElem.innerHTML = tooltip;
      document.body.appendChild(tooltipElem);

      var coords = target.getBoundingClientRect();
      var left = coords.right;
      var top = coords.top;
      tooltipElem.style.left = left + 'px';
      tooltipElem.style.top = top + 'px';

      showingTooltip = tooltipElem;
    };
    
    document.onmouseout = function(e) {

        if (showingTooltip) {
          document.body.removeChild(showingTooltip);
          showingTooltip = null;
        }

      };
</script>
</html>