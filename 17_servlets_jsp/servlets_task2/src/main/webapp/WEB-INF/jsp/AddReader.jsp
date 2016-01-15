
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="add book">
	<jsp:attribute name="content_area">
<h2>Add Reader</h2>
<table>
		<tr>
			<td>Reader first name</td>
			<td>Reader last name</td>
			<td>email</td>
			<td></td>
		</tr>
		<form id="addcategory" action="addreader" method="post">
			<tr>
				<td><input type="text" name="readerfirstname" required/></td>
				<td><input type="text" name="readerlastname" required/></td>
				<td><input type="text" name="email" required/></td>
				<td><input type=submit value="add user" name="button"></td>
			</tr>
		</form>
		</table>
		
		</jsp:attribute>
</t:general_template>