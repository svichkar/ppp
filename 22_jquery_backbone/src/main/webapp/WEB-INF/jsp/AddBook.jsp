
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>	
	<t:general_template title="add book">
	<jsp:attribute name="content_area">
<h2>Add Book</h2>

<div class = "submit">
		<form id="addbook" action="addbook" onsubmit="return validate(this, addBookOpt)" method="post">
			<p>
			<label for="name">Book Name</label>
				<input class = "submit" type="text" name="bookname" id = "input" placeholder="Book name" data-tooltip="here my advice will be shown" />
			</p>	
			<p>
				<label for="afname">Author First Name</label>
				<input class = "submit" type="text" name="authorfirstname" id = "input" placeholder="Author first name" data-tooltip="here my advice will be shown"/>
			</p>
			<p>	
				<label for="alname">Author Last Name</label>
			<input class = "submit" type="text" name="authorlastname" id = "input" placeholder="Author last name" data-tooltip="here my advice will be shown"/>
			</p>
			<p>
				<label for="cat">Category</label>
				<select class = "submit" name="selectcategory" id = "input">
						<option selected disabled value="">choose category</option>
						<c:forEach var="categ" items="${categories}">
							<option value="${categ.name}">${categ.name}</option>
						</c:forEach>
				</select>
				</p>
				<p>
				<label for="cell">Cell</label>
				<select class = "submit" name="selectcell" id = "input">
						<option selected disabled value="">choose cell</option>
						<c:forEach var="cell" items="${cells}">
							<option value="${cell.name}">${cell.name}</option>
						</c:forEach>
				</select>
				</p>
				<p>
				<label for="count">Count</label>
				<input class = "submit" type="text" name="count" id = "input" placeholder="count" data-tooltip="here my advice will be shown"/>
				</p>
			<input class = "submit" type=submit value="create book" name="button">	
		</form>
		
</div>		
		</jsp:attribute>
</t:general_template>