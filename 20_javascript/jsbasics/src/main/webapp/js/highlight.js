document.onreadystatechange = function() {
	if (document.readyState === 'complete') {
		var tableRows = document.querySelectorAll('tr.highlightable td');
		for ( var index in tableRows) {
			if (!isNaN(index)) {
				var element = tableRows[index];
				element.addEventListener('click', clickListener, true);
			}
		}
	}

	function clickListener(e) {
		var clickedItem = e.target;
		if (clickedItem.nodeName === 'TD') {
			highlight(clickedItem.parentElement);
		}
		e.stopPropagation();
	}

	function highlight(item) {
		var clazz = 'highlight';
		if (hasClass(item, clazz)) {
			item.classList.remove(clazz);
		} else {
			item.classList.add(clazz);
		}
	}

	function hasClass(elem, clazz) {
		return (' ' + elem.className + ' ').indexOf(' ' + clazz + ' ') > -1;
	}
}