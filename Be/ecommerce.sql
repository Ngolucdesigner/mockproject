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
    city				VARCHAR(50), 
    `password`			VARCHAR(100) NOT NULL,
    `role`				ENUM('ADMIN', 'EMPLOYEE', 'MANAGER','CUSTOMERS'),
    CreateDate			DATE DEFAULT(now()), -- '2023-02-18'
    UUIDKey				VARCHAR(50) UNIQUE KEY NOT NULL,
    uuidUrl 			VARCHAR(50),
    FOREIGN KEY(uuidUrl) REFERENCES `fileTable`(uuid)
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




CREATE TABLE `oder`(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	accountId INT UNSIGNED, 
	productId INT UNSIGNED,
	`status`  ENUM("DONE","WAIT", "COMFIRM"),
	FOREIGN KEY(productId) REFERENCES `products`(productid),
	FOREIGN KEY(accountId) REFERENCES `Account`(AccountID)
);




INSERT INTO `Account`(UUIDKey, 					Email 					, Username		, `password`														, 	firtname			,	lastname ,		avatar, 		phone, 		address,		`role`		, 	CreateDate)
VALUE 				(UUID_TO_BIN(UUID()),			'account1@gmail.com'	,'vanchien1'	, '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	, 	'Nguyen Van'		,	'A'		 ,		NULL  ,				"094422",		"ha noi",		'ADMIN'		,  '2022-03-07'),
					(UUID_TO_BIN(UUID()),			'account2@gmail.com'	,'vanchien2'	, '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	, 	'Nguyen Van'		,	'B'		 ,		null  ,				"094422",		"Nghe An",		'ADMIN'		,  '2022-03-07'),
                    (UUID_TO_BIN(UUID()),			'account3@gmail.com'	,'vanchien3'	, '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	, 	'Nguyen Van'		,	'C'		 ,		NULL  ,				"094422",		"ha noi",		'ADMIN'		,  '2022-03-07'),
                    (UUID_TO_BIN(UUID()),			'account4@gmail.com'	,'vanchien4'	, '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	, 	'Nguyen Van'		,	'D'		 ,		null  ,				"094422",		"Nghe An",		'ADMIN' 	,  '2022-03-07'),
                    (UUID_TO_BIN(UUID()),			'account5@gmail.com'	,'anks'			, '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	, 	'Anks'				,	'Anks'	 ,		null  ,				"094422",		"ha noi",		'MANAGER'	,  '2022-03-07');


INSERT INTO `category`(catagory)
VALUE ("chair"),
		("MOBILE");

INSERT INTO `products`(productName, imgUrl,price,shortDesc,`description`,avgRating,catagoryId,uuidUrl)
VALUE
	("product1", "abcdesf", 12.3, "loremdfas dsafkh hgdsajfh hsidafj syfiusad", "dsafuin sdaihfjb hisadfhm dksufhkmn hfsdjkahf asdfdkfhksdjaf jsadf", 4.8, 1, "7585c750-e86c-470f-8dc7-216c74a60487");
    
INSERT INTO `reviews`(userName, reviewText,rating,productId)
VALUE
	("ANKS", "ASDJASKLD ASDM DSAFSK DFSAFAS ASDFASD SADFDAS SDFADAS SDAFAS", 4, 1),
    ("ANdsdfsKS", "ASDJASKLD ASDM DSAFSK DFSAFAS ASDFASD SADFDAS SDFADAS SDAFAS", 5, 1);
    

    