var options = {

firstName: [
       {
           isValid: function (domElement) {
               return notEmptyElement(domElement);
           },
           message: "The name is required"
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
              message: "Last name is required"
          },
          {
              isValid: function (domElement) {
                 return lettersOnly(domElement);
              },
              message: "Last name should contain only letters"
          }
      ],

selectedGroup: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Group is required"
          }
   ],

selectedSubject: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Subject is required"
          }
   ],

selectedGrade: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Grade is required"
          }
   ]
};