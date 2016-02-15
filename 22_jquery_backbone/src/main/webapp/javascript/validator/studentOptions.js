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

date: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Date is required"
          },
          {
               isValid: function (domElement) {
                  return isDateValid (domElement);
               },
               message: "Date is larger than today"
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

selectedTerm: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Term is required"
          }
   ],

selectedStatus: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Status is required"
          }
   ]
};