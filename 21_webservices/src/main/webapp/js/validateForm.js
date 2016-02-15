var REQUIRED = {
	isValid : function(domElement) {
		return domElement.value;
	},
	message : "The name field is required. Please enter the name"
}

var LETTERS_ONLY = {
	isValid : function(domElement) {
		return (/^[a-zA-Z]+$/.test(domElement.value));
	},
	message : "The field should contain only letters. Numbers are not allowed"
}

var LETTERS_ONLY_OR_EMPTY = {
	isValid : function(domElement) {
		return (/^[a-zA-Z]+$|^$/.test(domElement.value));
	},
	message : "The field should contain only letters. Numbers are not allowed"
}

var TERM = {
	isValid : function(domElement) {
		return (/^[a-zA-Z]+\-\d{4}$/.test(domElement.value));
	},
	message : "The field has incorrect format"
}

var DATE = {
	isValid : function(domElement) {
		return (/^(\d{4})\-(0\d|1[012])\-([0-2]\d|3[01])$/
				.test(domElement.value));
	},
	message : "The admission date should be in format YYYY-MM-DD"
}

var EMAIL = {
	isValid : function(domElement) {
		return (/^[a-zA-Z0-9_\-\.]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$/
				.test(domElement.value));
	},
	message : "The email address is invalid. Please enter the correct email"
}

var optionsForAddStudent = {
	newFirstName : [ REQUIRED, LETTERS_ONLY ],
	newLastName : [ REQUIRED, LETTERS_ONLY ],
	newAdmissionDate : [ REQUIRED, DATE ],
	newGroupName : [ REQUIRED ],
	newStatusName : [ REQUIRED ],
	newTermName : [ REQUIRED ]
}

var optionsForSearchStudent = {
	searchLastName : [ LETTERS_ONLY_OR_EMPTY ]
}

var optionsForUpdateStudent = {
	updatedFirstName : [ REQUIRED, LETTERS_ONLY ],
	updatedLastName : [ REQUIRED, LETTERS_ONLY ],
	updatedAdmissionDate : [ REQUIRED, DATE ],
	updatedGroupName : [ REQUIRED ],
	updatedStatusName : [ REQUIRED ],
	updatedTermName : [ REQUIRED ]
}

var optionsForAddSubject = {
	newSubjectName : [ REQUIRED, LETTERS_ONLY ],
	newTermName : [ REQUIRED ]
}

var optionsForSearchSubject = {
	searchSubjectName : [ LETTERS_ONLY_OR_EMPTY ]
}

var optionsForUpdateSubject = {
	updatedSubjectName : [ REQUIRED, LETTERS_ONLY ],
	updatedTermName : [ REQUIRED ]
}

var optionsForAddTerm = {
	newTermName : [ REQUIRED, TERM ]
}

var optionsForUpdateTerm = {
	updatedTermName : [ REQUIRED, TERM ]
}

var optionsForAddJournal = {
	newStudent : [ REQUIRED ],
	newSubjectName : [ REQUIRED ],
	newGradeName : [ REQUIRED ]
}

var optionsForUpdateJournal = {
	updatedStudent : [ REQUIRED ],
	updatedSubject : [ REQUIRED ],
	updatedGrade : [ REQUIRED ]
}

var optionsForViewJournal = {
	term_id : [ REQUIRED ]
}

var optionsForAddUser = {
	newLogin : [ REQUIRED, LETTERS_ONLY ],
	newEmail : [ REQUIRED, EMAIL ],
	newPassword : [ REQUIRED ],
	newRole : [ REQUIRED ]
}

var optionsForUpdateUser = {
	updatedLogin : [ REQUIRED, LETTERS_ONLY ],
	updatedEmail : [ REQUIRED, EMAIL ],
	updatedPassword : [ REQUIRED ],
	updatedRole : [ REQUIRED ]
}

function showError(container, error) {
	var msg = document.createElement('span');
	msg.className = "error-hint";
	msg.innerHTML = error;
	container.appendChild(msg);
}

function resetError(container) {
	container.className = '';
	while (container.lastChild.className == "error-hint") {
		container.removeChild(container.lastChild);
	}
}

function validate(form, options) {
	var isValid = true;
	for ( var i in options) {
		if (options.hasOwnProperty(i)) {
			var validators = options[i];
			var field = form[i];
			resetError(field.parentNode);
			for ( var j in validators) {
				if (validators.hasOwnProperty(j)) {
					var validator = validators[j];
					if (!validator.isValid(field)) {
						showError(field.parentNode, validator.message);
						isValid = false;
					}
				}
			}
		}
	}
	return isValid;
}