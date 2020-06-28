CREATE TABLE ip_data (
    ip varchar(20) NOT NULL,
    date date NOT NULL ,
    country varchar(40) NOT NULL,
    iso_code varchar(2) NOT NULL,
    languages varchar(40) NOT NULL,
    currency  varchar(40) NOT NULL,
    times varchar(40) NOT NULL,
    estimated_distance varchar(40) NOT NULL,
    PRIMARY KEY (ip)
);