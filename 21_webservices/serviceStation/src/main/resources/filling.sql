INSERT INTO SQLLAB.USER_ROLE (USER_ROLE_NAME )
VALUES('manager');
INSERT INTO SQLLAB.USER_ROLE (USER_ROLE_NAME )
VALUES('customer');
INSERT INTO SQLLAB.USER_ROLE (USER_ROLE_NAME )
VALUES('worker');
INSERT INTO SQLLAB.USER_ROLE (USER_ROLE_NAME )
VALUES('storekeeper');

INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('manager','1',1);
INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('customer1','customer',2);
INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('customer2','customer',2);
INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('customer3','customer',2);
INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('customer4','customer',2);
INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('customer5','customer',2);
INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('worker','worker',3);
INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('storekeeper','storekeeper',4);
INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('worker1','worker',3);
INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('worker2','worker',3);
INSERT INTO SQLLAB.USER (USER_LOGIN ,USER_PASSWORD ,USER_ROLE_ID )
Values('worker3','worker',3);

INSERT INTO SQLLAB.CUSTOMER (FIRST_NAME ,LAST_NAME ,PHONE ,USER_ID  )
Values('IVAN','IVANOV','1-23-3455-5435',2);
INSERT INTO SQLLAB.CUSTOMER (FIRST_NAME ,LAST_NAME ,PHONE ,USER_ID  )
Values('Petr','Petrov','23-3455-5435',3);
INSERT INTO SQLLAB.CUSTOMER (FIRST_NAME ,LAST_NAME ,PHONE ,USER_ID  )
Values('Fedor','Fedorov','1-23-3454735863',4);
INSERT INTO SQLLAB.CUSTOMER (FIRST_NAME ,LAST_NAME ,PHONE ,USER_ID  )
Values('Alex','Alkov','827382755-5435',5);
INSERT INTO SQLLAB.CUSTOMER (FIRST_NAME ,LAST_NAME ,PHONE ,USER_ID  )
Values('Alk','Alexov','82755-54827335',6);

INSERT INTO SQLLAB.WORKER_STATUS (WORKER_STATUS_NAME )
Values('busy');
INSERT INTO SQLLAB.WORKER_STATUS (WORKER_STATUS_NAME )
Values('free');
INSERT INTO SQLLAB.WORKER_STATUS (WORKER_STATUS_NAME )
Values('ill');
INSERT INTO SQLLAB.WORKER_STATUS (WORKER_STATUS_NAME )
Values('vacation');

INSERT INTO SQLLAB.WORKER_SPECIALIZATION (SPECIALIZATION_NAME)
Values('mechanik low');
INSERT INTO SQLLAB.WORKER_SPECIALIZATION (SPECIALIZATION_NAME)
Values('mechanik high');
INSERT INTO SQLLAB.WORKER_SPECIALIZATION (SPECIALIZATION_NAME)
Values('electric');
INSERT INTO SQLLAB.WORKER_SPECIALIZATION (SPECIALIZATION_NAME)
Values('diagnostician');
INSERT INTO SQLLAB.WORKER_SPECIALIZATION (SPECIALIZATION_NAME)
Values('engine');
INSERT INTO SQLLAB.WORKER_SPECIALIZATION (SPECIALIZATION_NAME)
Values('manager');
INSERT INTO SQLLAB.WORKER_SPECIALIZATION (SPECIALIZATION_NAME)
Values('storekeeper');

INSERT INTO SQLLAB.WORKER (SPECIALIZATION_ID ,FIRST_NAME ,LAST_NAME ,WORKER_STATUS_ID ,USER_ID )
Values(1,'Ivan','Ivanov',1,1);
INSERT INTO SQLLAB.WORKER (SPECIALIZATION_ID ,FIRST_NAME ,LAST_NAME ,WORKER_STATUS_ID ,USER_ID )
Values(1,'Petr','Ivanov',2,6);
INSERT INTO SQLLAB.WORKER (SPECIALIZATION_ID ,FIRST_NAME ,LAST_NAME ,WORKER_STATUS_ID ,USER_ID )
Values(2,'Fedya','Ivanov',3,7);
INSERT INTO SQLLAB.WORKER (SPECIALIZATION_ID ,FIRST_NAME ,LAST_NAME ,WORKER_STATUS_ID ,USER_ID )
Values(3,'Anton','Ivanov',2,8);
INSERT INTO SQLLAB.WORKER (SPECIALIZATION_ID ,FIRST_NAME ,LAST_NAME ,WORKER_STATUS_ID ,USER_ID )
Values(4,'Aleksey','Ivanov',4,9);
INSERT INTO SQLLAB.WORKER (SPECIALIZATION_ID ,FIRST_NAME ,LAST_NAME ,WORKER_STATUS_ID ,USER_ID )
Values(5,'Evgeniy','Ivanov',1,10);
INSERT INTO SQLLAB.WORKER (SPECIALIZATION_ID ,FIRST_NAME ,LAST_NAME ,WORKER_STATUS_ID ,USER_ID )
Values(4,'Vasia','Vasiliev',2,11);

