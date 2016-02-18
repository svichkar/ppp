var hintText = {
	"part_name" : "Define name of part",
	"vendor" : "Define vendor of part",
	"amount" : "Define amount of parts"
}

var rules = {
		part_name : [ {
			isValid : [ isString ],
			message : [ "Part name has to be as a string" ]
		}, , {
			isValid : [ isNotEmpty ],
			message : [ "Part name cannot be empty" ]
		} ],
		vendor : [ {
			isValid : [ isString ],
			message : [ "Vendor can be only string" ]
		}, {
			isValid : [ isNotEmpty ],
			message : [ "Vendor cannot be empty" ]
		} ],
		amount : [ {
			isValid : [ isNumber ],
			message : [ "Amount has to be as a number" ]
		}, {
			isValid : [ isNotEmpty ],
			message : [ "Amount cannot be empty" ]
		} ]
	}