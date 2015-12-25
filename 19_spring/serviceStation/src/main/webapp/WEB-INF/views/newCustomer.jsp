<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="new customer Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>
						<c:out value="Add new customer" />
			</h1>
	</div>
<form id="newCustomer" action="<c:url value="/admin/createNewCustomer"></c:url>" method="post">
<table>
<tr>
					<td class="nav-menu"><p>Last Name</p></td>
					<td class="nav-menu"><input type="text" name="last_name"
					placeholder="Last Name" form="newCustomer" required ></input>
</tr>
<tr> 
					<td class="nav-menu"><p>First Name</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="first_name"
					placeholder="First Name" form="newCustomer" required ></input></td>
</tr>
<tr> 
					<td class="nav-menu"><p>Phone Number</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="phone"
					placeholder="Phone Number" form="newCustomer" required ></input></td>
</tr>
<tr> 
					<td class="nav-menu"><p>User Login</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="userLogin"
					placeholder="User Login" form="newCustomer" required ></input></td>
</tr>					
<tr> 
					<td class="nav-menu"><p>User Password</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="userPassword"
					placeholder="User Password" form="newCustomer" required ></input></td>
</tr>						
			<tr>				<td class="nav-menu" colspan="3">
			<input hidden="hidden" name="homePage" value="${homePage }">
			<input type="submit" class="btn btn-primary btn-block btn-large"
					form="newCustomer"></input></td>			</tr>
			
</table>
	</form>
				<br />
		<br />
<form action="<c:url value="/admin/adminPage"></c:url>" method="get">
<div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large">Cancel</button></div>
		</form>

		</jsp:attribute>
</t:mainTemplate>
