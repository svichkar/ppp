var options = {
	order_description : [ {
		isValid : [ nonEmptyValidator ],
		message : [ 'Order description field should not be empty.' ]
	} ],
	timestamp_started : [ {
		isValid : [ nonEmptyValidator ],
		message : [ 'Start time field should not be empty.' ]
	} ],
	timestamp_finished : [ {
		isValid : [ function(domElement) {
			var start = domElement.parentElement.parentElement
					.querySelector('input[name="timestamp_started"]');
			var startDate = new Date(start.value);
			var finishDate = new Date(domElement.value);
			return domElement.value === '' || finishDate > startDate;
		} ],
		message : [ 'Finished time should be more than start date.' ]
	} ]
}
