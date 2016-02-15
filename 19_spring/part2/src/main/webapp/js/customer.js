var hintText = {
	"user_id" : "Select user login",
	"f_name" : "Define first name of customer",
	"l_name" : "Define last name of customer",
	"phone" : "Define phone number of customer",
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
	} ],
	phone : [ {
		isValid : [ isPhoneNumber ],
		message : [ "Phone has to be as a 10 digits" ]
	}, {
		isValid : [ isNotEmpty ],
		message : [ "Phone cannot be empty" ]
	} ]
}