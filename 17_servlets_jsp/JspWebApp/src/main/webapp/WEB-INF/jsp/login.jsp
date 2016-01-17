<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link href="images/favicon.png" rel="shortcut icon" type="shortcut/ico">
    <link rel="stylesheet" href="style/login-style.css">
</head>
<body>
<div class="login-card">
    <h1>Student Grade Management</h1><br>
    <form method="post" action="login">
        <input type="text" name="login" placeholder="Login" maxlength="50">
        <input type="password" name="password" placeholder="Password" maxlength="20">
        <input type="submit" name="commit" class="login login-submit" value="Login">
    </form>
</div>
</body>
</html>