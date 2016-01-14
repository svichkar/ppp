<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Service station</title>

<link href="<c:url value="/resources/style/loginStyle.css" />"
	rel="stylesheet">
<!-- <link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" /> -->
<!-- <link rel="stylesheet" type="text/css" href="style/loginStyle.css"> -->
</head>

<body>

	<div class="login">
		<h1>SERVICE STATION</h1>
		<h2>Please authorize:</h2>
		<form name='f' action="<c:url value='j_spring_security_check' />"
			method='POST'>
			<input type="text" name='j_username' placeholder="Username"></input>
			<input type="password" name='j_password' placeholder="Password"></input>
			<button type="submit" class="btn btn-primary btn-block btn-large"
				value="login">Login</button>
			<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>"
				value="<c:out value="${_csrf.token}"/>" />
		</form>
	</div>
</body>
</html>
