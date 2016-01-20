<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="sidebar_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:url value="/images/header.png" var="logoUrl"/>
<html>

<head>
  <title>${title}</title>
  <meta name="description" content="student grade management website" />
  <link href="images/favicon.png" rel="shortcut icon" type="shortcut/ico">
  <link href="${cssUrl}" type="text/css" rel="stylesheet"/>
</head>

<body>
<header>
<div class="container">
<a href="/student-grades/">
<img src="${logoUrl}" width="100%">
</a>
</header>

<div id="menubar">
<table>
<tr>
<td>
  <ul id="menu">
     <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
     <li><a class="selected" href="<c:url value="/home"/>">Home</a></li>
     <li><a href="<c:url value="/student"/>">Students</a></li>
     <li><a href="<c:url value="/subject"/>">Subjects</a></li>
     <li><a href="<c:url value="/term"/>">Terms</a></li>
     <li><a href="<c:url value="/journal"/>">Journal</a></li>
   </ul>
</td
<td>
<p><a href="logout">Logout</a></p>
</td>
</tr>
</table>



</div>
<div id="site_content">
      <div id="content">
        <jsp:invoke fragment="content_area" />
      </div>
    </div>
    <div id="footer">
        Copyright &copy; 2016 | All Rights Reserved
    </div>
  </div>
</body>
</html>