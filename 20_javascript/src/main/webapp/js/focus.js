/**
 * Created by rybkinrolla on 07.02.2016.
 */
window.addEventListener('load', function () {
    var allInput = document.getElementsByTagName("*");
    for (i = 0; i < allInput.length; i++) {
        allInput[i].onfocus = showHint
        allInput[i].onblur = hideHint
    }
});

function showHint() {
    var hint = document.getElementById("hint");
    if (typeof this.title !== "undefined" && this.title != "") {
        hint.innerHTML = this.title;
        hint.style.display = "block";
    }

};

function hideHint() {
    var hint = document.getElementById("hint");
    hint.style.display = "none";
};

