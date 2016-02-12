var hintText = {
	"datetime_start" : "Define datetime for starting order",
	"datetime_end" : "Define datetime for finishing order",
	"description" : "Define description for order",
	"car_id" : "Select car for processing",
	"order_status_id": "Select status for order"
}

var rules = {
		datetime_start : [ {
			isValid : [ isDateTime ],
			message : [ "Datetime has to be in format yyyy-mm-dd HH:mm:ss" ]
		}, {
			isValid : [ isNotEmpty ],
			message : [ "Datetime cannot be empty" ]
		} ],
		description : [ {
			isValid : [ isStringAndNumber ],
			message : [ "Description can be only string and numbers" ]
		}, {
			isValid : [ isNotEmpty ],
			message : [ "Description cannot be empty" ]
		} ]
	}