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

	function showHint(item) {
		item.classList.remove('hidden');
		item.classList.add('visible');
	}

	function hideHint(item) {
		item.classList.remove('visible');
		item.classList.add('hidden');
	}

	function hasClass(elem, clazz) {
		return (' ' + elem.className + ' ').indexOf(' ' + clazz + ' ') > -1;
	}

	function submitListener(e) {
		resetSubmitContainer(e.target);
		if (!validate(e.target, options)) {
			e.returnValue = false;
		}
	}

	function validate(form, options) {
		var flag = true;
		for ( var field in options) {
			var input = form.querySelector('[name="' + field + '"');
			if (input != null) {
				for ( var funcIndex in options[field][0].isValid) {
					var status = options[field][0].isValid[funcIndex](input);
					if (!status) {
						var divNode = document.createElement('div');
						divNode.innerHTML += options[field][0].message[funcIndex];
						divNode.className = 'error';
						insertAfter(divNode, input.parentNode
								.querySelector('div.hidden'));
					}
					flag = flag && status;
				}
			}
		}
		return flag;
	}

	function resetSubmitContainer(formElement) {
		var formDivs = formElement.querySelectorAll('div.formElement');
		for ( var index in formDivs) {
			if (index !== 'length' && index !== 'item') {
				var formDiv = formDivs[index];
				var errorDivs = formDiv.querySelectorAll('div.error');
				for ( var divIndex in errorDivs) {
					if (divIndex !== 'length' && divIndex !== 'item') {
						var errorDiv = errorDivs[divIndex];
						formDiv.removeChild(errorDiv);
					}
				}
			}
		}
	}

	function insertAfter(newNode, referenceNode) {
		referenceNode.parentNode.insertBefore(newNode,
				referenceNode.nextSibling);
	}
}

var nonEmptyValidator = function(domElement) {
	return domElement.value.trim() !== '';
}

var lettersOnlyValidator = function(domElement) {
	return /^[a-zA-Z\s]+$/.test(domElement.value);
}

var numbersOnlyValidator = function(domElement) {
	return /^[0-9]+$/.test(domElement.value);
}