var options = {

firstName: [
       {
           isValid: function (domElement) {
               return notEmptyElement(domElement);
           },
           message: "Name is required"
       },
       {
           isValid: function (domElement) {
               return lettersOnly(domElement);
           },
           message: "Name should contain only letter"
       },
       {
          isValid: function (domElement) {
          return notLonger (domElement);
          },
          message: "Name cannot be longer than 32 symbols"
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
          },
          {
              isValid: function (domElement) {
              return notLonger (domElement);
              },
              message: "Last name cannot be longer than 32 symbols"
          }
      ],

email: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Email is required"
          },
          {
               isValid: function (domElement) {
                  return isEmailValid(domElement);
               },
               message: "The email address is not valid"
          }
   ],

login: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Login is required"
          },
          {
               isValid: function (domElement) {
                  return isLengthValid (domElement);
               },
               message: "Login can't be shorter than 3 symbols"
          },
          {
               isValid: function (domElement) {
                  return isAlphanumeric (domElement);
               },
               message: "Login should be alphanumeric only"
          },
          {
               isValid: function (domElement) {
                  return notLonger (domElement);
               },
               message: "Login cannot be longer than 32 symbols"
          }
   ],

userPassword: [
                  {
                      isValid: function (domElement) {
                          return notEmptyElement(domElement);
                  },
                  message: "Password is required"
              },
              {
                   isValid: function (domElement) {
                      return isLengthValid (domElement);
                   },
                   message: "Password can't be shorter than 3 symbols"
              },
              {
                   isValid: function (domElement) {
                      return isAlphanumeric (domElement);
                   },
                   message: "Password should be alphanumeric only"
              },
              {
                   isValid: function (domElement) {
                      return notLonger (domElement);
                   },
                   message: "Password cannot be longer than 32 symbols"
              }
],

selectedRole: [
          {
              isValid: function (domElement) {
                  return notEmptyElement(domElement);
              },
              message: "Role is required"
          }
   ]
};