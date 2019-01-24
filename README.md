## UserAddress
Client-server web application for maintaining the people and address lists.
With possibilities to: 
 - Add new person and address
 - Edit/delete person, address data
 - Specify the persons address from the list of addresses. 
 - Search from users or address list.

### Technologies used: 
Java, Spring MVC, Hibernate, Maven, MySQL, Log4J, JSP, JSTL.

### How To Run

Prepare Database Structure

CREATE TABLE `address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CITY` varchar(255) DEFAULT NULL,
  `FLAT_NUMBER` int(11) DEFAULT NULL,
  `HOME_NUMBER` varchar(255) DEFAULT NULL,
  `REGION` varchar(255) DEFAULT NULL,
  `STREET` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BIRTH_DATE` datetime DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `PATRONYMIC` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `address_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK27E3CB16002408` (`address_ID`),
  CONSTRAINT `FK27E3CB16002408` FOREIGN KEY (`address_ID`) REFERENCES `address` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8

run mvn package and place newly generated `war` archive into userAddress folder at your $CATALINA_HOME\webapps directory

configure your database configuration in `userAddress\spring.properties` file

run tomcat-server

open your browser and navigate http://localhost:8080/userAddress/main/index.html
