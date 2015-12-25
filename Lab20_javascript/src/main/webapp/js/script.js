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
						row.className = row.className.replace(click_Class_Reg, "");
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
		container.className = 'error';
		var msgElem = document.createElement('span');
		msgElem.className = "error-message";
		msgElem.innerHTML = errorMessage;
		container.appendChild(msgElem);
	}

	function resetError(container) {
		container.className = '';
		if (container.lastChild.className == "error-message") {
			container.removeChild(container.lastChild);
		}
	}

	function validate(form, options) {
		var result = true;
		resetError(from.parentNode);
		for ( var option in options) {
			var field = form.querySelector('[name="' + option + '"]');
			if (input != undefined) {
				for ( var check in options[option]) {
					if (!options[option][check].isValid(field)) {
						showError(from.parentNode,
								options[option][check].message);
						result = false;
					}
				}
			}
		}
		return result;
	}
	
	var isNotEmpty = function(domElement) {
		return domElement.value !== '';
	}

	var options = {
		login : [ {
			isValid : isNotEmpty,
			message : "The login field is required. Please enter the login name"
		} ],
		password : [ {
			isValid : isNotEmpty,
			message : "The password field is required. Please enter the password"
		} ],
		confirmPassword : [
				{
					isValid : isNotEmpty(),
					message : "The password field is required. Please enter the password"
				},
				{
					isValid : function(domElement) {
						var pass = document.getElementById('password').value;
						return domElement.value == pass;
					},
					message : "Passwords do not match."
				} ],
		email : [
				{
					isValid : isNotEmpty,
					message : "The e-mail field is required. Please enter the email"
				},
				{
					isValid : function(domElement) {
						var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
						return re.test(domElement.value);
					},
					message : "The email address is invalid. Please enter the correct email"
				} ],
		role : [ {
			isValid : isNotEmpty,
			message : "The role field is required. Please choose yser role"
		} ]
	}
}