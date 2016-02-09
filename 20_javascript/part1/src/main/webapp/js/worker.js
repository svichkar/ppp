var hintText = {
	"f_name" : "Define first name of worker",
	"l_name" : "Define last name of worker",
	"spec_id" : "Select user specialization",
	"status_id" : "Select user status"
}

var rules = {
	f_name : [ {
		isValid : [ isString ],
		message : [ "First name can be only string" ]
	}, {
		isValid : [ isNotEmpty ],
		message : [ "First name cannot be empty" ]
	} ],
	l_name : [ {
		isValid : [ isString ],
		message : [ "Last name can be only string" ]
	}, {
		isValid : [ isNotEmpty ],
		message : [ "First name cannot be empty" ]
	} ]
}