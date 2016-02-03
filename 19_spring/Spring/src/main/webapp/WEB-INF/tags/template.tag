<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true"%>
<%@attribute name="content_area" fragment="true"%>
<%@attribute name="sidebar_area" fragment="true"%>
<%@attribute name="message_area" fragment="true"%>
<c:url value="/style/style.css" var="cssUrl"/>
<!DOCTYPE html>
<html>
<head>
  <title>${title}</title>
  <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
  <link rel="icon" href="/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" type="text/css" href="${cssUrl}"/>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <jsp:invoke fragment="head_area" />
</head>
<body>
        <form action="<c:url value="/logout"></c:url>" method="get"/>
                <input type="submit" value="Logout">
        </form>
    <div id="site_content">
        <article id="content">
            <jsp:invoke fragment="content_area" />
        </article>
        <aside id="sidebar">
                <jsp:invoke fragment="sidebar_area" />
                <p>Welcome</p>
                                    <p><sec:authentication property="principal.username"/><p>
                                    <p><a href="bookManagement">Find book</a></p>
                                    <p><a href="addBook">Add book</a></p>
                                    <p><a href="addReader">Add reader</a></p>
                                    <p><a href="loanManagement">Loan book</a></p>
                                    <%-- <c:if test="${sessionScope.role=='admin'}"> --%>
                                        <p><a href="userManagement">Add user</a></p>
                                        <p><a href="categoryManagement">Add Category</a></p>
                                    <%-- </c:if> --%>
             </aside>
        <div id="message">
            <jsp:invoke fragment="message_area" />
        </div>
    </div>

    <footer id="footer" align="right">
        <img src="webContent/images/Footer.png">
    </footer>
  </div>
</body>
</html>