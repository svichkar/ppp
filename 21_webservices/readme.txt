Playload:

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