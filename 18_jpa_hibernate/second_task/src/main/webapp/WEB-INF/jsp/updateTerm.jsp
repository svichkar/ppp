<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<t:template title="Update Term">
	<jsp:attribute name="menu_area">
		<ul>
			<li><a href="<c:url value="/students"/>">Students</a></li>
			<li><a href="<c:url value="/subjects"/>">Subjects</a></li>
			<li><a class="active" href="<c:url value="/terms"/>">Terms</a></li>
			<li><a href="<c:url value="/journals"/>">Journal</a></li>
			<ul style="float: right; list-style-type: none;">
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
			</ul>
		</ul>
	</jsp:attribute>
	<jsp:attribute name="content_area">
       <h2>Update Term</h2>
       <form action="updateTerm" method="post">
       <input type="hidden" name="term_id"
				value="<c:out value="${updateTerm.termId}"/>">
       <table class="form-table">
       <tr>
					<td>Term name : </td>
					<td><input type="text" name="updatedTermName"
						value="<c:out value="${updateTerm.termName}"/>" required></td>
				</tr>
		</table>
       <br /><input type="submit" name="update" value="Update">
       </form>         
    </jsp:attribute>
</t:template>