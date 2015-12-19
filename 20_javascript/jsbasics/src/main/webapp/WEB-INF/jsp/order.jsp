<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/style/style.css" var="cssUrl" />
<c:url value="/js/basic.js" var="jsbasicUrl"/>
<c:set var="title"
	value="${action == 'add' ? 'Add New Order' : 'Edit Order'}" />
<html>
<head>
<title><c:out value="${title}" /></title>
<link href="${cssUrl}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${jsbasicUrl}"></script>
</head>
<body>
	<div class="head1"></div>
	<div align="center">
		<p>
			<c:out value="${title}" />
		</p>
		<div class="head2"></div>
		<div class="third" id="left">
			<c:if test="${action == 'edit'}">
				<p>Used parts in order</p>
				<table>
					<tr>
						<td><b>Part Name</b></td>
						<td><b>Used Amount</b></td>
						<td><b>Action</b></td>
					</tr>
					<c:forEach var="partItem" items="${parts}">
						<tr>
							<td><input type="text" value="${partItem.partName}" readonly /></td>
							<td><input type="text" value="${partItem.usedAmount}"
								readonly /></td>
							<td>
								<form method="POST">
									<input type="hidden" name="order_id"
										value="${partItem.orderId}" /> <input type="hidden"
										name="part_id" value="${partItem.partId}" /> <input
										type="submit" name="action" formaction="<c:url value="/admin/editOrderPart.do"></c:url>" value="Edit" /> <input
										type="submit" name="action" formaction="<c:url value="/admin/deleteOrderPart.do"></c:url>" value="Delete" />
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								</form>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan=3 height="40">
							<div align="center">
								<form>
									<input type="hidden" name="order_id" value="${order.orderId}" /> <input
										type="submit" formaction="<c:url value="/admin/addOrderPart.do"></c:url>" formmethod="GET"
										value="Add new part to order" class="input_add" />
								</form>
							</div>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
		<div class="third" id="center">
			<form action="<c:url value="/admin/orderPost.do"></c:url>" method="POST" class="focusableForm">
				<c:if test="${action=='edit'}">
					<p>Order ID:</p>
					<input type="text" name="id" value="${order.orderId}" readonly/>
					<div class="hidden">
						<p> Id of the order </p>
					</div>
					<br>
					<div class="buffer"></div>
				</c:if>
				<p>Order Description:</p>
				<textarea rows="" cols="" name="order_description"
					class="large_text">
					<c:out
						value="${order.description == null ? '' : order.description}" />
				</textarea>
				<div class="hidden">
				<p> Description of the order </p>
				</div>
				<br>
				<div class="buffer"></div>
				<p>Order Status:</p>
				<select name="order_status_id">
					<c:forEach var="item" items="${order_statuses}">
						<option value="${item.orderStatusId}"
							<c:if test="${order.orderStatus.orderStatusId eq item.orderStatusId}"> selected</c:if>>
							<c:out value="${item.orderStatusName}" />
						</option>
					</c:forEach>
				</select><br>
				<div class="buffer"></div>
				<p>Car:</p>
				<select name="car_id">
					<c:forEach var="item" items="${cars}">
						<option value="${item.carId}"
							<c:if test="${order.car.carId eq item.carId}"> selected</c:if>><c:out
								value="${item.model}" />
							<c:out value="${item.vin}" /></option>
					</c:forEach>
				</select><br>
				<div class="buffer"></div>
				<c:if test="${action == 'edit'}">
					<p>Time Started:</p>
					<input type="text" name="timestamp_started"
						value="${order.timestampStart}" />
					<div class="hidden">
						<p> Time when order was started </p>
					</div>
					<br>
					<div class="buffer"></div>
					<p>Time Finished:</p>
					<input type="text" name="timestamp_finished"
						value="${order.timestampFinish}" />
					<div class="hidden">
						<p> Time when order was finished </p>
					</div>
					<br>
					<div class="buffer"></div>
				</c:if>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="target" value="Orders" /> <input
					type="submit" value="Submit" class="input_add" />
			</form>
		</div>
		<div class="third" id="right">
			<c:if test="${action == 'edit'}">
				<p>Used workers in order</p>
				<table>
					<tr>
						<td><b>Worker Name</b></td>
						<td><b>Is completed</b></td>
						<td><b>Action</b></td>
					</tr>
					<c:forEach var="workerItem" items="${workers}">
						<tr>
							<td><input type="text" value="${workerItem.workerName}"
								readonly /></td>
							<td><input type="text" value="${workerItem.isCompleted}"
								readonly /></td>
							<td>
								<form method="POST">
									<input type="hidden" name="order_id"
										value="${workerItem.orderId}" /> <input type="hidden"
										name="worker_id" value="${workerItem.workerId}" /> <input
										type="submit" formaction="<c:url value="/admin/editOrderWorker.do"></c:url>" name="action"
										value="Edit" /> <input type="submit"
										formaction="<c:url value="/admin/deleteOrderWorker.do"></c:url>" name="action" value="Delete" />
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								</form>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan=3 height="40">
							<div align="center">
								<form>
									<input type="hidden" name="order_id" value="${order.orderId}" /> <input
										type="submit" formaction="<c:url value="/admin/addOrderWorker.do"></c:url>" formmethod="GET"
										value="Add new worker to order" class="input_add" />
								</form>
							</div>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>