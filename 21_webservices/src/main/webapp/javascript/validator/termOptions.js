var options = {

termName: [
       {
           isValid: function (domElement){
           return notEmptyElement(domElement);
           },
           message: "Term name is required"
       }
   ]
};