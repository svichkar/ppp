var options = {

termName: [
       {
           isValid: function (domElement){
           return notEmptyElement(domElement);
           },
           message: "Term name field is required. Please enter the term"
       }
   ]
};