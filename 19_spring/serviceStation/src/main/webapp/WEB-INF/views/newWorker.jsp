<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="new worker Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>
						<c:out value="Add new worker" />
			</h1>
	</div>
<form id="newWorker" action="createNewWorker" method="post">
<table>
<tr>
					<td class="nav-menu"><p>Last Name</p></td>
					<td class="nav-menu"><input type="text" name="last_name"
					placeholder="Last Name" form="newWorker" required ></input>
</tr>
<tr> 
					<td class="nav-menu"><p>First Name</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="first_name"
					placeholder="First Name" form="newWorker" required ></input></td>
</tr>
<tr> 
					<td class="nav-menu"><p>Specialization</p></td>	
					<td class="nav-menu" colspan="2">
					<select  name="specialization_id" form="newWorker">
					<c:forEach var="spec" items="${specs}">
					<option value="${spec.specialization_id }"><c:out value="${spec.specialization_name }"></c:out>
					</c:forEach>
					</select>
</tr>
<tr> 
					<td class="nav-menu"><p>User Login</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="userLogin"
					placeholder="User Login" form="newWorker" required ></input></td>
</tr>					
<tr> 
					<td class="nav-menu"><p>User Password</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="userPassword"
					placeholder="User Password" form="newWorker" required ></input></td>
</tr>						
			<tr>				<td class="nav-menu" colspan="3">
			<input hidden="hidden" name="homePage" value="${homePage }">
			<input type="submit" class="btn btn-primary btn-block btn-large"
					name="target" value="Create Worker" form="newWorker"></input></td>			</tr>
			
</table>
	</form>
				<br />
		<br />
<form action="adminPage" method="get"><div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large"
				name="target" value="car" >Cancel</button></div>
		</form>

		</jsp:attribute>
</t:mainTemplate>
