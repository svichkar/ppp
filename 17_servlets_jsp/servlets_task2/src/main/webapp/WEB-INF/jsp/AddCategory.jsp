
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="add book">
	<jsp:attribute name="content_area">
<h2>Add category</h2>
<table>
		<tr>
			<td>category name</td>
			<td></td>
		</tr>
		<form id="addcategory" action="addcategory" method="post">
			<tr>
				<td><input type="text" name="categoryname" required/></td>
				<td><input type=submit value="create category" name="button"></td>
			</tr>
		</form>
		</table>
		
		</jsp:attribute>
</t:general_template>