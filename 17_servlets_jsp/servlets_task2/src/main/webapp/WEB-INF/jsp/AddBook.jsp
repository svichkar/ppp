
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="add book">
	<jsp:attribute name="content_area">
		<h2>Add Book</h2>
		<form id="addbook" action="addbook" method="post">		
			<input type="text" name="bookname" placeholder="Book name" required/>
			<input type="text" name="authorfirstname" placeholder="Author first name" required/>
			<input type="text" name="authorlastname" placeholder="Author last name" required/>
			<select name="selectcategory" required>
				<option selected disabled value="">choose category</option>
				<c:forEach var="categ" items="${categories}">
					<option value="${categ.name}">${categ.name}</option>
				</c:forEach>
			</select>
			<select name="selectcell" required>
				<option selected disabled value="">choose cell</option>
				<c:forEach var="cell" items="${cells}">
					<option value="${cell.name}">${cell.name}</option>
				</c:forEach>
			</select>
			<input type="text" name="count" placeholder="count" required/>
			<input type=submit value="create book" name="button">
		</form>		
		</jsp:attribute>
</t:general_template>