<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Service station</title>

<link href="<c:url value="/resources/style/loginStyle.css" />" rel="stylesheet">
<!-- <link rel="stylesheet" type="text/css" href="style/loginStyle.css"> -->
</head>

<body>

	<div class="login">
		<h1>SERVICE STATION</h1>
		<h2>Please authorize:</h2>
		<form action="login" method="post">
			<input type="text" name="login" placeholder="Username"></input> <input
				type="password" name="password" placeholder="Password"></input>
			<button type="submit" class="btn btn-primary btn-block btn-large"
				value="login">Login>></button>
		</form>
	</div>
</body>
</html>
