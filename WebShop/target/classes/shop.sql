USE user;

 CREATE TABLE users
 (
 	userEmail varchar(45),
     userName varchar(20),
     userPassword varchar(20),
   image varchar(45)
 );

 CREATE TABLE Products
 (
 	productName varchar(45),
	productCost int,
	productImg varchar(100),
	productInfo varchar(45),
    productType varchar(20),
    productCountry varchar(20)
 );

INSERT INTO Products VALUES  ("1", 19, "/img/beer.jpg", "Sider", "Sider", "Ukraine" );
INSERT INTO Products VALUES  ("2", 19, "/img/beer.jpg", "Dark", "Dark", "Germany" );
INSERT INTO Products VALUES  ("3", 19, "/img/beer.jpg", "Dark", "Dark", "England" );
INSERT INTO Products VALUES  ("4", 19, "/img/beer.jpg", "Sider", "Sider", "Germany" );
INSERT INTO Products VALUES  ("5", 19, "/img/beer.jpg", "Dark", "Dark", "England" );
INSERT INTO Products VALUES  ("6", 19, "/img/beer.jpg", "Sider", "Sider", "Ukraine" );
INSERT INTO Products VALUES  ("7", 19, "/img/beer.jpg", "Dark", "Dark", "England" );
INSERT INTO Products VALUES  ("8", 19, "/img/beer.jpg", "Dark", "Dark", "Ukraine" );
INSERT INTO Products VALUES  ("9", 19, "/img/beer.jpg", "Sider", "Sider", "Germany" );
INSERT INTO Products VALUES  ("9", 19, "/img/beer.jpg", "Sider", "Sider", "Ukraine" );