INSERT INTO SQLLAB.PART (PART_NAME ,VENDOR ,AMOUNT )
Values('tyaga ВАЗ','81-56fa354fa',6);
INSERT INTO SQLLAB.PART (PART_NAME ,VENDOR ,AMOUNT )
Values('brake pads OPEL','5634-fafa815',12);
INSERT INTO SQLLAB.PART (PART_NAME ,VENDOR ,AMOUNT )
Values('brake pads BMW','54-fafa81563',12);
INSERT INTO SQLLAB.PART (PART_NAME ,VENDOR ,AMOUNT )
Values('tyaga BMW','8156fa354-fa',6);
INSERT INTO SQLLAB.PART (PART_NAME ,VENDOR ,AMOUNT )
Values('shrovaya BMW','8156354-fafa',5);
INSERT INTO SQLLAB.PART (PART_NAME ,VENDOR ,AMOUNT )
Values('brake pads AUDI','54-fafa81563',12);
INSERT INTO SQLLAB.PART (PART_NAME ,VENDOR ,AMOUNT )
Values('tyaga AUDI','8156fa354-fa',6);
INSERT INTO SQLLAB.PART (PART_NAME ,VENDOR ,AMOUNT )
Values('lamp H4 PHILIPS','6354-f815afa',10);
INSERT INTO SQLLAB.PART (PART_NAME ,VENDOR ,AMOUNT )
Values('lamp H4 OSRAM','81a56354-faf',10);
INSERT INTO SQLLAB.PART (PART_NAME ,VENDOR ,AMOUNT )
Values('shrovaya AUDI','8156354-fafa',5);

INSERT INTO SQLLAB.CAR (CAR_MODEL ,VIN_NUMBER ,CAR_DESCRIPTION ,REG_NUMBER ,CUSTOMER_ID)
Values('AUDI','u45890qwe67123rty','forever first auto for tests','AX1234AA',1);
INSERT INTO SQLLAB.CAR (CAR_MODEL ,VIN_NUMBER ,CAR_DESCRIPTION ,REG_NUMBER ,CUSTOMER_ID)
Values('BMW','u4583rty90qwe6712','TAZ','AX1256AC',2);
INSERT INTO SQLLAB.CAR (CAR_MODEL ,VIN_NUMBER ,CAR_DESCRIPTION ,REG_NUMBER ,CUSTOMER_ID)
Values('BMW','e67u45890qw123rty','TAZ','AX4356EE',2);
INSERT INTO SQLLAB.CAR (CAR_MODEL ,VIN_NUMBER ,CAR_DESCRIPTION ,REG_NUMBER ,CUSTOMER_ID)
Values('OPEL','u45812390qwe67rty','car_description','AX7634EA',3);
INSERT INTO SQLLAB.CAR (CAR_MODEL ,VIN_NUMBER ,CAR_DESCRIPTION ,REG_NUMBER ,CUSTOMER_ID)
Values('ВАЗ-2102','u453rt890qwe6712y','car_description','AX0923BC',2);
INSERT INTO SQLLAB.CAR (CAR_MODEL ,VIN_NUMBER ,CAR_DESCRIPTION ,REG_NUMBER ,CUSTOMER_ID)
Values('DODGE CHARGER','u4581290qwe673rty','car_description','AX8623BB',4);

INSERT INTO SQLLAB.ORDER_STATUS (ORDER_STATUS_NAME)
Values('waiting');
INSERT INTO SQLLAB.ORDER_STATUS (ORDER_STATUS_NAME)
Values('in work');
INSERT INTO SQLLAB.ORDER_STATUS (ORDER_STATUS_NAME)
Values('ready');

INSERT INTO SQLLAB.ORDER_IN_WORK (ORDER_DESCRIPTION ,DATETIME_START ,ORDER_STATUS_ID ,CAR_ID )
Values('change lamp',CURRENT_TIMESTAMP(),1,1);
INSERT INTO SQLLAB.ORDER_IN_WORK (ORDER_DESCRIPTION ,DATETIME_START ,ORDER_STATUS_ID ,CAR_ID )
Values('change brake pads',CURRENT_TIMESTAMP(),2,2);
INSERT INTO SQLLAB.ORDER_IN_WORK (ORDER_DESCRIPTION ,DATETIME_START ,DATETIME_FINISH ,ORDER_STATUS_ID ,CAR_ID )
Values('change brake pads',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),3,5);
INSERT INTO SQLLAB.ORDER_IN_WORK (ORDER_DESCRIPTION ,DATETIME_START ,DATETIME_FINISH ,ORDER_STATUS_ID ,CAR_ID )
Values('change tyaga',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),3,4);


INSERT INTO SQLLAB.ORDER_WORKER (ORDER_ID ,WORKER_ID, ISCOMPLETED)
Values(1,1,FALSE);
INSERT INTO SQLLAB.ORDER_WORKER (ORDER_ID ,WORKER_ID, ISCOMPLETED)
Values(2,6,FALSE);

INSERT INTO SQLLAB.PART_ORDER (ORDER_ID ,PART_ID ,AMOUNT)
Values(1,3,2);
INSERT INTO SQLLAB.PART_ORDER (ORDER_ID ,PART_ID ,AMOUNT)
Values(2,8,4);
INSERT INTO SQLLAB.PART_ORDER (ORDER_ID ,PART_ID ,AMOUNT)
Values(2,3,2);
INSERT INTO SQLLAB.PART_ORDER (ORDER_ID ,PART_ID ,AMOUNT)
Values(4,10,1);
