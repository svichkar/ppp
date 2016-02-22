<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:url value="/images/header.png" var="logoUrl"/>
<html>
<head>
  <title>${title}</title>
  <meta name="description" content="student grade management website" />
  <link href="<c:url value="/images/favicon.png"/>" rel="shortcut icon" type="shortcut/ico">
  <link href="${cssUrl}" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <header>
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
                     <li><a href="<c:url value="/logout"/>">Logout</a></li>
                </ul>
                </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div id="site_content">
          <div id="content">
            <jsp:invoke fragment="content_area" />
          </div>
    </div>
        <div id="footer">Copyright &copy; 2016 | All Rights Reserved</div>
    </div>
</div>
</body>
</html>