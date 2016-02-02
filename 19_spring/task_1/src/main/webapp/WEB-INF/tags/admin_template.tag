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
<a href="<c:url value="/admin"/>">
<img src="${logoUrl}" width="100%">
</a>
</header>

<div id="menubar">
<table>
<tbody>
<tr>
<td style="background: transparent; border: none; padding:10 0 0 10; margin: 0;width: 100%;">
</td>

<td style="background: transparent; border: none; padding:10 0 0 10; margin: 0;">
<ul id="menu">
     <li>
     <form action="${logoutUrl}" method="post">
       <input type="submit" value="Logout" style="background: transparent;background-color: transparent;border: none;letter-spacing: 0.1em;font-family: sans-serif;font-size: 100%;display: block;float: left;height: 20px;text-decoration: none;font-weight: normal;text-align: center;color: #0C1BE6;">
       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
     </form>
     </li>
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