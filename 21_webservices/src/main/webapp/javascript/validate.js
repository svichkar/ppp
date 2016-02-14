function validateForm(form, options) {

    clearErrors();

    var isValid = true;
    for (var keyOption in options) {
        if (options.hasOwnProperty(keyOption)) {
            var validators = options[keyOption];
            var field = form[keyOption];
            for (var keyValidator in validators) {
                if (validators.hasOwnProperty(keyValidator)) {
                    var validator = validators[keyValidator];
                    if (!validator.isValid(field)) {
                        var errorMsg = field.nextElementSibling;
                        errorMsg.innerHTML = validator.message;
                        errorMsg.style.visibility = "visible";
                        field.focus();
                        return false;
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
};

function clearErrors() {
    var list = document.getElementsByClassName('errorValidation');
    for (var i = 0; i < list.length; i++) {
        list[i].value = "";
        list[i].style.visibility = "hidden";
    }
}