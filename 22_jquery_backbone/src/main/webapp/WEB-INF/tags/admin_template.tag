<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:url value="/style/tooltips.css" var="cssTooltip"/>
<c:url value="/images/header.png" var="logoUrl"/>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<html>

<head>
  <title>${title}</title>
  <meta name="description" content="student grade management website" />
  <link href="<c:url value="/images/favicon.png"/>" rel="shortcut icon" type="shortcut/ico">
  <link href="${cssUrl}" type="text/css" rel="stylesheet"/>
  <link href="${cssTooltip}" type="text/css" rel="stylesheet"/>
  <script src="<c:url value="/javascript/focus.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/javascript/toggleScript.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/javascript/validator/constant.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/javascript/validator/userOptions.js"/>" type="text/javascript"></script>
  <script src="<c:url value="/javascript/validate.js"/>" type="text/javascript"></script>
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
     <form:form action="${logoutUrl}" method="post">
       <input type="submit" value="Logout" style="background: transparent;background-color: transparent;border: none;letter-spacing: 0.1em;font-family: sans-serif;font-size: 100%;display: block;float: left;height: 20px;text-decoration: none;font-weight: normal;text-align: center;color: #0C1BE6;">
     </form:form>
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

</body>
</html>