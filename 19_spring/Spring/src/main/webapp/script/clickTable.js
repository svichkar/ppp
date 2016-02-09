document.onreadystatechange=function(){
var m, k;
m=document.querySelectorAll(".table tr");
k=m.length;
while(k--){m[k].onclick=clickTR};
 }

function clickTR(){
if(this.className==="act"){this.className="";}
else{this.className="act";}
 }