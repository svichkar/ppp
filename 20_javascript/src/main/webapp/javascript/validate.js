function prepareForms () {
var allForms = document.querySelectorAll('form.validatedForm');
    for (var i = 0; i < allForms.length; i++) {
        allForms[i].addEventListener('submit', formListener, false)
    }
};

function formListener(e) {
    if (!validate(e.target, options)) {
	    e.returnValue = false;
	}
};

function validate (form, options) {
       		var flag = true;
    		for (var field in options) {
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
};

function insertError(newNode, referenceNode) {
		referenceNode.parentNode.insertBefore(newNode,
				referenceNode.nextSibling);
	}