var options = {
	description : [ {
		isValid : [nonEmptyValidator],
		message : ['Description field should not be empty.']
	} ],
	model : [ {
		isValid : [nonEmptyValidator],
		message : ['Model field should be not empty.']
	} ],
	vin : [ {
		isValid : [ nonEmptyValidator, function(domElement) {
			return /^[a-zA-Z0-9]{17}$/.test(domElement.value);
		} ],
		message : [ 'VIN field should not be empty.',
				'VIN field must contain only 17 alphanumeric symbols.' ]
	} ]
}