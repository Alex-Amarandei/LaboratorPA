USE `Lab8`;

DROP TABLE `movies`;
CREATE TABLE `movies` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(100) NOT NULL,
    `release_date` DATE NOT NULL,
    `duration` INT NOT NULL,
    `score` INT NOT NULL,
    CHECK (`score` IN(0, 1, 2, 3, 4, 5)),
    PRIMARY KEY(`id`)
);

DROP TABLE `genres`;
CREATE TABLE `genres` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`id`)
);
	
DROP TABLE `movie_genres`;
CREATE TABLE `movie_genres` (
	`movie_id` VARCHAR(5) NOT NULL,
    `genre_id` VARCHAR(3) NOT NULL,
    PRIMARY KEY(`movie_id`, `genre_id`)
);