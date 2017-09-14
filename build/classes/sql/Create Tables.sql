create table Manager(
ma_id integer primary key,
manager_fname varchar2 (30),
manager_lname varchar2 (30),
addres  varchar2 (200),
email  varchar2 (40),
ContactNumber varchar2 (40)
);

Create table Service(
service_id integer primary key,
typeOf_service varchar2(20),
cost varchar2(20)
);
-----------------------
Create table Equipment(
equip_id integer primary key,
name_eq varchar2(100), 
State_condition varchar2 (20),
cost varchar2(20)
);

Create Table Cal_Date(
id_Date integer primary key,
note varchar2(200),
login varchar2 (20),
Booked date
);

--Table create for the employee
Create table Employe(
id_emp integer primary key,
em_firName varchar2 (30),
em_lastName varchar2 (30),
em_Address varchar2 (100),
em_contactNumber varchar2 (40),
em_timePart_Time varchar2 (10),
em_wages    varchar2 (50),
em_premisions varchar2 (10),
email  varchar2 (40),
account_Created_on date
);

Create table Technician(
id_technician integer primary key,
technicianJob varchar2 (90),
id_employe integer,
cost varchar2(20),
FOREIGN KEY (id_employe) REFERENCES Employe(id_emp)
);



create table Band(
Band_id INTEGER primary key,
band_Name varchar2 (50),
member_number Integer,
music_Geners varchar2(20),
account_Created_on Date,
manager_id integer,
FOREIGN KEY (manager_id) REFERENCES Manager (ma_id)
);


Create table s_User(
id_user integer primary key,
login varchar2 (30),
password varchar2 (30),
id_employe integer,
FOREIGN KEY (id_employe) REFERENCES Employe (id_emp)
);



Create table receipt(
id_receipt integer primary key,
totalPay varchar2 (90),
Payed varchar2(2),
band_id integer,
manager_id integer,
id_Date integer,
id_user integer,
FOREIGN KEY (band_id) REFERENCES Band (Band_id),
FOREIGN KEY (manager_id) REFERENCES Manager (ma_id),
FOREIGN KEY (id_Date) REFERENCES Cal_Date (id_Date),
FOREIGN KEY (id_user) REFERENCES s_User (id_user)
);

--Many to many Maping tables 
--Maping services to recepies
Create table rec_Serv(
id_receipt integer,
service_id integer,
PRIMARY KEY (id_receipt,service_id),
FOREIGN KEY (id_receipt) REFERENCES receipt (id_receipt),
FOREIGN KEY (service_id) REFERENCES Service (service_id));

--Maping technicians to Recepies
create Table rec_Tech(
id_receipt integer,
id_technician integer,
PRIMARY KEY (id_receipt,id_technician),
FOREIGN KEY (id_receipt) REFERENCES receipt (id_receipt),
FOREIGN KEY (id_technician) REFERENCES Technician (id_technician));

--Maping Equipment To the Recepies
create Table rec_Equip(
id_receipt integer,
equip_id integer,
PRIMARY KEY (id_receipt,equip_id),
FOREIGN KEY (id_receipt) REFERENCES receipt (id_receipt),
FOREIGN KEY (equip_id) REFERENCES Equipment (equip_id));


