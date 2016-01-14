<%@ page contentType="text/html; charset=windows-1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:mainTemplate title="Part Page">
	<jsp:attribute name="content_area">
		<div align="center">
			<h1>
				<c:out value="Edit existing part" />
			</h1>
		</div>
		<form id="part" action="<c:url value="/admin/updatePart"></c:url>" method="post">
				<input hidden="hidden" name="part_id" value="${part.part_id}" />
<table>
				<tr>
					<th class="nav-menu" colspan="2"><h3>Edit general info</h3></th>
				</tr>
				<tr>
					<td class="nav-menu"><b>part ID</b></td>
					<td class="nav-menu"><input disabled="disabled" value="${part.part_id}" />
					</td>
				</tr>
				<tr>
					<td class="nav-menu"><p>Part name</p></td>
					<td class="nav-menu"><input type="text" name="part_name"
					value="${ part.part_name}" form="part"></input>
</tr>
<tr> 
					<td class="nav-menu"><p>vendor</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="vendor"
					value="${ part.vendor}" form="part" ></input></td>
</tr>
<tr> 
					<td class="nav-menu"><p>amount</p></td>	
					<td class="nav-menu" colspan="2"><input type="text" name="amount"
					value="${ part.amount}" form="part" ></input></td>
</tr>	
				<tr>
				<td class="nav-menu" colspan="2">
				<button type="submit" class="btn btn-primary btn-block btn-large">Update</button>
				</td>
				</tr>
			</table>
		</form>
		<br />
		<br />
		<form action="<c:url value="/admin/adminPage"></c:url>" method="get">
			<div style="width: 50%; margin: 0 auto; text-align: center;">
			<button type="submit" class="btn btn-primary btn-block btn-large">Cancel</button>
			</div>
		</form>
	</jsp:attribute></t:mainTemplate>