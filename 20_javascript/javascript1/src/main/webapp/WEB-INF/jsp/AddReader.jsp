
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:general_template title="add reader">

	<jsp:attribute name="content_area">
<h2>Add Reader</h2>
<div class="submit">
		<form id="addcategory" action="addreader" onsubmit="return validate(this, addReaderOpt)" method="post">
				<p>
				<label for="rfname">Reader First Name</label>
				<input class="submit" type="text" name="readerfirstname"
						placeholder="Reader first name"  data-tooltip="here my advice will be shown"/>
				</p>
				<p>
				<label class="submit" for="rlname">Reader Last Name</label>
				<input class="submit" type="text" name="readerlastname"
						placeholder="Reader last name"  data-tooltip="here my advice will be shown"/>
				</p>
				<p>
				<label for="email">Email</label>
				<input class="submit" type="text" name="email" placeholder="email" data-tooltip="here my advice will be shown"
						 />
				</p>
				<input class="submit" type=submit value="add reader" name="button"> 
		</form>
	</div>
		
		</jsp:attribute>
</t:general_template>