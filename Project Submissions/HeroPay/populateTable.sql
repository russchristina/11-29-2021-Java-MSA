insert into employee_role values(default, 'Knight'), (default, 'Cleric'), (default, 'Mage'), (default, 'Manager');



insert into employee_account values(default, 'Emerick' , 'Ademar', 1), (default, 'William', 'Johnson', 1), (default, 'Gwyndolyn', 'Braveheart', 1);

insert into employee_account values(default, 'Gustave' , 'Khihrer', 2), (default, 'Terese', 'Stoutcrag', 2), (default, 'Haines', 'Nakein', 2);

insert into employee_account values(default, 'Iphazor' , 'Thitarum', 3), (default, 'Amari', 'Rhidrisse', 3), (default, 'Enumari', 'Obaris', 3);

insert into employee_account values(default, 'John' , 'Smith', 4), (default, 'Linda', 'Gobert', 4);

insert into employee_account values(default, 'Greg', 'Marcus', 4) returning *;

update employee_account set first_name = 'Misha' where id = 12 returning *;



insert into login_info values
(default, 'knight1', 'pass1', 1), 
(default, 'knight2', 'pass2', 2),
(default, 'knight3', 'pass3', 3),
(default, 'cleric1', 'pass4', 4),
(default, 'cleric2', 'pass5', 5),
(default, 'cleric3', 'pass6', 6),
(default, 'mage1', 'pass7', 7),
(default, 'mage2', 'pass8', 8),
(default, 'mage3', 'pass9', 9),
(default, 'manager1', 'pass10', 10),
(default, 'manager2', 'pass11', 11);


insert into request_type values(default, 'Travel');
insert into request_type values(default, 'Equipment');
insert into request_type values(default, 'Consumable');
insert into request_type values(default, 'Book');