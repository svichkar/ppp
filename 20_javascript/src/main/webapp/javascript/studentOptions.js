var options = {

firstName: [
       {
           isValid: function (domElement) {
               // check if domElement is valid
               // and return boolean result;
           },
           message: "The name field is required. Please enter the name"
       },
       {
           isValid: function (domElement) {
               // check if only letters in element
               // and return boolean result;
           },
           message: "The field should contain only letter. Numbers are not allowed"
       }
   ],

lastName: [
          {
              isValid: function (domElement) {
                  // check if domElement is valid
                  // and return boolean result;
              },
              message: "The name field is required. Please enter the name"
          },
          {
              isValid: function (domElement) {
                  // check if only letters in element
                  // and return boolean result;
              },
              message: "The field should contain only letter. Numbers are not allowed"
          }
      ],

email: [
          {
              isValid: function (domElement) {
                  // check if domElement is valid
                  // and return boolean result;
              },
              message: "The date field is required. Please pick up the date"
          },
          {
               isValid: function (domElement) {
                   //…
               },
               message: "The email address is invalid. Please enter the correct email"
          }
   ]
};