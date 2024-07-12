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


DROP TABLE IF EXISTS `account`;

CREATE TABLE `account`(
	id			INT UNSIGNED PRIMARY KEY AUTO_INCREMENT ,
    email				VARCHAR(50) UNIQUE KEY NOT NULL,
    username			VARCHAR(50) UNIQUE KEY NOT NULL,
    lastname			VARCHAR(50) NOT NULL,
    firstname			VARCHAR(50) NOT NULL,
    avatar				VARCHAR(100) ,
	phone				VARCHAR(20) NOT NULL,
    address				TEXT,
    `password`			TEXT NOT NULL,
    `role`				ENUM('ADMIN', 'CUSTOMER'),
    `active`			BOOLEAN,
    `otp`				VARCHAR(50) UNIQUE KEY,
    CreateDate			DATETIME DEFAULT(now()), -- '2023-02-18'
    UUIDKey				VARCHAR(50) UNIQUE KEY,
    FOREIGN KEY(avatar) REFERENCES `fileTable`(uuid) ON DELETE CASCADE
);
-- gfg123

INSERT INTO `account` (id, email, username, lastname, firstname, phone, address, `password`, `role` ,UUIDKey, `active`)
VALUES (1, 'nguyenxuandung707@gmail.com', 'dung8anx', 'Dũng', 'Nguyễn Xuân', '0358123444', 'Hà Nội', '$2a$10$F5rzmMmNJcgwqTXmcro1eOeATecEUDsPM8WjKtF8Qx46RFDjlmCSW', 'CUSTOMER', '112233', 1),
	   (2, 'admin@gmail.com', 'admin', 'Admin', 'Nguyễn Xuân', '0358123555', 'Hà Nội', '$2a$10$F5rzmMmNJcgwqTXmcro1eOeATecEUDsPM8WjKtF8Qx46RFDjlmCSW', 'ADMIN', '221133' , 1);


CREATE TABLE `products`(
	id int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR(100) NOT NULL,
    imgUrl TEXT NOT NULL,
    price FLOAT UNSIGNED NOT NULL,
    priceSale FLOAT UNSIGNED DEFAULT(0),
    shortDesc TEXT NOT NULL,
    `description` TEXT NOT NULL,
    avgRating FLOAT,
    
    catagoryId INT UNSIGNED,
    uuidUrl VARCHAR(50),
    originId INT UNSIGNED,
    informationId INT UNSIGNED,
    product_code VARCHAR(50) UNIQUE KEY NOT NULL,
    FOREIGN KEY(catagoryId) REFERENCES `category`(categoryid) ON DELETE CASCADE,
    FOREIGN KEY(uuidUrl) REFERENCES `fileTable`(uuid) ON DELETE CASCADE,
    FOREIGN KEY(originId) REFERENCES `origin`(id) ON DELETE CASCADE,
    FOREIGN KEY(informationId) REFERENCES `infomation`(id)ON DELETE CASCADE
);



CREATE TABLE `reviews`(
	reviewid int UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    reviewText TEXT,
    rating FLOAT UNSIGNED,
    productId int UNSIGNED,
	FOREIGN KEY(productId) REFERENCES `products`(id) ON DELETE CASCADE
);


CREATE TABLE `customers`(
	customersId INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(50),
    email    VARCHAR(50),
    phone		VARCHAR(50),
    address TEXT,
    city VARCHAR(50),
    postalcode INT UNSIGNED,
    country VARCHAR(50),
    
    customer_code VARCHAR(50) UNIQUE KEY NULL
    
);

CREATE TABLE `orders`(
	orderId 			INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	customersId 	INT UNSIGNED, 
    `date` 		DATETIME  DEFAULT(now()),
	totalPrice 	FLOAT UNSIGNED,
    payment 	VARCHAR(80),
    `status`	ENUM ('PENDING', 'SHIPPING', 'COMPLETED'),	
	FOREIGN KEY (customersId) REFERENCES `customers`(customersId) ON DELETE CASCADE
);



CREATE TABLE `orderDetails`(
		orderDetailsId 				INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        orderId 		INT UNSIGNED,
        productId 		INT UNSIGNED,
        productQuantity INT UNSIGNED,
        
        price			FLOAT UNSIGNED,
        price_sales			FLOAT UNSIGNED,
        FOREIGN KEY(orderId) REFERENCES `orders`(orderId) ON DELETE CASCADE,
		FOREIGN KEY(productId) REFERENCES `products`(id) ON DELETE CASCADE
);

CREATE TABLE `quantity`(
		quantityId  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        totalQuantity INT UNSIGNED,
        totalSale INT UNSIGNED,
        productId INT UNSIGNED,
		FOREIGN KEY(productId) REFERENCES `products`(id) ON DELETE CASCADE
);

CREATE TABLE `payments`(
		paymentId INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
        orders_id INT UNSIGNED,
        payment_date DATE DEFAULT(now()),
        payment_amount VARCHAR(50),
        payment_method  VARCHAR(50),
        
        FOREIGN KEY(orders_id) REFERENCES `orders`(orderId) ON DELETE CASCADE
        
);

DROP TABLE IF EXISTS SERVICES;

CREATE TABLE SERVICES(
	iconId INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    icon VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    subTitle TEXT NOT NULL,
    background VARCHAR(50)
);


DROP TABLE IF EXISTS FOOTERDATA;
CREATE TABLE FOOTERDATA(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    `description` TEXT NOT NULL,
    address TEXT NOT NULL,
    phone VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL
);

DROP TABLE IF EXISTS HerroSection;
CREATE TABLE HerroSection(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    img TEXT NOT NULL,
    title TEXT NOT NULL,
    subTitle VARCHAR(50) NOT NULL,
    backgroundColor VARCHAR(150) NOT NULL,
    captionHeader VARCHAR(150) NOT NULL
);
