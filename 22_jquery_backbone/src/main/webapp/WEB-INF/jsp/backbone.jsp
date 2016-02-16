<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:backbone>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		
		<script type="text/template" id="cars">
            <table class="table">
                <caption>Cars</caption>
                <thead>
                <tr>
				<th>Model</th>
				<th>Vin</th>
				<th>Description</th>
				<th>Customer</th>
				<th>Action</th>
                </tr>
                </thead>
                <tbody>
                &lt;% _.each(cars, function(car) { &gt;%
                    <tr>
                        <td>&lt;%= car.model &gt;%</td>
                        <td>&lt;%= car.vin &gt;%</td>
						<td>&lt;%= car.description &gt;%</td>
						<td>&lt;%= car.fname &gt;%</td>
                        <td><a href="#editCar/&lt;%= car.id &gt;%">Edit</a> / <a href="#deleteCar/&lt;%= car.id &gt;%">Delete</a></td>
                    </tr>
                &lt;% }); &gt;%
                </tbody>
            </table>
    </script>
		
		<div class="container">
        <div class="row">
            <div class="span8">
                <div class="content"></div>
            </div>
        </div>
    	</div>

		<div id="hint" /> 
	</jsp:attribute>
</t:backbone>
