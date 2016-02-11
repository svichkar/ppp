var options = {

subjectName: [
       {
           isValid: function (domElement){
           return notEmptyElement(domElement);
           },
           message: "Subject name field is required. Please enter the subject"
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