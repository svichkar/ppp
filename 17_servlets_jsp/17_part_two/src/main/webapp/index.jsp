<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <title>Library welcome page</title>
  <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
  <link rel="icon" href="/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" type="text/css" href="${cssUrl}"/>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
</head>
<body>
    <form action="mainPage" method="post">
                <div id="loginForm" align="center">
                    <table class="table" border="1" width="30%" cellpadding="3">
                        <thead>
                            <tr>
                                <th colspan="2">Welcome, Please login</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>User name</td>
                                <td><input type="text" name="userName"/></td>
                            </tr>
                            <tr>
                                <td>User password</td>
                                <td><input type="password" name="userPassword"/></td>
                            </tr>
                            <tr>
                                <td><input class="button" type="submit" name="login" value="Login"/></td>
                                <td><input class="button" type="submit" name="registration" value="Registration"/></td>
                            </tr>
                            <tr>
                                <td colspan="2"><a href="retrievePassword.jsp">Forgot password?</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </form>
            <c:if test="${not empty param.message}">
                <p align="center"><c:out value="${param.message}"/></p>
            </c:if>
    </body>
</html>