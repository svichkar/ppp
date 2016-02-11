var options = {

subjectName: [
       {
           isValid: function (domElement){
           return notEmptyElement(domElement);
           },
           message: "Subject name is required"
       }
   ],
selectedTerm: [
          {
              isValid: function (domElement){
              return notEmptyElement(domElement);
              },
              message: "Term is required"
          }
    ]
};