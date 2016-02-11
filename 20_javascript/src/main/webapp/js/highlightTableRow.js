function highlightTableRow(tableClass) {
	var tables = document.getElementsByClassName(tableClass);
	for (i = 0; i < tables.length; i++) {
		var rows = tables[i].getElementsByTagName("TR");
		for (j = 0; j < rows.length; j++) {
			rows[j].onclick = function() {
				if (this.style.backgroundColor === "") {
					this.style.backgroundColor = "lightgreen"
				} else {
					this.style.backgroundColor = ""
				}
			}
		}
		;
	}
};