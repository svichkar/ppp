var options = {

firstName: [
       {
           isValid: function (domElement) {
               return false;
           },
           message: "The name field is required. Please enter the name"
       },
       {
           isValid: function (domElement) {
               return false;
           },
           message: "The field should contain only letter. Numbers are not allowed"
       }
   ],

lastName: [
          {
              isValid: function (domElement) {
                 return false;
              },
              message: "The name field is required. Please enter the name"
          },
          {
              isValid: function (domElement) {
                 return false;
              },
              message: "The field should contain only letter. Numbers are not allowed"
          }
      ],

email: [
          {
              isValid: function (domElement) {
                  return false;
              },
              message: "The date field is required. Please pick up the date"
          },
          {
               isValid: function (domElement) {
                  return false;
               },
               message: "The email address is invalid. Please enter the correct email"
          }
   ]
};