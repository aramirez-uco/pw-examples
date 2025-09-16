DROP TABLE IF EXISTS `Student`;
DROP TABLE IF EXISTS `Course`;
DROP TABLE IF EXISTS `Professor`;

CREATE TABLE IF NOT EXISTS `Student` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `birthDate` DATE DEFAULT NULL,
  `type` ENUM('FULL_TIME', 'PARTIAL_TIME', 'ERASMUS'),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `Professor` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `department` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `Course` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `degree` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `year` int(11) NOT NULL,
  `idProfessor` int(11),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE `Course`
ADD FOREIGN KEY (`idProfessor`) REFERENCES `Professor`(`id`);

CREATE TABLE IF NOT EXISTS `StudentCourse`(
  `idStudent` int(11) NOT NULL,
  `idCourse` int(11) NOT NULL,
  CONSTRAINT PK_StudentCourse PRIMARY KEY (`idStudent`, `idCourse`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `Student` VALUES (1, 'Andrea', 'Aguirre', '2000-01-10', 'FULL_TIME');
INSERT INTO `Student` VALUES (2, 'Beatriz', 'Benitez', '2000-02-12', 'FULL_TIME');
INSERT INTO `Student` VALUES (3, 'Carlos', 'Castro', '2000-03-03', 'PARTIAL_TIME');
INSERT INTO `Student` VALUES (4, 'David', 'Dominguez', '2001-04-14', 'FULL_TIME');
INSERT INTO `Student` VALUES (5, 'Eric', 'Elliott', '2002-05-25', 'ERASMUS');
INSERT INTO `Student` VALUES (6, 'Francesca', 'Ferguson', '2002-06-26', 'ERASMUS');

INSERT INTO `Course` VALUES (1, 'Programming', 'Computer Science', 1, NULL);
INSERT INTO `Course` VALUES (2, 'Maths', 'Computer Science', 1, NULL);
INSERT INTO `Course` VALUES (3, 'Software Engineering', 'Computer Science', 2, NULL);
INSERT INTO `Course` VALUES (4, 'Web Programming', 'Computer Science', 3, NULL);

INSERT INTO `Professor` VALUES (1, 'Martin', 'Moreno', 'Maths and Statistics');
INSERT INTO `Professor` VALUES (2, 'Rodrigo', 'Redondo', 'Computer Science and AI');
INSERT INTO `Professor` VALUES (3, 'Sara', 'Saez', 'Computer Science and AI');