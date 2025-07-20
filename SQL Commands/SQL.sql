create database Airline

use Airline

create table Airport(
AirportCode varchar(3) primary key not null,
AirportName varchar(40) not null,
AirportState varchar(25) not null,
City varchar(25) not null
);
create table AirplaneType(
Typename varchar(45) primary key not null,
Manfacturing_Company varchar(45) not null,
Max_Seats int not null
);
create table Airplane(
ID int primary key not null,
TotalNoSeats int not null,
Typename varchar(45),
CONSTRAINT fk_airplaneetype FOREIGN KEY (Typename)
REFERENCES AirplaneType(Typename)
);
create table CanLand(
AirportCode varchar(3),
Typename varchar(45),
CONSTRAINT fk_canland FOREIGN KEY (Typename)
REFERENCES AirplaneType(Typename),
CONSTRAINT fk_canland2 FOREIGN KEY (AirportCode)
REFERENCES Airport(AirportCode)
);
create table Flight(
Flightnumber varchar(10) primary key not null,
Airline varchar(25) not null,
Weekdays varchar(25) not null
);
create table Seat(
Seatno int primary key not null,
Customer_name varchar(35) not null,
Customer_phone varchar(25) not null,
SeatDate varchar(50) not null,
CONSTRAINT fk_seat FOREIGN KEY (SeatDate)
REFERENCES LegInstance(Date1)
);
create table LegInstance(
Date1 varchar(50) primary key not null,
NoOfAvailableSeats int not null,
Dep_Time varchar(8) not null,
Arr_Time varchar(8) not null,
ID int not null,
LegNo int not null,
AirportCode varchar(3) not null,
CONSTRAINT fk_leginstance FOREIGN KEY (ID)
REFERENCES Airplane(ID),
CONSTRAINT fk_leginstance2 FOREIGN KEY (LegNo)
REFERENCES FlightLeg(LegNo),
CONSTRAINT fk_leginstance3 FOREIGN KEY (AirportCode)
REFERENCES Airport(AirportCode)
);
create table FlightLeg(
LegNo int primary key not null,
Scheduled_Dep_Time time not null,
Scheduled_Arr_Time time not null,
Flightnumber varchar(10) not null,
AirportCode varchar(3),
CONSTRAINT fk_flightleg1 FOREIGN KEY (Flightnumber)
REFERENCES Flight(Flightnumber),
CONSTRAINT fk_flightleg2 FOREIGN KEY (AirportCode)
REFERENCES Airport(AirportCode)
);
insert into FlightLeg values(2,'04:00AM','08:00AM','FSF 456','FRA')
insert into Airport values('CAI','Cairo International Airport','Cairo','Heliopolis')
select * from Airport
select * from AirplaneType
select * from Airplane
select * from Seat
select * from CanLand
select * from FlightLeg
select * from Flight
select * from LegInstance
ALTER TABLE FlightLeg
DROP COLUMN Scheduled_Dep_Time;
ALTER TABLE FlightLeg
ADD Scheduled_Arr_Time varchar(8);
ALTER TABLE FlightLeg
DROP COLUMN Flightnumber;
ALTER TABLE FlightLeg
DROP CONSTRAINT FK__FlightLeg__Fligh__2B3F6F97;
ALTER TABLE FlightLeg
DROP COLUMN Flightnumber;
ALTER TABLE FlightLeg
ADD FOREIGN KEY (AirportCode) REFERENCES Airport(AirportCode);
ALTER TABLE FlightLeg
ADD AirportCode varchar(3) not null;
SELECT Airport.AirportCode, Airport.AirportName
FROM Airport
INNER JOIN CanLand ON Airport.AirportCode=CanLand.AirportCode;