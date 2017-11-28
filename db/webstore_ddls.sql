CREATE TABLE `products` (
  `productid` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `imagefile` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(30) NOT NULL,
    `password` CHAR(128) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

