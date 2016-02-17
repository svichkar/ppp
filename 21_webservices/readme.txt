реализован только рест сервис, лежит тут https://bitbucket.org/nixsolutions/javappp/src/284f7b0c5c1f26ebb4ae4b2e9cfc53f0b61e5c19/21_webservices/src/main/java/com/nixsolutions/hibernate/service/?at=kozlovskij
//create
POST http://localhost:8080/Jersey/rest/user/create
{"userId":"0","login":"test","password":"test","roleId":"2"}
//update
PUT http://localhost:8080/Jersey/rest/user/update
{"userId":"2","login":"test","password":"test","role":{"roleId":"1"}}
//delete
DELETE http://localhost:8080/Jersey/rest/user/delete
{"userId":"2","login":"test","password":"test","role":{"roleId":"1"}}
//selectAll
GET http://localhost:8080/Jersey/rest/user/getAll
//selectById
GET http://localhost:8080/Jersey/rest/user/get/1