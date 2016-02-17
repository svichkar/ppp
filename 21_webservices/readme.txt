For application
login:mor
password:mor
Page "Workers" is allowed only



Spring WS wsdl:
http://10.10.202.59:8080/soap/userService.wsdl

Create:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://www.servicestation.nixsolutions.com/soap">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:createUserRequest>
         <soap:user>
            <soap:login>tester</soap:login>
            <soap:password>tester</soap:password>
            <soap:role>
               <soap:roleId>2</soap:roleId>
               <soap:roleName>ROLE_CLIENT</soap:roleName>
            </soap:role>
         </soap:user>
      </soap:createUserRequest>
   </soapenv:Body>
</soapenv:Envelope>


Update:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://www.servicestation.nixsolutions.com/soap">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:updateUserRequest>
         <soap:user>
            <soap:userId>4</soap:userId>
            <soap:login>mor</soap:login>
            <soap:password>morrrrra</soap:password>
            <soap:role>
               <soap:roleId>1</soap:roleId>
               <soap:roleName>ROLE_MANAGER</soap:roleName>
            </soap:role>
         </soap:user>
      </soap:updateUserRequest>
   </soapenv:Body>
</soapenv:Envelope>


Jersey Rest WS:
DELETE http://localhost:8080/services/worker/2

PUT http://localhost:8080/services/worker/update
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>

<employee>

	<employeeCategory>

            <employeeCategoryId>1</employeeCategoryId>

            <employeeCategoryName>electricity</employeeCategoryName>

        </employeeCategory>

        <employeeId>1</employeeId>

        <firstName>Vit</firstName>

        <lastName>Ryb</lastName>

</employee>

GET http://localhost:8080/services/worker/getAll
GET http://localhost:8080/services/worker/1

POST http://localhost:8080/services/worker/create/
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>

<employee>

    <employeeCategory>
        <employeeCategoryId>1</employeeCategoryId>

        <employeeCategoryName>electricity</employeeCategoryName>

    </employeeCategory>

    <firstName>tester</firstName>

    <lastName>tester</lastName>

</employee>
