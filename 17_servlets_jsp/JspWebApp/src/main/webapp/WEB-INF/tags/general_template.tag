<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="sidebar_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl"/>
<c:url value="/images/financial.jpg" var="logoUrl"/>
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
<header>
 <div class="header-bg">
  <!--img src="${logoUrl}" alt="Student Grade Management"-->
<p style="background-image: url(/student-grades/images/financial.jpg);background-repeat: round;" align="center">Student Grade Management</p>
 </div>
</header>
<!--div class="logo" style="margin:10px 10px 10px 10px; background-image: url(${logoUrl});background-repeat: no-repeat;float: none;width: inherit;height: 100;"></div-->
  <div id="main">
    <div id="header">
      <div id="logo">
        <div id="logo_text">
          <!-- class="logo_colour", allows you to change the colour of the text -->
          <h1><a href="index.html">night<span class="logo_colour">_sky</span></a></h1>
          <h2>Simple. Contemporary. Website Template.</h2>
        </div>
      </div>
      <div id="menubar">
        <ul id="menu">
          <!-- put class="selected" in the li tag for the selected page - to highlight which page you're on -->
          <li class="selected"><a href="index.html">Home</a></li>
          <li><a href="<c:url value="/examples"/>">Students</a></li>
          <li><a href="<c:url value="/apage"/>">A Page</a></li>
          <li><a href="<c:url value="/anotherpage"/>">Another Page</a></li>
          <li><a href="<c:url value="/contactus"/>">Contact Us</a></li>
        </ul>
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
    <div id="footer">
        Copyright &copy; 2015 | All Rights Reserved
    </div>
  </div>
</body>
</html>