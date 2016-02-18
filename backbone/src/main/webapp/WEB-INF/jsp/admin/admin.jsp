<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Accounting student performance</title>
<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
<script src="<c:url value="/resources/js/jquery-2.2.0.js"/>"></script>
<script src="<c:url value="/resources/js/underscore.js"/>"></script>
<script src="<c:url value="/resources/js/backbone.js"/>"></script>
<script src="<c:url value="/resources/js/onePage.js"/>"></script>
<script src="<c:url value="/resources/js/form.js"/>"></script>
<script src="<c:url value="/resources/js/table.js"/>"></script>

<script type="text/template" id="users">
<h2>Manage users</h2>
 <table class="users" border="1">
     <thead>
		<tr>
        	<th width=10%>User Id</th>
			<th width=20%>User Name</th>
			<th width=20%>User Role</th>
			<th width=10%>Action</th>
        </tr>
   	</thead>
   	<tbody>
	<\% _.each(users, function(user) { %>
    	<tr>
        	<td><\%= user.id %></td>
            <td><\%= user.userName %></td>
            <td><\%= user.role.roleName %></td>
            <td><a href="#editUser/<\%= user.id %>">Edit</a> / <a href="#deleteUser/<\%= user.id %>">Delete</a></td>
        </tr>
     <\% }); %>
     </tbody>
 </table>
</script>

<script type="text/template" id="addUser">
	<h2>Add users</h2>
         <div>
            <form>
                <div>
                    <label for="userName">Login (email):</label><br>
					<input id="userName"></input>
                </div>
                <div>
                    <label for="password">Password:</label> <br>
					<input type="password" id="password"></input>
                </div>
				<div>
                    <label for="cPassword">Confirm password:</label><br>
					<input type="password" id="cPassword"></input>
                </div>
				<div>
                    <label for="role">Role:</label><br>
					<select id="role">
					<\% _.each(roles, function(role) { %>
						<option value="<\%= role.id %>" label="<\%= role.roleName %>"/>
					<\% }); %>
					</select>
                </div>
                <button type="submit" id="addSbmt" style="width: 150px;">Add</button>
            </form>
        </div>
    </script>
    
<script type="text/template" id="editUser">
	<h2>Edit users</h2>
         <div>
            <form>
				<input type="hidden" id="id" value="<\%= user.id %>">
                <div>
                    <label for="userName">Login (email):</label><br>
					<input id="userName" value="<\%= user.userName %>"></input>
                </div>
                <div>
                    <label for="password">Password:</label> <br>
					<input type="password" id="password" value="<\%= user.password %>"></input>
                </div>
				<div>
                    <label for="cPassword">Confirm password:</label><br>
					<input type="password" id="cPassword" value="<\%= user.password %>"></input>
                </div>
				<div>
                    <label for="role">Role:</label><br>
					<select id="role">
						<option value="<\%= user.role.id %>" label="<\%= user.role.roleName %>"/>
					<\% _.each(roles, function(role) { %>						
						<option value="<\%= role.id %>" label="<\%= role.roleName %>"/>						
					<\% }); %>						
					</select>
                </div>
                <button type="submit" id="updateSbmt" style="width: 150px;">Update</button>
            </form>
        </div>
    </script>

</head>
<body>
	<c:url var="logoutUrl" value="/logout" />
	<form action="${logoutUrl}" method="post">
		<input type="submit" value="Log out" /> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<div class="container">
		<div class="row">
			<div id="hmenu">
				<ul>
					<li><a id="home" href="<c:url value="/home"/>">Home</a></li>
					<li class="users"><a href="#users">Users</a></li>
					<li class="addUser"><a href="#addUser">Add user</a></li>
				</ul>
			</div>			
			<div class="span8">
				<div class="content"></div>
			</div>
		</div>
	</div>
	<div id="myAlt" style="visibility: hidden;"></div>

</body>
</html>