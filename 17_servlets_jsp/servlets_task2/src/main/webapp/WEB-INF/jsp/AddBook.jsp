
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="add book">
	<jsp:attribute name="content_area">
<h2>Add Book</h2>
<table>
		<tr>
			<td>name</td>
			<td>author first name</td>
			<td>author last name</td>
			<td>category</td>
			<td>cell</td>
			<td>count</td>
			<td></td>
		</tr>
		<form id="addbook" action="addbook" method="post">
			<tr>
				<td><input type="text" name="bookname" required/></td>
				<td><input type="text" name="authorfirstname" required/></td>
				<td><input type="text" name="authorlastname" required/></td>
				<td><select name="selectcategory" required>
						<option selected disabled value="">choose</option>
						<c:forEach var="categ" items="${categories}">
							<option value="${categ.name}">${categ.name}</option>
						</c:forEach>
				</select></td>
				<td><select name="selectcell" required>
						<option selected disabled value="">choose</option>
						<c:forEach var="cell" items="${cells}">
							<option value="${cell.name}">${cell.name}</option>
						</c:forEach>
				</select></td>
				<td><input type="text" name="count" required/></td>
				<td><input type=submit value="create book" name="button"></td>
			</tr>
		</form>
		</table>
		
		</jsp:attribute>
</t:general_template>