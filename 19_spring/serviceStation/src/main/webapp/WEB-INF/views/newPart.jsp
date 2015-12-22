<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="new part Page">
	<jsp:attribute name="content_area">
		<div align="center">
		<h1>
						<c:out value="Add new part" />
			</h1>
	</div>
<form id="newPart" action="createNewPart" method="post">
<table>
<tr>
					<td class="nav-menu"><p>Part name</p></td>
					<td class="nav-menu"><input type="text" name="part_name"
					placeholder="Part name" form="newPart" required ></input>
</tr>
<tr> 
					<td class="nav-menu"><p>vendor</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="vendor"
					placeholder="vendor" form="newPart" required ></input></td>
</tr>
<tr> 
					<td class="nav-menu"><p>amount</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="amount"
					placeholder="amount" form="newPart" required ></input></td>
</tr>					
<tr>				<td class="nav-menu" colspan="3">
			<input hidden="hidden" name="homePage" value="${homePage }">
			<input type="submit" class="btn btn-primary btn-block btn-large"
					name="target" value="Create Part" form="newPart"></input></td>			</tr>
			
</table>
	</form>
				<br />
		<br />
<form action="adminPage" method="get"><div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large"
				name="target" value="part" >Cancel</button></div>
		</form>

		</jsp:attribute>
</t:mainTemplate>
