var hintText = {
	"amount" : "Define amount of parts in order",
	"part_id" : "Select part for order"
}

var rules = {
	amount : [ {
		isValid : [ isNumber ],
		message : [ "Amount can be only number" ]
	}, {
		isValid : [ isNotEmpty ],
		message : [ "Amount cannot be empty" ]
	} ]
}
