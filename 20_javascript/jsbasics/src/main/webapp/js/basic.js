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
}

function validate(form, options) {
	var flag = true;
	for ( var field in options) {
		var input = form.querySelector('[name="' + field + '"')
		for ( var funcIndex in options[field]) {
			var status = !options[field][funcIndex].isValid(input);
			if (!status) {
				alert(options[field][funcIndex].message);
				
			}
			flag = flag & status;
		}
	}
	return flag;
}

var options = {
	login : [ {
		isValid : function(domElement) {
			return domElement.value !== '';
		},
		message : 'The login field is required. Please enter the login.'
	} ],
	description : [ {
		isValid : function(domElement) {
			return domElement.value !== '';
		},
		message : 'Description field should not be empty.'
	} ],
	model : [ {
		isValid : function(domElement) {
			return domElement.value !== '';
		},
		message : 'Model field should be not empty.'
	} ],
	vin : [ {
		isValid : function(domElement) {
			return (domElement.value !== '')
					& (domElement.value
							.search('[!@#$%^&*()-_=+\\|/.,<>?~`"\\\']') === -1);
		},
		message : 'VIN field should not be empty and must contain only alpanumeric symbols.'
	} ],
	first_name : [ {
		isValid : function(domElement) {
			//
			return false;
		},
		message : 'test'
	} ],
	last_name : [ {
		isValid : function(domElement) {
			//
			return false;
		},
		message : 'test'
	} ],
	phone : [ {
		isValid : function(domElement) {
			//
			return false;
		},
		message : 'test'
	} ],
	order_description : [ {
		isValid : function(domElement) {
			//
			return false;
		},
		message : 'test'
	} ],
	timestamp_started : [ {
		isValid : function(domElement) {
			//
			return false;
		},
		message : 'test'
	} ],
	timestamp_finished : [ {
		isValid : function(domElement) {
			//
			return false;
		},
		message : 'test'
	} ],
	used_amount : [ {
		isValid : function(domElement) {
			//
			return false;
		},
		message : 'test'
	} ],
	part_name : [ {
		isValid : function(domElement) {
			//
			return false;
		},
		message : 'test'
	} ],
	vendor : [ {
		isValid : function(domElement) {
			//
			return false;
		},
		message : 'test'
	} ],
	amount : [ {
		isValid : function(domElement) {
			//
			return false;
		},
		message : 'test'
	} ]
}
