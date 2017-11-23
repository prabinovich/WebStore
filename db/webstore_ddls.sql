--CREATE DATABASE `webstore` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `products` (
  `productid` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `imagefile` varchar(100) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
