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

CREATE TABLE IF NOT EXISTS content (
	id INT UNSIGNED NOT NULL UNIQUE,
	title VARCHAR (50),
    post VARCHAR (1000),
    tags VARCHAR (20),
    comment_creator VARCHAR (50),
    comment_post VARCHAR (200),
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (comment_creator) REFERENCES enduser (name)
);

CREATE TABLE IF NOT EXISTS blog (
	id INT UNIQUE,
	blog_creator VARCHAR (50) NOT NULL,
    blog_content VARCHAR (1000) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (blog_creator) REFERENCES enduser (name),
    FOREIGN KEY (blog_content) REFERENCES content (title)
);