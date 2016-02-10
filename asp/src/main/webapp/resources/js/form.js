document.onreadystatechange = function() {
	if (document.readyState === 'complete') {

		document.addEventListener('focus', focusOn, true);
		document.addEventListener('blur', focusOff, true);
		document.addEventListener('submit', validate, true);
		
		var forms = document.querySelectorAll('form');
		for ( var index in forms) {
			if (!isNaN(index)) {
				var element = forms[index];
				element.addEventListener('submit', submitEventListener, true);
			}
		}

		function submitEventListener(e) {
			resetError(e.target);
			if (!validate(e.target, eval(e.target.name))) {
				e.returnValue = false;
			}
		}
		
		function validate(form, options) {
			var result = true;
			for (var field in options) {
				for (var check in options[field]){
					var fieldToCheck = form.elements[field];
					if(!options[field][check].isValid(fieldToCheck)){
						showError(fieldToCheck, options[field][check].message);
						if (result)
							result = false;
					}
				}					
			}
			return result;
		}

		function showError(container, errorMessage) {
			var msgElem = document.createElement('span');
			msgElem.className = "error";
			msgElem.innerHTML = errorMessage;
			insertAfter(msgElem, container);
		}
		
		function insertAfter(elem, refElem) {
			return refElem.parentNode.insertBefore(elem, refElem.nextSibling);
		}

		function resetError(container) {
			var errors = container.querySelectorAll('.error');
			if (errors.length != 0) {
				for ( var error in errors) {
					if (error !== 'length' && error !== 'item')
						container.removeChild(errors[error]);
				}
			}
		}
		
		var REQUIRED = {
			isValid : function(domElement) {
				if (!domElement.value) {
					return false;
				} else {
					return true;
				}
			},
			message : "The name field is required. "

		}

		var LETTERS_ONLY = {
			isValid : function(domElement) {
				if (!(/^[a-zA-Z]+$/.test(domElement.value))) {
					return false;
				}
			},
			message : 'The field should contain only letter. Numbers are not allowed. '
		}

		var EMAIL = {
			isValid : function(domElement) {
				var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
				return re.test(domElement.value);
			},
			message : "The email format is invalid. Please enter the correct email. "
		}

		var DATE = {
			isValid : function(domElement) {
				var re = /([0-2]\d|3[01])\.(0\d|1[012])\.(\d{4})/;
				return re.test(domElement.value);
			},
			message : "Data has incorect form. Please enter data in format YYYY-MM-DD. Example: 1990-02-02 "
		}

		var ENTRY_MORE_BIRTHDAY = {
			isValid : function(domElement) {
				var bday = new Date(document.getElementById('bday').value);
				var eday = new Date(domElement.value);
				return eday > bday;
			},
			message : "Date Entry is less then Date Birsday. "
		}

		var CONFIRM_PASWWORD = {
			isValid : function(domElement) {
				var pass = document.getElementById('password').value;
				return domElement.value == pass;
			},
			message : "Passwords do not match. "
		}

		var ROLE = {
			isValid : function(domElement) {
				var text = domElement.value;
				if (text === "NONE") {
					return false;
				} else {
					return true;
				}
			},
			message : "The role field is required. Please choose user role. "
		}
		
		var addUser = {
				userName : [ REQUIRED, EMAIL ],
				password : [ REQUIRED ],
				confirm : [ REQUIRED, CONFIRM_PASWWORD ],
				'role.roleName' : [ ROLE ]
			};

		var showingTooltip;

		function focusOn(e) {
			var target = e.target;
			var tooltip = target.getAttribute('tooltip');
			if (!tooltip)
				return;
			var popUp = document.getElementById('myAlt');
			popUp.innerHTML = tooltip;
			popUp.style.visibility = "visible";
			popUp.style.left = target.getBoundingClientRect().left
					+ target.offsetWidth + 'px';
			popUp.style.top = target.getBoundingClientRect().top + 'px';
			showingTooltip = popUp;
		}
		;

		function focusOff(e) {
			if (showingTooltip) {
				var popUp = document.getElementById('myAlt');
				popUp.style.visibility = "hidden";
				popUp.innerHTML = "";
				popUp.style.left = 0;
				popUp.style.top = 0;
				showingTooltip = null;
			}
		}
		;
	}
}