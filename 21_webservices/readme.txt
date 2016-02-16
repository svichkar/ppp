admin credentials: admin/1
user credentials: dinio/1

SOAP
endpoint: http://localhost:8080/spring/soap/user.wsdl

REST:
endpoint: http://localhost:8080/spring/rest/
get all cars(GET): http://localhost:8080/spring/rest/car/all
create car(POST): http://localhost:8080/spring/rest/car/create
  {
    "model": "SKODA",
    "vin": "SUPT3456D999996GG999",
    "description": "silver metal color",
    "customerId": 1,
    "id": 0,
    "fname": "Hans",
    "lname": "Bernanrd"
  }
get car(GET): http://localhost:8080/spring/rest/car/{define id of created car or car that exists in database}

update car(PUT):  http://localhost:8080/spring/rest/car/update
    {
    "model": "SKODA",
    "vin": "SUPT3456D999996GG999",
    "description": "silver color",
    "customerId": {define id that was returned in request above},
    "id": {define id that was returned in request above},
    "fname": "Hans",
    "lname": "Bernanrd"	
  }  

delete car(DELETE): http://localhost:8080/spring/rest/car/{define id of created car or car that exists in database}