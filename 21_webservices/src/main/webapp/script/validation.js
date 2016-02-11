function validateForm(form, options) {
    var isValid = true;
    for (var option in options) {
        if (options.hasOwnProperty(option)) {
            var validators = options[option];
            var field = form[option];
            for (var validation in validators) {
                if (validators.hasOwnProperty(validation)) {
                    var validator = validators[validation];
                    if (!validator.isValid(field)) {
                        var errorMsg = document.getElementById("errorMsg");
                        errorMsg.innerHTML = field.name + validator.message;
                        errorMsg.style.display = "block";
                        isValid = false;
                    }
                }
                if(!isValid){
                    break;
                }
            }
        }
        if(!isValid){
            break;
        }
    }
    return isValid;
}


var requiredValidate = {
    isValid: function (elem) {
        return elem.value !== "";
    }, message: " field must not be empty"
}

var lettersValidate = {
    isValid: function (elem) {
        return /^[a-zA-Z\s]+$/.test(elem.value);
    }, message: " must contains letters only"
}

var lettersAndNumbersValidate = {
    isValid: function (elem) {
        return /^[a-zA-Z0-9\s]+$/.test(elem.value);
    }, message: " must contains letters and numbers only"
}

var optionsForAddCategoryForm = {
    categoryName: [requiredValidate, lettersValidate]
};

var optionsForLoginForm = {
    login: [requiredValidate, lettersAndNumbersValidate],
    password: [requiredValidate, lettersAndNumbersValidate]
};

var optionsForUserForm = {
    userLogin: [requiredValidate, lettersAndNumbersValidate],
    userPassword: [requiredValidate, lettersAndNumbersValidate],
};

var optionsForReaderForm = {
    clientFirstName: [requiredValidate, lettersValidate],
    clientLastName: [requiredValidate, lettersValidate],
    clientPhone: [requiredValidate, lettersAndNumbersValidate],
    clientEmail: [requiredValidate, lettersAndNumbersValidate],
};