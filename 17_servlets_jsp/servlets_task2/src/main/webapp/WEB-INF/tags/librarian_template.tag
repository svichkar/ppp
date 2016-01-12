<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="sidebar_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl"/>
<html>

<head>
  <title>${title}</title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="content-type" content="text/html; charset=windows-1252" />
  <link href="${cssUrl}" type="text/css" rel="stylesheet"/>
  <jsp:invoke fragment="head_area" />
</head>

<body>

	<ul>
  <li><a class="active">Hi, ${usrName}!</a></li>
  <li><a href="librarian">Home</a></li>
  <li><a href="#Find book">Find book</a></li>
  <li><a href="#Add book">Add book</a></li>
  <li><a href="#Add reader">Add reader</a></li>
  <li><a href="#Loan book">Loan book</a></li>
</ul>

    <div style="margin-left:25%;padding:1px 16px;height:1000px;">
        <jsp:invoke fragment="content_area" />
      </div>

</body>
</html>