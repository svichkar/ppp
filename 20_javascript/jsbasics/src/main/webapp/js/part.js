var options = {
	part_name : [ {
		isValid : [ nonEmptyValidator ],
		message : [ 'Part name should not be empty.' ]
	} ],
	vendor : [ {
		isValid : [ nonEmptyValidator ],
		message : [ 'Vendor field should not be empty.' ]
	} ],
	amount : [ {
		isValid : [ nonEmptyValidator, numbersOnlyValidator ],
		message : [ 'Amount field should not be empty.',
				'Amount field should contain only numeric values.' ]
	} ]
}