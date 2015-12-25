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
		<h1>Access Denied</h1>
		<br/>
		<br/>
		<br/>
		<h2>YOU HAVE NO PERMISSIONS</h2>
		<br/>
		<br/>
		<br/>
		<form action="<c:url value="/login"></c:url>" method="get">
			<div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large">Login</button>
			</div>
		</form>

	</div>
</body>
</html>