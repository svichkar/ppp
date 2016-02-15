 function notEmptyElement (domElement) {
     return !!domElement.value;
 };

function isLengthValid (domElement) {
    return domElement.value.length > 3;
};

function notLonger (domElement) {
    return domElement.value.length < 32;
};

function lettersOnly (domElement) {
    return /^[a-zA-Z]+$/.test(domElement.value);
};

function isEmailValid (domElement) {
    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return regex.test(domElement.value);
};

function isAlphanumeric (domElement) {
    return /^[a-z0-9]+$/i.test(domElement.value);
};

function isDateValid (domElement) {
    return new Date(domElement.value) < new Date();
};
