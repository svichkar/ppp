document.onreadystatechange = function() {
	if (document.readyState === 'complete') {
		var tables = document.getElementsByTagName('table');
		if (tables.length > 0) {
			document.getElementsByTagName('table')[0].onclick = function(e) {
				var click_Class = "clicked_Row";
				if (!e)
					e = window.event;
				var elem = e.target || e.srcElement;
				while (!elem.tagName || !elem.tagName.match(/td|th|table/i))
					elem = elem.parentNode;

				if (elem.parentNode.tagName == 'TR'
						&& elem.parentNode.parentNode.tagName == 'TBODY') {
					var click_Class_Reg = new RegExp("\\b" + click_Class
							+ "\\b");
					var row = elem.parentNode;

					if (row.getAttribute('clicked_Row')) {
						row.removeAttribute('clicked_Row');
						row.className = row.className.replace(click_Class_Reg,
								"");
					} else {
						row.className += " " + click_Class;
						row.setAttribute('clicked_Row', true);
					}
				}
			}
		}
	}
}
