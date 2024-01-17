DROP DATABASE IF EXISTS ecommerce;
CREATE DATABASE ecommerce;
USE ecommerce;


CREATE TABLE `category`(
	categoryid int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    catagory VARCHAR(50) not null
);

CREATE TABLE `fileTable`(
	uuid	 VARCHAR(50) PRIMARY KEY NOT NULL,
	imgdata  LONGBLOB,
	imgname	 VARCHAR(50) NOT NULL,
    typeImg  VARCHAR(50) NOT NULL
);

CREATE TABLE `infomation`(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    wattage VARCHAR(10),
    noise VARCHAR(10),
	technology VARCHAR(100),
    `level` VARCHAR(200),
    `mode` VARCHAR(300),
	accessory VARCHAR(200),
    size 		varchar(100),
    Weight 		VARCHAR(10),
    color		VARCHAR(50),
    funtion     text
);

CREATE TABLE `origin`(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    manufacturer VARCHAR(50),
    madeIn VARCHAR(50),
	guarantee VARCHAR(50)
  
);


DROP TABLE IF EXISTS `Account`;

CREATE TABLE `Account`(

	AccountID			INT UNSIGNED PRIMARY KEY AUTO_INCREMENT ,
    Email				VARCHAR(50) UNIQUE KEY NOT NULL,
    Username			VARCHAR(50) UNIQUE KEY NOT NULL,
    lastname			VARCHAR(50)  NOT NULL,
    firtname			VARCHAR(50) NOT NULL,
    avatar				VARCHAR(100) ,
	phone				VARCHAR(20) NOT NULL,
    address				VARCHAR(100) NOT NULL,
    `password`			VARCHAR(100) NOT NULL,
    `role`				ENUM('ADMIN', 'EMPLOYEE', 'MANAGER'),
    CreateDate			DATE DEFAULT(now()), -- '2023-02-18'
    UUIDKey				VARCHAR(50) UNIQUE KEY NOT NULL,
    FOREIGN KEY(avatar) REFERENCES `fileTable`(uuid)
);


CREATE TABLE `products`(
	productid int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR(50) NOT NULL,
    imgUrl TEXT NOT NULL,
    price FLOAT UNSIGNED NOT NULL,
    shortDesc TEXT NOT NULL,
    `description` TEXT NOT NULL,
    avgRating FLOAT ,
    
    catagoryId INT UNSIGNED,
    uuidUrl VARCHAR(50),
    originId INT UNSIGNED,
    informationId INT UNSIGNED,
    
    
    FOREIGN KEY(catagoryId) REFERENCES `category`(categoryid),
    FOREIGN KEY(uuidUrl) REFERENCES `fileTable`(uuid),
    FOREIGN KEY(originId) REFERENCES `origin`(id),
    FOREIGN KEY(informationId) REFERENCES `infomation`(id)
);



CREATE TABLE `reviews`(
	reviewid int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(50),
    reviewText TEXT,
    rating FLOAT UNSIGNED,
    productId int UNSIGNED,
	FOREIGN KEY(productId) REFERENCES `products`(productid)
);


CREATE TABLE `customers`(
	customersId INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(50),
    email    VARCHAR(50),
    phone		VARCHAR(50),
    address TEXT,
    city VARCHAR(50),
    postalcode INT UNSIGNED,
    country VARCHAR(50)
);

CREATE TABLE `orders`(
	orders_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	customers_id INT UNSIGNED, 
    order_date DATE DEFAULT(now()),
	total_amount INT UNSIGNED,
	FOREIGN KEY(customersId) REFERENCES `customers`(customersId)
);



CREATE TABLE `OrderDetails`(
		OrderDetails_id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        order_id INT UNSIGNED,
        product_id INT UNSIGNED,
        quantity INT UNSIGNED,
        unit_price  FLOAT UNSIGNED,
        FOREIGN KEY(order_id) REFERENCES `orders`(orders_id),
		FOREIGN KEY(product_id) REFERENCES `products`(productid)
);

CREATE TABLE `quantity`(
		quantityId  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        totalQuantity INT UNSIGNED,
        totalSale INT UNSIGNED,
        productId INT UNSIGNED,
        FOREIGN KEY(productId) REFERENCES `products`(productid)
);

CREATE TABLE `payments`(
		paymentId INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        orders_id INT UNSIGNED,
        payment_date DATE DEFAULT(now()),
        payment_amount VARCHAR(50),
        payment_method  VARCHAR(50),
        
        FOREIGN KEY(orders_id) REFERENCES `orders`(orders_id)
        
);



    