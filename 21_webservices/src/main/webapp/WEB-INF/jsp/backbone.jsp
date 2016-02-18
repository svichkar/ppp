<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:backbone>
	<jsp:attribute name="head_area">
	</jsp:attribute>
	<jsp:attribute name="content_area">
		
		<script type="text/template" id="cars">
			<a href="#addCar">Add car</a>
			<br>
            <table class="table">
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
                {{ _.each(cars, function(car) { }}
                    <tr>
                        <td>{{= car.model }}</td>
                        <td>{{= car.vin }}</td>
						<td>{{= car.description }}</td>
						<td>{{= car.fname }} {{= car.lname }}</td>
                        <td><a href="#editCar/{{= car.id }}">Edit</a> / <a href="#deleteCar/{{= car.id }}">Delete</a></td>
                    </tr>
                {{ }); }}
                </tbody>
            </table>
    	</script>
    	
    	<script type="text/template" id="editCar">
        	
            	<form>
                	<input type="hidden" id="id" value="{{= car.id }}">
					<input type="hidden" id="customerId" value="{{= car.customerId }}">
					<table>
                	<tr>
	                    <td><label for="name">Model</label></td> 
						<td><textarea class="form-control" rows="1" id="model">{{= car.model }}</textarea></td>
    	            </tr>
        	        <tr>
            	        <td><label for="description">Vin</label></td> 
						<td><textarea class="form-control" rows="2" id="vin">{{= car.vin }}</textarea></td>
                	</tr>
					<tr>
                    	<td><label for="description">Description</label></td> 
						<td><textarea class="form-control" rows="2" id="description">{{= car.description }}</textarea></td>
                	</tr>
					<tr>
                	<td colspan=2 align="right"><button type="submit" id="updateSbmt" class="btn btn-success">Update</button></td>
					</tr>
            	</form>
       		
    	</script>
    	
    	<script type="text/template" id="addCar">
            	<form>
                	<input type="hidden" id="id" value="">
					<table>
                	<tr>
	                    <td><label for="name">Model</label></td> 
						<td><textarea class="form-control" rows="1" id="model"></textarea></td>
    	            </tr>
        	        <tr>
            	        <td><label for="description">Vin</label></td> 
						<td><textarea class="form-control" rows="2" id="vin"></textarea></td>
                	</tr>
					<tr>
                    	<td><label for="description">Description</label></td> 
						<td><textarea class="form-control" rows="2" id="description"></textarea></td>
                	</tr>
					<tr>
                    	<td><label for="customerId">Customer</label></td> 
						<td>
							<select id="customerId">
							{{ _.each(customers, function(customer) { }}
							<option value={{= customer.customerId }} > {{= customer.fname}} {{= customer.lname }}
							{{ }); }}  
							</select>
						</td>
                	</tr>
					<tr>
                	<td colspan=2 align="right"><button type="submit" id="addSbmt" class="btn btn-success">Add</button></td>
					</tr>
            	</form>
    	</script>
    	
		<div class="container">
        <div class="row">
            <div class="span8">
                <div class="content"></div>
            </div>
        </div>
    	</div>
	</jsp:attribute>
</t:backbone>
