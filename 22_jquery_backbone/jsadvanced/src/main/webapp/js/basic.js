document.onreadystatechange = function() {
	if (document.readyState === 'complete') {
		var formInputs = document
				.querySelectorAll('form.focusableForm input, textarea');
		for ( var index in formInputs) {
			if (!isNaN(index)) {
				var element = formInputs[index];
				if (!hasClass(element, 'input_add')) {
					element.addEventListener('focus', focusListener, false);
					element.addEventListener('blur', blurListener, false);
				}
			}
		}
		//
		var tableRows = document.querySelectorAll('tr.highlightable td');
		for ( var index in tableRows) {
			if (!isNaN(index)) {
				var element = tableRows[index];
				element.addEventListener('click', clickListener, true);
			}
		}
		//
		var forms = document.querySelectorAll('form.focusableForm');
		for ( var index in forms) {
			if (!isNaN(index)) {
				var element = forms[index];
				element.addEventListener('submit', submitListener, true);
			}
		}
	}

	function focusListener(e) {
		var focusedItem = e.target;
		var parent = focusedItem.parentNode;
		if (focusedItem.nodeName === 'INPUT'
				|| focusedItem.nodeName === 'TEXTAREA') {
			showHint(parent.querySelector('div.hidden'));
		}
		e.stopPropagation();
	}

	function blurListener(e) {
		var blurredItem = e.target;
		var parent = blurredItem.parentNode;
		if (blurredItem.nodeName === 'INPUT'
				|| blurredItem.nodeName === 'TEXTAREA') {
			hideHint(parent.querySelector('div.visible'));
		}
		e.stopPropagation();
	}

	function clickListener(e) {
		var clickedItem = e.target;
		if (clickedItem.nodeName === 'TD') {
			highlight(clickedItem.parentElement);
		}
		e.stopPropagation();
	}

	function submitListener(e) {
		resetSubmitContainer(e.target);
		if (!validate(e.target, options)) {
			e.returnValue = false;
		}
	}

	function highlight(item) {
		var clazz = 'highlight';
		if (hasClass(item, clazz)) {
			item.classList.remove(clazz);
		} else {
			item.classList.add(clazz);
		}
	}

	function hasClass(elem, clazz) {
		return (' ' + elem.className + ' ').indexOf(' ' + clazz + ' ') > -1;
	}

	function showHint(item) {
		item.classList.remove('hidden');
		item.classList.add('visible');
	}

	function hideHint(item) {
		item.classList.remove('visible');
		item.classList.add('hidden');
	}

	function validate(form, options) {
		var flag = true;
		for ( var field in options) {
			var input = form.querySelector('[name="' + field + '"');
			if (input != null) {
				for ( var funcIndex in options[field]) {
					var status = options[field][funcIndex].isValid(input);
					if (!status) {
						var divNode = document.createElement('div');
						divNode.innerHTML += options[field][funcIndex].message;
						divNode.className = 'error';
						insertAfter(divNode, input.parentNode.querySelector('div.hidden'));
					}
					flag = flag && status;
				}
			}
		}
		return flag;
	}

	function resetSubmitContainer(formElement) {
		var formDivs = formElement.querySelectorAll('div.formElement');
		for (var index in formDivs) {
			if (index !== 'length' && index !== 'item') {
				var formDiv = formDivs[index];
				var errorDivs = formDiv.querySelectorAll('div.error');
				for (var divIndex in errorDivs) {
					if (divIndex !== 'length' && divIndex !== 'item') {
						var errorDiv = errorDivs[divIndex];
						formDiv.removeChild(errorDiv);
					}
				} 
			}
		}
	}

	function insertAfter(newNode, referenceNode) {
		referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
	}
	
	var nonEmptyValidator = function(domElement) {
		return domElement.value.trim() !== '';
	}

	var options = {
		login : [ {
			isValid : nonEmptyValidator,
			message : 'The login field is required. Please enter the login.'
		} ],
		description : [ {
			isValid : nonEmptyValidator,
			message : 'Description field should not be empty.'
		} ],
		model : [ {
			isValid : nonEmptyValidator,
			message : 'Model field should be not empty.'
		} ],
		vin : [ {
			isValid : nonEmptyValidator,
			message : 'VIN field should not be empty.'
		}, {
			isValid : function(domElement) {
				return /^[a-zA-Z0-9]{17}$/.test(domElement.value);
			},
			message : 'VIN field must contain only 17 alphanumeric symbols.'
		} ],
		first_name : [ {
			isValid : nonEmptyValidator,
			message : 'First name should not be empty.'
		}, {
			isValid : function(domElement) {
				return /^[a-zA-Z\s]+$/.test(domElement.value);
			},
			message : 'First name should contain only letters.'
		} ],
		last_name : [ {
			isValid : nonEmptyValidator,
			message : 'Last name should not be empty.'
		}, {
			isValid : function(domElement) {
				return /^[a-zA-Z\s]+$/.test(domElement.value);
			},
			message : 'Last name should contain only letters.'
		} ],
		phone : [ {
			isValid : nonEmptyValidator,
			message : 'Phone field should not be empty.'
		}, {
			isValid : function(domElement) {
				return /^[0-9]{10,12}$/.test(domElement.value);
			},
			message : 'Phone field name should contain from 10 to 12 digits.'
		} ],
		order_description : [ {
			isValid : nonEmptyValidator,
			message : 'Order description field should not be empty.'
		} ],
		timestamp_started : [ {
			isValid : nonEmptyValidator,
			message : 'Start time field should not be empty.'
		} ],
		timestamp_finished : [ {
			isValid : function(domElement) {
				var start = domElement.parentElement.parentElement.querySelector('input[name="timestamp_started"]');
				var startDate = new Date(start.value);
				var finishDate = new Date(domElement.value);
				return domElement.value === '' || finishDate > startDate;
			},
			message : 'Finished time should be more than start date.'
		} ],
		used_amount : [ {
			isValid : nonEmptyValidator,
			message : 'Used Amount field should not be empty.'
		}, {
			isValid : function(domElement) {
				return /^[0-9]+$/.test(domElement.value);
			},
			message : 'Used Amount field should contain only numeric values.'
		} ],
		part_name : [ {
			isValid : nonEmptyValidator,
			message : 'Part name should not be empty.'
		} ],
		vendor : [ {
			isValid : nonEmptyValidator,
			message : 'Vendor field should not be empty.'
		} ],
		amount : [ {
			isValid : nonEmptyValidator,
			message : 'Amount field should not be empty.'
		}, {
			isValid : function(domElement) {
				return /^[0-9]+$/.test(domElement.value);
			},
			message : 'Amount field should contain only numeric values.'
		} ]
	}
}
