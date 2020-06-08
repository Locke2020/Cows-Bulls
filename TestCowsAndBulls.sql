DROP DATABASE IF EXISTS TestCowsAndBulls;

CREATE DATABASE TestCowsAndBulls;

USE TestCowsAndBulls;

CREATE TABLE Games (
GameID int auto_increment PRIMARY KEY,
GameActive boolean not null default 1,
GameAnswer int not null
);

CREATE TABLE Rounds (
RoundID int auto_increment PRIMARY KEY,
RoundTime datetime not null,
GameID int not null,
FullMatch int not null,
PartialMatch int not null,
GuessNo int not null,
Foreign Key fk_Games_Rounds (GameID)
	REFERENCES Games(GameID)
);
