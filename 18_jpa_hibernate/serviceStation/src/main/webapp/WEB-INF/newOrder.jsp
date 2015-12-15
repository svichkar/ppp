<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="New Order Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>
						<c:out value="Create new order" />
			</h1>
	</div>
<table>
<form id="newOrder" action="addNew" method="post">					
					<tr>				<td class="nav-menu"><p>Select car registration number</p></td> 
			<td class="nav-menu">
			<select name="regNumber">
					<c:forEach var="car" items="${carList}">
			<option value="${car.reg_number}"><c:out
												value="${car.reg_number}" /></option>
	</c:forEach>
	</select>
			</td>
			<td class="nav-menu">
			<input hidden="hidden" name="homePage" value ="newOrder">
			<button type="submit" class="btn btn-primary btn-block btn-large"
				name="target" value="car" form="newOrder">Add new car</button>
		</td>			</tr>
			<tr> <td class="nav-menu"><p>Enter issue description</p></td>	
						<td class="nav-menu"><textarea type="text" name="description"
									placeholder="issue description" form="newOrder"></textarea></td>			</tr>
			<tr>				<td class="nav-menu" colspan="2"><input
								type="submit" class="btn btn-primary btn-block btn-large"
								name="target" value="Create order" form="newOrder"></input></td>			</tr>
			
	</form>
</table>
				<br/><br/>
<form action="adminPage" method="get"><div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large"
				name="target" value="car">Cancel</button></div>
		</form>

		</jsp:attribute>
</t:mainTemplate>
