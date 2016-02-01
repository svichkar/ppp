<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:url value="/images/header.png" var="logoUrl"/>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<html>

<head>
  <title>${title}</title>
  <meta name="description" content="student grade management website" />
  <link href="<c:url value="/images/favicon.png"/>" rel="shortcut icon" type="shortcut/ico">
  <link href="${cssUrl}" type="text/css" rel="stylesheet"/>
</head>

<body>
<header>
<div class="container">
<a href="<c:url value="/home"/>">
<img src="${logoUrl}" width="100%">
</a>
</header>

<div id="menubar">
<table>
<tbody>
<tr>
<td style="background: transparent; border: none; padding:10 0 0 10; margin: 0;width: 100%;">
  <ul id="menu">
     <li><a href="<c:url value="/home"/>">Home</a></li>
     <li><a href="<c:url value="/student"/>">Students</a></li>
     <li><a href="<c:url value="/subject"/>">Subjects</a></li>
     <li><a href="<c:url value="/term"/>">Terms</a></li>
     <li><a href="<c:url value="/journal"/>">Journal</a></li>
   </ul>
</td>

<td style="background: transparent; border: none; padding:10 0 0 10; margin: 0;">
<ul id="menu">
     <li><a  href="${logoutUrl}">Logout</a></li>
</ul>
</td>

</tr>
</tbody></table>

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
<form>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="submit" name="csrf" hidden/>
</form>
</body>
</html>