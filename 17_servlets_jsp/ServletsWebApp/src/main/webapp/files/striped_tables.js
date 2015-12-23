
function fgetAllDataTables()
{
	if (!document.getElementsByTagName) return false;

	var eleTables = document.getElementsByTagName("table");

	for (var i=0; i < eleTables.length; i++)
	{
		if (eleTables[i].className == "datatable")
		{
			fStripes(eleTables[i]);
		}
	}
}

function fStripes(eleTable)
{
	var eleTableRows = eleTable.getElementsByTagName("tr");

	for (var i=1; i < eleTableRows.length; i++)
	{
		eleTableRows[i].className = "trbg";
		i++;
	}
}

function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    }
  }
}

addLoadEvent(fgetAllDataTables);