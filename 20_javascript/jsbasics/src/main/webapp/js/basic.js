document.addEventListener("focus", focusInHandler(event), true);

function focusInHandler(event){
    event.fire("showHint");
}
function focusOutHandler(event){
	event.fire("hideHint");
}

function highlight(item) {
	var clazz = "highlight";
	if (hasClass(item, clazz)) {
		item.className = "";
	} else {
		item.className = clazz;
	}
}

function hasClass(elem, clazz) {
	return (" " + elem.className + " ").indexOf(" " + clazz + " ") > -1;
}

function showHint(item) {
	item.style.display = "block";
}

function hideHint(item) {
	item.style.display = "inline";
}