# backbone in action: #

1) login / password: user / user

2) Navigate to 'Students(one page app)'

*Result:*
1) Backbone back-end:

- Represent list of students ('List of Students')
- Add/Edit/Delete operations

2) Implemented REST service 'studentService'

GET: http://localhost:8080/backbone/ws/rest/studentService/students
GET: http://localhost:8080/backbone/ws/rest/studentService/term
GET: http://localhost:8080/backbone/ws/rest/studentService/status
GET: http://localhost:8080/backbone/ws/rest/studentService/group
GET: http://localhost:8080/backbone/ws/rest/studentService/student/{id}

PUT: http://localhost:8080/backbone/ws/rest/studentService/student/{id}
Content-Type:application/json

DELETE: http://localhost:8080/backbone/ws/rest/studentService/student/{id}

POST: http://localhost:8080/backbone/ws/rest/studentService/student
Content-Type:application/json

*SRC locations:*
 - webapp/javascript/backbone  - backbone staff;
 - webapp/onePageApp.html - static html page
 - src/main/java/studentgrade/webservice/rest/StudentWebService.java - REST service