var hintText = {
	"username" : "Define username for user",
	"password" : "Define password for user",
	"role_id" : "Select role for user"
}

var rules = {
	username : [ {
		isValid : [ isString ],
		message : [ "Username can be only string" ]
	}, {
		isValid : [ isNotEmpty ],
		message : [ "Username name cannot be empty" ]
	} ],
	password : [ {
		isValid : [ isStringAndNumber ],
		message : [ "Password can be only string and numbers" ]
	}, {
		isValid : [ isNotEmpty ],
		message : [ "Password cannot be empty" ]
	} ]
}