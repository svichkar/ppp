<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Title</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<script src="jquery-2.2.0.js"></script>
<script src="underscore.js"></script>
<script src="backbone.js"></script>
<script src="onePage.js"></script>
<script type="text/template" id="products">
            <table class="table">
                <caption>Users</caption>
                <thead>
                <tr>
                    <th width=10%>User Id</th>
					<th width=20%>User Name</th>
					<th width=20%>User Role</th>
					<th width=10%>Action</th>
                </tr>
                </thead>
                <tbody>
                <% _.each(users, function(user) { %>
                    <tr>
                        <td><%= user.name %></td>
                        <td><%= user.description %></td>
                        <td><a href="#editProduct/<%= user.id %>">Edit</a> / <a href="#deleteProduct/<%= user.id %>">Delete</a></td>
                    </tr>
                <% }); %>
                </tbody>
            </table>
    </script>
</head>
<body>
	<c:url var="logoutUrl" value="/logout" />
	<form action="${logoutUrl}" method="post">
		<input type="submit" value="Log out" /> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<div id="hmenu">
		<ul>
			<li><a id="home" href="<c:url value="/home"/>">Home</a></li>
			<li><a id="addNew" href="<c:url value="/admin/add-new-user"/>">Add new user</a></li>
		</ul>
	</div>
	
	<div id="myAlt" style="visibility: hidden;"></div>
</body>
</html>