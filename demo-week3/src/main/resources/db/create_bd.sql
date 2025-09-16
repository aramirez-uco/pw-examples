DROP TABLE IF EXISTS `Student`;
CREATE TABLE IF NOT EXISTS `Student` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `birthDate` DATE DEFAULT NULL,
  `type` ENUM('FULL_TIME', 'PARTIAL_TIME', 'ERASMUS'),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `Student` VALUES (1, 'Andrea', 'Aguirre', '2000-01-10', 'FULL_TIME');
INSERT INTO `Student` VALUES (2, 'Beatriz', 'Benitez', '2000-02-12', 'FULL_TIME');
INSERT INTO `Student` VALUES (3, 'Carlos', 'Castro', '2000-03-03', 'PARTIAL_TIME');
INSERT INTO `Student` VALUES (4, 'David', 'Dominguez', '2001-04-14', 'FULL_TIME');
INSERT INTO `Student` VALUES (5, 'Eric', 'Elliott', '2002-05-25', 'ERASMUS');
INSERT INTO `Student` VALUES (6, 'Francesca', 'Ferguson', '2002-06-26', 'ERASMUS');