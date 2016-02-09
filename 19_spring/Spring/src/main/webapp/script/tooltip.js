document.onreadystatechange=function(){
var m, k;
m=document.getElementsByTagName("input");
k=m.length;
while(k--){
if(m[k].getAttribute("type")=="text"){
m[k].onclick=clickTR;
}
}
}

function clickTR(){
alert(this.getAttribute("title"));
 }