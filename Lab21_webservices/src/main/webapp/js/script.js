document.onreadystatechange = function() {
	if (document.readyState === 'complete') {
		var showingTooltip;

		document.onmouseover = function(e) {
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
		};

		document.onmouseout = function(e) {
			if (showingTooltip) {
				var popUp = document.getElementById('myAlt');
				popUp.style.visibility = "hidden";
				popUp.innerHTML = "";
				popUp.style.left = 0;
				popUp.style.top = 0;
				showingTooltip = null;
			}
		};

		var forms = document.querySelectorAll('form');
		for ( var index in forms) {
			if (!isNaN(index)) {
				var element = forms[index];
				element.addEventListener('submit', submitEventListener, true);
			}
		}

		function submitEventListener(e) {
			resetError(e.target);
			if (!validate(e.target, options)) {
				e.returnValue = false;
			}
		}

		var tables = document.getElementsByTagName('table');
		if (tables.length > 0) {
			document.getElementsByTagName('table')[0].onclick = function(e) {
				var click_Class = "clicked_Row";
				if (!e)
					e = window.event;
				var elem = e.target || e.srcElement;
				while (!elem.tagName || !elem.tagName.match(/td|th|table/i))
					elem = elem.parentNode;

				if (elem.parentNode.tagName == 'TR'
						&& elem.parentNode.parentNode.tagName == 'TBODY') {
					var click_Class_Reg = new RegExp("\\b" + click_Class
							+ "\\b");
					var row = elem.parentNode;

					if (row.getAttribute('clicked_Row')) {
						row.removeAttribute('clicked_Row');
						row.className = row.className.replace(click_Class_Reg,
								"");
						row.className += " " + hover_Class;
					} else {
						row.className += " " + click_Class;
						row.setAttribute('clicked_Row', true);
					}
				}
			}
		}
	}

	function showError(container, errorMessage) {
		// container.className = 'error';
		var msgElem = document.createElement('span');
		msgElem.className = "error-message";
		msgElem.innerHTML = errorMessage;
		insertAfter(msgElem, container);
	}

	function resetError(container) {
		var errors = container.querySelectorAll('.error-message');
		if (errors.length != 0) {
			for ( var error in errors) {
				if (error !== 'length' && error !== 'item')
					container.removeChild(errors[error]);
				// error.remove();
			}
		}
	}
	/*
	 * container.className = ''; if (container.lastChild.className ==
	 * "error-message") { container.removeChild(container.lastChild); }
	 */

	function insertAfter(elem, refElem) {
		return refElem.parentNode.insertBefore(elem, refElem.nextSibling);
	}

	function validate(form, options) {
		var result = true;
		for ( var option in options) {
			var field = form.querySelector('[name="' + option + '"]');
			if (field != null) {
				for ( var check in options[option]) {
					if (!options[option][check].isValid(field)) {
						showError(field, options[option][check].message);
						if (result)
							result = false;
					}
				}
			}
		}
		return result;
	}

	var isNotEmpty = function(domElement) {
		var reg_pusto = domElement.value.replace(/\s+/, '');
		return reg_pusto !== '';
	}

	var options = {
		login : [ {
			isValid : isNotEmpty,
			message : "The login field is required. Please enter the login name. "
		} ],
		password : [ {
			isValid : isNotEmpty,
			message : "The password field is required. Please enter the password. "
		} ],
		confirmPassword : [
				{
					isValid : isNotEmpty,
					message : "The password field is required. Please enter the password. "
				}, {
					isValid : function(domElement) {
						var pass = document.getElementById('password').value;
						return domElement.value == pass;
					},
					message : "Passwords do not match."
				} ],
		email : [
				{
					isValid : isNotEmpty,
					message : "The e-mail field is required. Please enter the email. "
				},
				{
					isValid : function(domElement) {
						var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
						return re.test(domElement.value);
					},
					message : "The email address is invalid. Please enter the correct email. "
				} ],
		role : [ {
			isValid : function(domElement) {
				var text = domElement.value;
				if (text === "Select role") {
					return false;
				} else {
					return true;
				}
			},
			message : "The role field is required. Please choose yser role. "
		} ],
		alias : [ {
			isValid : isNotEmpty,
			message : "The tern name field is required. Please enter the alias. "
		} ],
		searchQuery : [ {
			isValid : isNotEmpty,
			message : "The search query field is required. Please enter search query. "
		} ],
		subject : [ {
			isValid : isNotEmpty,
			message : "The subject field is required. Please enter the subject name. "
		} ],
		term : [ {
			isValid : isNotEmpty,
			message : "The term is required. Please choose the term. "
		} ],
		group : [ {
			isValid : isNotEmpty,
			message : "The student group name is required. Please choose the student group name. "
		} ],
		status : [ {
			isValid : isNotEmpty,
			message : "The status is required. Please choose the status. "
		} ],
		studentGroupName : [ {
			isValid : isNotEmpty,
			message : "The Student Group Name field is required. Please enter the student group name. "
		} ],
		studentGroup : [ {
			isValid : isNotEmpty,
			message : "The search field is required. Please enter the student group name. "
		} ],
		firstName : [
				{
					isValid : isNotEmpty,
					message : 'The first name field is required. Please enter the first name. '
				}, {
					isValid : function(domElement) {
						return /^[a-zA-Z\s]+$/.test(domElement.value);
					},
					message : 'First name should contain only letters.'
				} ],
		lastName : [
				{
					isValid : isNotEmpty,
					message : 'The last name field is required. Please enter the last name. '
				}, {
					isValid : function(domElement) {
						return /^[a-zA-Z\s]+$/.test(domElement.value);
					},
					message : 'Last name should contain only letters.'
				} ],
		bday : [
				{
					isValid : isNotEmpty,
					message : "The Date Birthday field is required. Please enter the Date Birthday. "
				},
				{
					isValid : function(domElement) {
						var re = /([0-2]\d|3[01])\.(0\d|1[012])\.(\d{4})/;
						return re.test(domElement.value);
					},
					message : "Data has incorect form. Please enter data in format YYYY-MM-DD. Example: 1990-02-02"
				} ],
		eday : [
				{
					isValid : isNotEmpty,
					message : "The Date Entry field is required. Please enter the Entry Date. "
				},
				{
					isValid : function(domElement) {
						var re = /([0-2]\d|3[01])\.(0\d|1[012])\.(\d{4})/;
						return re.test(domElement.value);
					},
					message : "Data has incorect form. Please enter data in format YYYY-MM-DD. Example: 1990-02-02"
				},
				{
					isValid : function(domElement) {
						var bday = new Date(
								document.getElementById('bday').value);
						var eday = new Date(domElement.value);
						return eday > bday;
					},
					message : "Date Entry is less then Date Birsday. "
				} ]
	}
}