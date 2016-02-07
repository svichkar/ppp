/**
 * Created by rybkinrolla on 07.02.2016.
 */
onload = function () {
    var allInput = document.getElementsByTagName("input");
    for (i = 0; i < allInput.length; i++) {
        allInput[i].onfocus = showHint
    }
};

function showHint() {
    var hint = document.getElementById("hint");
    hint.innerHTML = this.name + " " + this.value;
    hint.style.display = "block";
};

