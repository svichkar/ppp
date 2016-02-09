//function addRowHandlers() {
//	var table = document.getElementById("tableId");
//
//	var rows = table.getElementsByTagName("tr");
//	for (i = 0; i < rows.length; i++) {
//		var currentRow = table.rows[i];
//		var createClickHandler = function(row) {
//			return function() {
//				if (row.style.backgroundColor == "") {
//					row.style.backgroundColor = 'red';
//				} else {
//					row.style.backgroundColor = "";
//				}
//			};
//		};
//		currentRow.onclick = createClickHandler(currentRow);
//	}
//}

//function addRowHandlers(event) {
//    if (!event.target.hasAttribute('painter')) return;
//
//    var painter = event.target;
//
//    if (painter.style.backgroundColor == "") {
//		painter.style.backgroundColor = 'red';
//	} else {
//		painter.style.backgroundColor = "";
//	}
//  };

var REQUIRED = {
	isValid : function(domElement) {
		if (!domElement.value) {
			return false;
		} else {
			return true;
		}
	},
	message : 'the field is required'

}
var LETTERS_ONLY = {
	isValid : function(domElement) {
		if (!(/^[a-zA-Z]+$/.test(domElement.value))) {
			return false;
		}
	},
	message : 'domElement has to be consisted only from letters'
}
var NUMBERS_ONLY = {
	isValid : function(domElement) {
		if (!(/[0-9]+$/.test(domElement.value))) {
			alert('');
			return false;
		} else {
			return true;
		}
	},
	message : "domElement has to be consisted only from numbers"
}
var EMAIL = {
	isValid : function(domElement) {
		if (!(/^[a-zA-Z0-9_\-\.]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$/
				.test(domElement.value))) {
			return false;
		}
	},
	message : "An error in E-mail!"
}

var NOTLESSTHANFOUR = {
	isValid : function(domElement) {
		if (domElement.length < 4) {
			return false;
		}
	},
	message : 'the domElement has to be not less than 4 symbols!'
}

var LATIN_W_ARABIC_N = {
	isValid : function(domElement) {
		if (!(/^[a-zA-Z0-9]+$/.test(domElement.value))) {
			return false;
		}
	},
	message : 'domElement has consist from arabic numbers and latin characters!'
}

// /addreader options
var addReaderOpt = {
	readerfirstname : [ REQUIRED, LETTERS_ONLY ],
	readerlastname : [ REQUIRED, LETTERS_ONLY, NOTLESSTHANFOUR ],
	email : [ REQUIRED, EMAIL ]
}

// /
function showError(container, errorMessage) {
	var msgElem = document.createElement('span');
	msgElem.className = "error-message";
	msgElem.innerHTML = errorMessage;
	container.appendChild(msgElem);
}

function resetError(container) {
	container.className = '';
	while (container.lastChild.className == "error-message") {
		container.removeChild(container.lastChild);
	}
}

function validate(form, options) {
	var isValid = true;
	for ( var key in options) {
		if (options.hasOwnProperty(key)) {
			var validators = options[key];
			var field = form[key];
			resetError(field.parentNode);
			for ( var key2 in validators) {
				if (validators.hasOwnProperty(key2)) {
					var validator = validators[key2];
					if (!validator.isValid(field)) {
						showError(field.parentNode, validator.message);
						isValid = false;
					}
				}
			}
		}
	}
	if (isValid){
		form.submit();
	}
}