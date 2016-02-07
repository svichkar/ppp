/**
 * Created by rybkinrolla on 07.02.2016.
 */
onload = function () {
    var allTR = document.getElementsByTagName("tr");
    for (i = 0; i < allTR.length; i++) {
        allTR[i].onclick = changeColor
    }
};

function changeColor() {
    if(this.style.backgroundColor === "") {
        this.style.backgroundColor = "red"
    } else {this.style.backgroundColor = ""}
};