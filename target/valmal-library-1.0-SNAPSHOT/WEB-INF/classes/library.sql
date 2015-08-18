# drop database if exists library;
# create database if not exists library;

USE library;

# DROP TABLE IF EXISTS library.address;
# CREATE TABLE if not exists library.address (
#   reader_id INT NOT NULL AUTO_INCREMENT,
#   country VARCHAR(50) NULL,
#   city VARCHAR(50) NULL,
#   street VARCHAR(50) NULL,
#   house VARCHAR(50) NULL,
#   PRIMARY KEY (reader_id)
# );
#
# DROP TABLE IF EXISTS library.readers;
# CREATE TABLE if not exists library.readers(
#   id INT NOT NULL AUTO_INCREMENT,
#   fName VARCHAR(50) NULL ,
#   mName VARCHAR(50) NULL ,
#   lName VARCHAR(50) NULL ,
#   date_of_birth datetime null,
#   phone VARCHAR(50) NULL ,
#   address_id INT,
#   registration_date datetime null,
#   PRIMARY KEY (id),
#   FOREIGN KEY (address_id) REFERENCES library.address(reader_id),
#   unique (address_id)
# );
#
# drop table if exists library.books;
# create table if not exists library.books(
# 	id int not null auto_increment,
#     title varchar(100) not null,
#     subject varchar(100) null,
#     authors varchar(100) null,
#     year int null,
#     primary key (id)
# );
#
# drop table if exists library.subjects;
# create table if not exists library.subjects(
# 	id int not null auto_increment,
#     subject varchar(50) not null,
#     primary key (id)
# );

insert into readers(id, fName, mName, lName, dateOfBirth, phone, country, city, Street, house, registrationDate) values
(1, 'fName1', 'mName1', 'lName1', cast('1990-01-01' as date), 'phone1', 'country', 'city', 'street', 'house', cast('2015-01-01' as date)),
(2, 'fName2', 'mName2', 'lName2', cast('1990-01-02' as date), 'phone2', 'country', 'city', 'street', 'house', cast('2015-01-02' as date)),
(3, 'fName3', 'mName3', 'lName3', cast('1990-01-03' as date), 'phone3', 'country', 'city', 'street', 'house', cast('2015-01-03' as date)),
(4, 'fName4', 'mName4', 'lName4', cast('1990-01-04' as date), 'phone4', 'country', 'city', 'street', 'house', cast('2015-01-04' as date)),
(5, 'fName5', 'mName5', 'lName5', cast('1990-01-05' as date), 'phone5', 'country', 'city', 'street', 'house', cast('2015-01-05' as date)),
(6, 'fName6', 'mName6', 'lName6', cast('1990-01-06' as date), 'phone6', 'country', 'city', 'street', 'house', cast('2015-01-06' as date)),
(7, 'fName7', 'mName7', 'lName7', cast('1990-01-07' as date), 'phone7', 'country', 'city', 'street', 'house', cast('2015-01-07' as date)),
(8, 'fName8', 'mName8', 'lName8', cast('1990-01-08' as date), 'phone8', 'country', 'city', 'street', 'house', cast('2015-01-08' as date)),
(9, 'fName9', 'mName9', 'lName9', cast('1990-01-09' as date), 'phone9', 'country', 'city', 'street', 'house', cast('2015-01-09' as date)),
(10, 'fName10', 'mName10', 'lName10', cast('1990-01-10' as date), 'phone10', 'country', 'city', 'street', 'house', cast('2015-01-10' as date)),
(11, 'fName11', 'mName11', 'lName11', cast('1990-01-11' as date), 'phone11', 'country', 'city', 'street', 'house', cast('2015-01-11' as date)),
(12, 'fName12', 'mName12', 'lName12', cast('1990-01-12' as date), 'phone12', 'country', 'city', 'street', 'house', cast('2015-01-12' as date));

insert into books(id, title, authors, year, genre, amount, isScarce) values
(1, 'In Search of Lost Time', 'Marcel Proust', '2015', 'genre', 1, false),
(2, 'Ulysses', 'James Joyce', '2015', 'genre', 2, true),
(3, 'Don Quixote', 'Miguel de Cervantes', '2015', 'genre', 3, false),
(4, 'Moby Dick', 'Herman Melville', '2015', 'genre', 4, true),
(5, ' Hamlet', 'William Shakespeare', '2015', 'genre', 5, false),
(6, 'War and Peace', 'Leo Tolstoy', '2015', 'genre', 6, false),
(7, ' The Odyssey', 'Homer', '2015', 'genre', 7, true),
(8, ' The Great Gatsby', 'F. Scott Fitzgerald', '2015', 'genre', 8, false),
(9, ' The Divine Comedy', 'Dante Alighieri', '2015', 'genre', 9, false),
(10, ' Madame Bovary', 'Gustave Flaubert', '2015', 'genre', 10, true),
(11, ' The Brothers Karamazov', 'Fyodor Dostoyevsky', '2015', 'genre', 11, false),
(12, ' One Hundred Years of Solitude', 'Gabriel Garcia Marquez', '2015', 'genre', 12, false);

# insert into records(id, date, returnDate, checked, book_id, reader_id) VALUES
# (1, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 1, 1),
# (2, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 2, 2),
# (3, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 3, 3),
# (4, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 4, 4),
# (5, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 5, 5),
# (6, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 6, 6),
# (7, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 7, 7),
# (8, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 8, 8),
# (9, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 9, 9),
# (10, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 10, 10),
# (11, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 11, 11),
# (12, CAST ('2015-08-01' as DATE ), CAST ('2015-08-20' as DATE ), false, 12, 12);


















