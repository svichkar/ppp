//alert(document.title);
document.onreadystatechange = function() {
	if (document.readyState === 'complete') {
		var table = document.getElementById('main_table');
		var rows = table != null ? table.getElementsByTagName("tr") : [];
		for (var i = 0; i < rows.length; i++) {
			rows[i].onclick = rowClickHandler;
		}

		var texboxes = document.querySelectorAll('input[type=text]');
		for (var i = 0; i < texboxes.length; i++) {
			var textbox = texboxes[i];
			textbox.addEventListener("focus", showHint);
			textbox.addEventListener("blur", hideHint);
		}

		var passwordboxes = document.querySelectorAll('input[type=password]');
		for (var i = 0; i < passwordboxes.length; i++) {
			var passwordbox = passwordboxes[i];
			passwordbox.addEventListener("focus", showHint);
			passwordbox.addEventListener("blur", hideHint);
		}

		var selectboxes = document.querySelectorAll('select');
		for (var i = 0; i < selectboxes.length; i++) {
			var selectbox = selectboxes[i];
			selectbox.addEventListener("focus", showHint);
			selectbox.addEventListener("blur", hideHint);
		}

		var submitForms = document
				.querySelectorAll('form[name="formForValidation"]');
		for (var i = 0; i < submitForms.length; i++) {
			var submitForm = submitForms[i];
			submitForm.addEventListener("submit", validateForm);
		}

	}
};

function rowClickHandler() {
	this.classList.toggle("active");
};

function showHint(e) {
	var posX = 0;
	var posY = 0;
	if (!e)
		var e = window.event;
	if (e.pageX || e.pageY) {
		posX = e.pageX;
		posY = e.pageY;
	} else {
		var coordinates = e.target.parentElement.getBoundingClientRect();
		posX = coordinates.right - 5;
		posY = coordinates.top - 3;
	}

	var hint = document.getElementById('hint');
	hint.innerHTML = hintText[e.target.name];
	hint.style.left = posX + 5 + 'px';
	hint.style.top = posY + 5 + 'px';
	hint.style.visibility = "visible";
};

function hideHint() {
	var hint = document.getElementById('hint');
	hint.style.visibility = "hidden";
	hint.innerHTML = "";
	hint.style.top = 0;
	hint.style.left = 0;
};

function validateForm(e) {
	resetWarning();
	var finalStatus = false;
	var status = {};
	if (!e)
		var e = window.event;
	// e.preventDefault();
	for ( var field in rules) {
		var validatedField = e.target.elements[field].value;
		if (validatedField != undefined) {
			var warnings = {};
			for ( var validator in rules[field]) {
				var result = rules[field][validator].isValid[0](validatedField);
				if (!result) {
					warnings[validator] = rules[field][validator].message[0];
				}
			}
		}
		if (Object.keys(warnings).length > 0) {
			showWarning(e.target.elements[field], warnings)
			status[field] = false;
		} else {
			status[field] = true;
		}
	}
	for ( var key in status) {
		if (status[key] === false) {
			finalStatus = false;
			e.preventDefault();
			break;
		} else {
			finalStatus = true;
		}
	}
	return finalStatus;
};

function resetWarning() {
	var warnings = document.querySelectorAll('.warning');
	if (warnings.length > 0) {
		for ( var warning in warnings) {
			var parent = warning.parentNode;
			if (parent) {
				parent.removeChild(warning);
			}
		}
	}
}

function showWarning(e, messages) {
	var posX = 0;
	var posY = 0;
	if (!e)
		var e = window.event;
	if (e.pageX || e.pageY) {
		posX = e.pageX;
		posY = e.pageY;
	} else {
		var coordinates = e.parentElement.getBoundingClientRect();
		posX = coordinates.right - 5;
		posY = coordinates.top - 3;
	}

	var warningDiv = document.createElement('div');
	warningDiv.setAttribute('class', 'warning');
	for ( var key in messages) {
		warningDiv.innerHTML = messages[key];
	}
	warningDiv.style.left = posX + 5 + 'px';
	warningDiv.style.top = posY + 5 + 'px';
	warningDiv.style.visibility = "visible";
	var parent = document.getElementById("hint");
	parent.appendChild(warningDiv);
};

var isPhoneNumber = function(value) {
	if (!value)
		value = "";
	var result = false;
	var regex = /^\d{10}$/;
	if (value.match(regex)) {
		result = true;
	}
	return result;
}

var isNumber = function(value) {
	if (!value)
		value = "";
	var result = false;
	var regex = /^\d{1,}$/;
	if (value.match(regex)) {
		result = true;
	}
	return result;
}

var isNotEmpty = function(value) {
	if (!value)
		value = "";
	return value.trim().length > 0;
}

var isString = function(value) {
	if (!value)
		value = "";
	var result = false;
	var regex = /^[a-zA-Z]{1,}$/;
	if (value.match(regex)) {
		result = true;
	}
	return result;
}

var isStringAndNumber = function(value) {
	if (!value)
		value = "";
	var result = false;
	var regex = /^[a-zA-Z0-9\s]{1,}$/;
	if (value.match(regex)) {
		result = true;
	}
	return result;
}

var isDateTime = function(value) {
	if (!value)
		value = "";
	var result = false;
	var regex = /^(\d{4})\-(\d{2})\-(\d{2}) (\d{2}):(\d{2}):(\d{2})/;
	if (value.match(regex)) {
		result = true;
	}
	return result;
}
