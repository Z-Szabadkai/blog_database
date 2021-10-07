DROP DATABASE blogDB;

CREATE DATABASE IF NOT EXISTS blogDB;

USE blogDB;

CREATE TABLE IF NOT EXISTS enduser (
    name VARCHAR (50) NOT NULL UNIQUE,
    privilege ENUM ('admin', 'moderator', 'user') DEFAULT 'user',
    email VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(35) NOT NULL,
    avatar MEDIUMBLOB,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (name)
    );

CREATE TABLE IF NOT EXISTS blog (
    blog_id INT UNSIGNED AUTO_INCREMENT,
    title VARCHAR(50),
    background MEDIUMBLOB,
    templatestyle ENUM ('book', 'food',	'marketing', 'music', 'news', 'personal', 'travel') DEFAULT 'personal',
    templatecolor ENUM ('black', 'brown', 'green', 'purple', 'red', 'yellow') DEFAULT 'black',
    blog_creator VARCHAR (50) NOT NULL,
    PRIMARY KEY (blog_id),
    FOREIGN KEY (blog_creator) REFERENCES enduser (name)
    );

CREATE TABLE IF NOT EXISTS content (
    title VARCHAR (50) NOT NULL,
    post VARCHAR (500),
    tags VARCHAR (20),
    published_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    can_comment_under BOOL DEFAULT TRUE,
    content_id INT UNSIGNED,
    PRIMARY KEY (title),
    FOREIGN KEY (content_id) REFERENCES blog (blog_id)
    );

CREATE TABLE IF NOT EXISTS comment (
    id INT UNSIGNED AUTO_INCREMENT,
    comment_creator VARCHAR (50),
    comment_post VARCHAR (200),
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    comment_under VARCHAR (50),
    PRIMARY KEY (id),
    FOREIGN KEY (comment_under) REFERENCES content (title)
    );