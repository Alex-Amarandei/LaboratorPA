use `SocialNetwork`;

DROP TABLE `users`;
CREATE TABLE `users` (
	username VARCHAR(50) NOT NULL PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL
);

DROP TABLE `relations`;
CREATE TABLE `relations` (
	friend1 VARCHAR(50) NOT NULL,
  friend2 VARCHAR(50) NOT NULL,
  CHECK(friend1 <> friend2),
  PRIMARY KEY(friend1, friend2)
);

DROP TABLE `logs`;
CREATE TABLE `logs` (
	username VARCHAR(50) NOT NULL PRIMARY KEY
);

DROP TABLE `messages`;
CREATE TABLE `messages` (
	receiver VARCHAR(50) NOT NULL,
	sender VARCHAR(50) NOT NULL,
  message VARCHAR(200) NOT NULL
);
