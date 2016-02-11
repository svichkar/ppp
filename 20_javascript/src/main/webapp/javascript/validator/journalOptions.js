var options = {

firstName: [
       {
           isValid: function (domElement) {
               return notEmptyElement(domElement);
           },
           message: "The name field is required. Please enter the name"
       },
       {
           isValid: function (domElement) {
               return lettersOnly(domElement);
           },
           message: "Name should contain only letter"
       }
   ],

lastName: [
          {
              isValid: function (domElement) {
                 return notEmptyElement(domElement);
              },
              message: "The name field is required. Please enter the name"
          },
          {
              isValid: function (domElement) {
                 return lettersOnly(domElement);
              },
              message: "The field should contain only letters"
          }
      ],

selectedGroup: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Pick up the group"
          }
   ],

selectedSubject: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Pick up the subject"
          }
   ],

selectedGrade: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Pick up the grade"
          }
   ]
};