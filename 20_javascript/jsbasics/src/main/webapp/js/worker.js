var options = {
	first_name : [ {
		isValid : [ nonEmptyValidator, lettersOnlyValidator ],
		message : [ 'First name should not be empty.',
				'First name should contain only letters.' ]
	} ],
	last_name : [ {
		isValid : [ nonEmptyValidator, lettersOnlyValidator ],
		message : [ 'Last name should not be empty.',
				'Last name should contain only letters.' ]
	} ]
}