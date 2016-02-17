
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%> 
<t:general_template title="Manage users Backbone">

<jsp:attribute name="content_area">
<script src="<c:url value="/resources/js/jquery.js" />"></script>
<script src="<c:url value="/resources/js/underscore.js" />"></script>
<script src="<c:url value="/resources/js/backbone.js" />"></script>
<script src="<c:url value="/resources/js/history.js" />"></script>

<h2>Manage users with Backbone</h2>

<script type="text/template" id="users">
	<table class="users">
		   <thead>
		<tr>
			<th class="users">user_id</th>
			<th class="users">user_name</th>
			<th class="users">user_password</th>
			<th class="users">user_role</th>
			<th class="users">action</th>
		</tr>
		 </thead>
		 <tbody>
                <\% _.each(users, function(user) { %>
                    <tr>
                        <td><\%= user.userId %></td>
                        <td><\%= user.userName %></td>
                        <td><\%= user.userPassword %></td>
                        <td><\%= user.role.name %></td>
                        <td><a href="#editUser/<\%= user.userId %>">Edit</a> / <a href="#deleteUser/<\%= user.userId %>">Delete</a></td>
                    </tr>
                <\% }); %>
                </tbody>
	</table>
<p class="addUser"><a href="#addUser">Add User</a></p>
</script>

<script type="text/template" id="editUser">
        <div class="" data-example-id="basic-forms">
            <form>
                <input type="hidden" id="id" value="<\%= user.userId %>">
                <div class="form-group">
                    <label for="name">User name</label> <textarea class="form-control" rows="1" id="name"><\%= user.userName %></textarea>
                </div>
                <div class="form-group">
                    <label for="description">User password</label> <textarea class="form-control" rows="1" id="password"><\%= user.userPassword %></textarea>
                </div>
                <button type="submit" id="updateSbmt" class="btn btn-success">Update</button>
            </form>
        </div>
    </script>
    
     <script type="text/template" id="addUser">
         <div class="" data-example-id="basic-forms">
            <form>
                <div class="form-group">
                    <label for="name">User name</label> <textarea class="form-control" rows="1" id="name"></textarea>
                </div>
<div class="form-group">
                    <label for="name">User Password</label> <textarea class="form-control" rows="1" id="password"></textarea>
                </div>
                <button type="submit" id="addSbmt" class="btn btn-success">Add</button>
            </form>
        </div>
    </script>

<div class="span8">
                <div class="content"></div>
            </div>
  
	</jsp:attribute>
</t:general_template>