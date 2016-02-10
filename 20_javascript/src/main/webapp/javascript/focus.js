function showTip(element, text) {
    var divMessage = element.parentElement.lastElementChild;
    divMessage.style.visibility = "visible";
	divMessage.innerHTML = text;
}

function hideTip(element) {
	var divMessage = element.parentElement.lastElementChild;
    divMessage.style.visibility = "hidden";
}
