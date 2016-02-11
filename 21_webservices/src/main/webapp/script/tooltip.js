window.addEventListener('load',function(){
    var input = document.getElementsByTagName("*");
    for (i=0;i<input.length;i++){
        input[i].onfocus = show
        input[i].onblur = hide
    }
});

function show () {
    var tooltip = document.getElementById("hint");
    if (typeof this.title !== "undefined" && this.title != ""){
       tooltip.innerHTML = this.title;
       tooltip.style.display = "block";
    }
};
function hide (){
    var tooltip = document.getElementById("hint");
    tooltip.style.display = "none";
}
