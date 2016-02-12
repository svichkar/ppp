var hintText = {
	"model" : "Define name of model",
	"vin" : "Define car vin code",
	"description" : "Any comment"
}

var rules = {
	model : [ {
		isValid : [ isString ],
		message : [ "Car model has to be as a string" ]
	}, , {
		isValid : [ isNotEmpty ],
		message : [ "Car model cannot be empty" ]
	} ],
	vin : [ {
		isValid : [ isStringAndNumber ],
		message : [ "Vin cannot be only string or only number" ]
	}, {
		isValid : [ isNotEmpty ],
		message : [ "Vin cannot be empty" ]
	} ],
	description : [ {
		isValid : [ isStringAndNumber ],
		message : [ "Description has to be as a string" ]
	}, {
		isValid : [ isNotEmpty ],
		message : [ "Description cannot be empty" ]
	} ]
}
