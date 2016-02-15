1) REST:
Create Term:
POST http://localhost:8080/21_webservices-0.0.1-SNAPSHOT/rest/term/create
JSON input {"termId":5,"termName":"Spring-2018"}

2) SOAP:
2.1) Create User:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:user="http://localhost:8080/21_webservices-0.0.1-SNAPSHOT/ws/soap/user">
   <soapenv:Header/>
   <soapenv:Body>
      <user:createUserRequest>
         <user>
            <login>test</login>
            <password>test</password>
            <email>test@mail.com</email>
            <role>
               <roleId>2</roleId>
               <roleName>manager</roleName>
            </role>
         </user>
      </user:createUserRequest>
   </soapenv:Body>
</soapenv:Envelope>

2.2) Update User
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:user="http://localhost:8080/21_webservices-0.0.1-SNAPSHOT/ws/soap/user">
   <soapenv:Header/>
   <soapenv:Body>
      <user:updateUserRequest>
         <user>
            <!--Optional:-->
            <userId>3</userId>
            <login>test_update</login>
            <password>test</password>
            <email>test@mail.com</email>
            <role>
               <roleId>2</roleId>
               <roleName>manager</roleName>
            </role>
         </user>
      </user:updateUserRequest>
   </soapenv:Body>
</soapenv:Envelope>