document.onreadystatechange = function() {
	if (document.readyState === 'complete') {
		var showingTooltip;

		document.onmouseover = function(e) {
			var target = e.target;
			var tooltip = target.getAttribute('tooltip');
			if (!tooltip)
				return;
			var popUp = document.getElementById('myAlt');
			popUp.innerHTML = tooltip;
			popUp.style.visibility = "visible";
			popUp.style.left = target.getBoundingClientRect().left + target.offsetWidth + 'px';
			popUp.style.top = target.getBoundingClientRect().top + 'px';
			showingTooltip = popUp;
		}

		document.onmouseout = function(e) {
			if (showingTooltip) {
				var popUp = document.getElementById('myAlt');
				popUp.style.visibility = "hidden";
				popUp.innerHTML = "";
				popUp.style.left = 0;
				popUp.style.top = 0;
				showingTooltip = null;
			}
		};

		document.getElementsByTagName('table')[0].onclick = function(e) {
			var click_Class = "clicked_Row";
			if (!e)
				e = window.event;
			var elem = e.target || e.srcElement;
			while (!elem.tagName || !elem.tagName.match(/td|th|table/i))
				elem = elem.parentNode;

			if (elem.parentNode.tagName == 'TR'
					&& elem.parentNode.parentNode.tagName == 'TBODY') {
				var click_Class_Reg = new RegExp("\\b" + click_Class + "\\b");
				var row = elem.parentNode;

				if (row.getAttribute('clicked_Row')) {
					row.removeAttribute('clicked_Row');
					row.className = row.className.replace(click_Class_Reg, "");
					row.className += " " + hover_Class;
				} else {
					row.className += " " + click_Class;
					row.setAttribute('clicked_Row', true);
				}
			}
		}

	}
}