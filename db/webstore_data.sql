use webstore;

/*
-- Query: SELECT * FROM webstore.products
LIMIT 0, 1000

-- Date: 2017-11-21 10:20
*/
INSERT INTO `products` (`productid`,`name`,`imagefile`,`price`) VALUES (1,'Arabic t-shirt','ArabicTShirt.jpg',10);
INSERT INTO `products` (`productid`,`name`,`imagefile`,`price`) VALUES (2,'Chinese t-shirt','ChineseTShirt.jpg',17);
INSERT INTO `products` (`productid`,`name`,`imagefile`,`price`) VALUES (3,'Hebrew t-shirt','HebrewTShirt.jpg',19);
INSERT INTO `products` (`productid`,`name`,`imagefile`,`price`) VALUES (4,'Hoodie','Hoodie.jpg',50);
INSERT INTO `products` (`productid`,`name`,`imagefile`,`price`) VALUES (5,'Keychain','Keychain.jpg',3);
INSERT INTO `products` (`productid`,`name`,`imagefile`,`price`) VALUES (6,'Lanyard','Lanyard.jpg',2);
INSERT INTO `products` (`productid`,`name`,`imagefile`,`price`) VALUES (7,'Thermos','Thermos.jpg',35);

INSERT INTO `users` VALUES (null, 'pavel', '1234'); 
INSERT INTO `users` VALUES (null, 'john', 'passw0rd');
