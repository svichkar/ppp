function validateForm(form, options) {
    var isValid = true;
    for (var option in options) {
        if (options.hasOwnProperty(option)) {
            var validators = options[option];
            var field = form[option];
            for (var keyValidator in validators) {
                if (validators.hasOwnProperty(keyValidator)) {
                    var validator = validators[keyValidator];
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
var firstLetterUpperValidate = {
    isValid: function (elem) {
        return /^[A-Z]$/.test(elem.value[0]);
    }, message: " first letter must be in upper case"
}

var lettersAndNumbersValidate = {
    isValid: function (elem) {
        return /^[a-zA-Z0-9\s]+$/.test(elem.value);
    }, message: " must contains letters and numbers only"
}

var optionsForCarForm = {
    brand: [requiredValidate, lettersValidate, firstLetterUpperValidate],
    model_name: [requiredValidate, lettersAndNumbersValidate, firstLetterUpperValidate],
    VIN: [requiredValidate, lettersAndNumbersValidate]
};

var optionsForLoginForm = {
    login: [requiredValidate, lettersAndNumbersValidate],
    password: [requiredValidate, lettersAndNumbersValidate]
};

var optionsForClientForm = {
    first_name: [requiredValidate, lettersValidate, firstLetterUpperValidate],
    last_name: [requiredValidate, lettersValidate, firstLetterUpperValidate],
    user_login:[requiredValidate, lettersAndNumbersValidate],
    user_password:[requiredValidate, lettersAndNumbersValidate]
};

var optionsForWorkerForm = {
    first_name: [requiredValidate, lettersValidate, firstLetterUpperValidate],
    last_name: [requiredValidate, lettersValidate, firstLetterUpperValidate]
};