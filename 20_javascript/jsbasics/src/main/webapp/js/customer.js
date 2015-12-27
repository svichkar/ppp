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
	} ],
	phone : [ {
		isValid : [ nonEmptyValidator, function(domElement) {
			return /^[0-9]{10,12}$/.test(domElement.value);
		} ],
		message : [ 'Phone field should not be empty.',
				'Phone field name should contain from 10 to 12 digits.' ]
	} ]
}