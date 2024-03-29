CREATE SCHEMA booked_flights;
CREATE TABLE airplane(
airplanes VARCHAR(33) DEFAULT NULL,
goTime TIME DEFAULT NULL,
helloTime TIME DEFAULT NULL,
destination VARCHAR(33) DEFAULT NULL
);
CREATE TABLE confirmedseats(
seats VARCHAR(2) DEFAULT NULL,
bkmealplan VARCHAR(33) DEFAULT NULL,
lunchmealplan VARCHAR(33) DEFAULT NULL,
dinnermealplan VARCHAR(33) DEFAULT NULL,
confirmationNum CHAR(8) DEFAULT NULL
);
CREATE TABLE destination(
poi VARCHAR(33) DEFAULT NULL,
nonstopfromAtoB VARCHAR(33) DEFAULT NULL
);
CREATE TABLE reserved(
passenger_name VARCHAR(33) DEFAULT NULL,
destination VARCHAR(33) DEFAULT NULL,
departureTime TIME DEFAULT NULL,
arrivalTime TIME DEFAULT NULL,
confirmation CHAR(8) DEFAULT NULL,
luggage tinyint(4) DEFAULT NULL
);
