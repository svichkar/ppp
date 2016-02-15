* REST Jersey playload:

1) create student:

method: POST
Content-Type: application/xml

http://localhost:8080/web-services/ws/rest/students/createStudent

<student>
   <admissionDate>2015-03-25</admissionDate>
   <firstName>Konstantin</firstName>
   <lastName>Kovalenko</lastName>
   <status>
      <statusId>1</statusId>
      <statusName>active</statusName>
   </status>
   <studentGroup>
      <groupId>1</groupId>
      <groupName>java 15-1</groupName>
   </studentGroup>
   <term>
      <termId>2</termId>
      <termName>second</termName>
   </term>
</student>

2) update student:

method: PUT
Content-Type: application/xml
http://localhost:8080/web-services/ws/rest/students/updateStudent

<student studentId="41">
   <admissionDate>2015-03-25</admissionDate>
   <firstName>Koval</firstName>
   <lastName>OLeg</lastName>
   <status>
      <statusId>1</statusId>
      <statusName>active</statusName>
   </status>
   <studentGroup>
      <groupId>2</groupId>
      <groupName>java 15-2</groupName>
   </studentGroup>
   <term>
      <termId>1</termId>
      <termName>first</termName>
   </term>
</student>

3) delete student

method: DELETE
http://localhost:8080/web-services/ws/rest/students/deleteStudent/{id}

4) getStudentByID

method: GET
http://localhost:8080/web-services/ws/rest/students/getStudent/{id}



* SOAP Spring WS playload:

1) 'createUser' operation:

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:user="http://www.user.com" xmlns:sch="http://xml.netbeans.org/schema/">
   <soapenv:Header/>
   <soapenv:Body>
      <user:createUserRequest>
         <user>
            <email>newUser@mail.ru</email>
            <firstName>New</firstName>
            <lastName>User</lastName>
            <login>new</login>
            <role>
               <roleId>2</roleId>
               <roleName>user</roleName>
            </role>
            <userPassword>new</userPassword>
         </user>
      </user:createUserRequest>
   </soapenv:Body>
</soapenv:Envelope>

2) 'updateUser' operation:

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:user="http://www.user.com" xmlns:sch="http://xml.netbeans.org/schema/">
   <soapenv:Header/>
   <soapenv:Body>
      <user:updateUserRequest>
         <user>
            <email>updated@mail.ru</email>
            <firstName>Admin</firstName>
            <lastName>Updated</lastName>
            <login>new</login>
            <role>
               <roleId>1</roleId>
               <roleName>admin</roleName>
            </role>
            <userId>10</userId>
            <userPassword>updated</userPassword>
         </user>
      </user:updateUserRequest>
   </soapenv:Body>
</soapenv:Envelope>