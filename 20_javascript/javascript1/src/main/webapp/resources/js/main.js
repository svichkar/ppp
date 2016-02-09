
	var showingTooltip;
	function toolTip(event) {	
      var target = event.target;

      var tooltip = target.getAttribute('data-tooltip');
      if (!tooltip) return;

      var tooltipElem = document.createElement('div');
      tooltipElem.className = 'tooltip';
      tooltipElem.innerHTML = tooltip;
      document.body.appendChild(tooltipElem);

      var coords = target.getBoundingClientRect();
      var left = coords.right;
      var top = coords.top;
      tooltipElem.style.left = left + 'px';
      tooltipElem.style.top = top + 'px';

      showingTooltip = tooltipElem;
    };
    
   function removeTip(event) {

        if (showingTooltip) {
          document.body.removeChild(showingTooltip);
          showingTooltip = null;
        }
      };


//////////////////
function addRowhandlers(event){
	var target = event.target;
	var parent = target.parentElement;
	if (parent.nodeName != "TR")
		return;
	if (parent.style.backgroundColor == "") {
		parent.style.backgroundColor = 'red';
	} else {
		parent.style.backgroundColor = "";
	}
}

var REQUIRED = {
	isValid : function(domElement) {
			return domElement.value;
	},
	message : 'the field is required'

}
var LETTERS_ONLY = {
	isValid : function(domElement) {
			return (/^[a-zA-Z]+$/.test(domElement.value));
	},
	message : 'has to be consisted only from letters'
}
var NUMBERS_ONLY = {
	isValid : function(domElement) {
			return (/[0-9]+$/.test(domElement.value));
	},
	message : "has to be consisted only from numbers"
}
var EMAIL = {
	isValid : function(domElement) {
			return (/^[a-zA-Z0-9_\-\.]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$/
			.test(domElement.value));	
	},
	message : "An error in E-mail!"
}

var NOTLESSTHANFOUR = {
	isValid : function(domElement) {
			return (domElement.value.length >= 4);
	},
	message : 'has to be not less than 4 symbols!'
}

var LATIN_W_ARABIC_N = {
	isValid : function(domElement) {
			return (/^[a-zA-Z0-9/\s/]+$/.test(domElement.value));
	},
	message : 'has to be consisted from arabic numbers and latin characters!'
}

// /addreader options
var addReaderOpt = {
	readerfirstname : [ REQUIRED, LETTERS_ONLY ],
	readerlastname : [ REQUIRED, LETTERS_ONLY, NOTLESSTHANFOUR ],
	email : [ REQUIRED, EMAIL ]
}

///addbook options
var addBookOpt = {
		bookname : [ REQUIRED, LATIN_W_ARABIC_N ],
		authorfirstname : [ REQUIRED, LETTERS_ONLY ],
		authorlastname : [ REQUIRED, LETTERS_ONLY, NOTLESSTHANFOUR ],
		selectcategory : [ REQUIRED ],
		selectcell : [ REQUIRED ],
		count : [REQUIRED, NUMBERS_ONLY]
	}

///addbook options
var addCategoryOpt = {
		categoryname : [ REQUIRED, LATIN_W_ARABIC_N ],
	}

///manageuser options
var addUserOpt = {
		username : [ REQUIRED, LATIN_W_ARABIC_N, NOTLESSTHANFOUR ],
		password : [ REQUIRED, LATIN_W_ARABIC_N, NOTLESSTHANFOUR ],
		selectrole : [ REQUIRED ]
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
	return isValid;
}