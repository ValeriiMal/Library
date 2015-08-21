drop database if exists library;
create database if not exists library;

USE library;

DROP TABLE IF EXISTS library.address;
CREATE TABLE if not exists library.address (
  reader_id INT NOT NULL AUTO_INCREMENT,
  country VARCHAR(50) NULL,
  city VARCHAR(50) NULL,
  street VARCHAR(50) NULL,
  house VARCHAR(50) NULL,
  PRIMARY KEY (reader_id)
);

DROP TABLE IF EXISTS library.readers;
CREATE TABLE if not exists library.readers(
  id INT NOT NULL AUTO_INCREMENT,
  fName VARCHAR(50) NULL ,
  mName VARCHAR(50) NULL ,
  lName VARCHAR(50) NULL ,
  date_of_birth datetime null,
  phone VARCHAR(50) NULL ,
  address_id INT,
  registration_date datetime null,
  PRIMARY KEY (id),
  FOREIGN KEY (address_id) REFERENCES library.address(reader_id),
  unique (address_id)
);

drop table if exists library.books;
create table if not exists library.books(
	id int not null auto_increment,
    title varchar(100) not null,
    subject varchar(100) null,
    authors varchar(100) null,
    year int null,
    primary key (id)
);

drop table if exists library.subjects;
create table if not exists library.subjects(
	id int not null auto_increment,
    subject varchar(50) not null,
    primary key (id)
);

insert into library.address values
(1, 'country1', 'City1', 'street1', 'house1'),
(2, 'country2', 'City2', 'street2', 'house2'),
(3, 'country3', 'City3', 'street3', 'house3'),
(4, 'country4', 'City4', 'street4', 'house4'),
(5, 'country5', 'City5', 'street5', 'house5'),
(6, 'country6', 'City6', 'street6', 'house6'),
(7, 'country7', 'City7', 'street7', 'house7'),
(8, 'country8', 'City8', 'street8', 'house8'),
(9, 'country9', 'City9', 'street9', 'house9'),
(10, 'country10', 'City10', 'street10', 'house10');

insert into library.readers values
(1, 'fName1', 'mName1', 'lName1', cast('1990-01-01' as date), 'phone1', 1, cast('2015-01-01' as date)),
(2, 'fName2', 'mName2', 'lName2', cast('1990-01-02' as date), 'phone2', 2, cast('2015-01-02' as date)),
(3, 'fName3', 'mName3', 'lName3', cast('1990-01-03' as date), 'phone3', 3, cast('2015-01-03' as date)),
(4, 'fName4', 'mName4', 'lName4', cast('1990-01-04' as date), 'phone4', 4, cast('2015-01-04' as date)),
(5, 'fName5', 'mName5', 'lName5', cast('1990-01-05' as date), 'phone5', 5, cast('2015-01-05' as date)),
(6, 'fName6', 'mName6', 'lName6', cast('1990-01-06' as date), 'phone6', 6, cast('2015-01-06' as date)),
(7, 'fName7', 'mName7', 'lName7', cast('1990-01-07' as date), 'phone7', 7, cast('2015-01-07' as date)),
(8, 'fName8', 'mName8', 'lName8', cast('1990-01-08' as date), 'phone8', 8, cast('2015-01-08' as date)),
(9, 'fName9', 'mName9', 'lName9', cast('1990-01-09' as date), 'phone9', 9, cast('2015-01-09' as date)),
(10, 'fName10', 'mName10', 'lName10', cast('1990-01-10' as date), 'phone10', 10, cast('2015-01-10' as date));

insert into library.books values
(1, 'title1', 'subject1', 'authors1', 1),
(2, 'title2', 'subject2', 'authors2', 2),
(3, 'title3', 'subject3', 'authors3', 3),
(4, 'title4', 'subject4', 'authors4', 4),
(5, 'title5', 'subject5', 'authors5', 5),
(6, 'title6', 'subject6', 'authors6', 6),
(7, 'title7', 'subject7', 'authors7', 7),
(8, 'title8', 'subject8', 'authors8', 8),
(9, 'title9', 'subject9', 'authors9', 9),
(10, 'title10', 'subject10', 'authors10', 10);

insert into library.subjects values
(1, 'subject1'),
(2, 'subject2'),
(3, 'subject3'),
(4, 'subject4'),
(5, 'subject5'),
(6, 'subject6'),
(7, 'subject7'),
(8, 'subject8'),
(9, 'subject9'),
(10, 'subject10');

use library; 
select * from address;
select * from readers;
select * from books;
select * from subjects;
select * from readers left join address on readers.address_id = address.reader_id;

use library;

DELIMITER //

CREATE TRIGGER contacts_before_delete
BEFORE DELETE
   ON books FOR EACH ROW
   
BEGIN

   DECLARE vUser varchar(50);

   -- Find username of person performing the DELETE into table
   SELECT USER() INTO vUser;
   
   -- Insert record into audit table
   
   
END; //

DELIMITER ;

use library;
show tables;























