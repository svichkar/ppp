<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:templateManager title="Update Term" activePage="Terms">
	<jsp:attribute name="content_area">
		<h2>Update Term</h2>
		<form action="updateTerm" method="post">
			<input type="hidden" name="term_id"
				value="<c:out value="${updateTerm.termId}" />">
			<table class="form-table">
				<tr>
					<td>Term name : </td>
					<td>
						<input type="text" name="updatedTermName"
						value="<c:out value="${updateTerm.termName}" />" required>
					</td>
				</tr>
			</table>
			<br />
			<input type="submit" name="update" value="Update">
		</form>
	</jsp:attribute>
</t:templateManager>