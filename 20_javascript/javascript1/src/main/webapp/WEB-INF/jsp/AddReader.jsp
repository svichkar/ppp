
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="add reader">

	<jsp:attribute name="content_area">
<h2>Add Reader</h2>
<div class="submit">
		<form id="addcategory" action="addreader" method="post">
				<p>
				<label for="rfname">Reader First Name</label>
				<input class="submit" type="text" name="readerfirstname"
						placeholder="Reader first name" required />
				</p>
				<p>
				<label class="submit" for="rlname">Reader Last Name</label>
				<input class="submit" type="text" name="readerlastname"
						placeholder="Reader last name" required />
				</p>
				<p>
				<label for="email">Email</label>
				<input class="submit" type="text" name="email" placeholder="email"
						required />
				</p>
				<!-- <input class="submit" type=submit value="add reader" name="button"> -->
			<input type="button" onclick="validate(this.form, addReaderOpt)" value="check">
		</form>
	</div>
		
		</jsp:attribute>
</t:general_template>