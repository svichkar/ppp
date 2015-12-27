var options = {
	used_amount : [ {
		isValid : [ nonEmptyValidator, numbersOnlyValidator ],
		message : [ 'Used Amount field should not be empty.',
				'Used Amount field should contain only numeric values.' ]
	} ]
}