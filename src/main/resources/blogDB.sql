DROP DATABASE blogDB;

CREATE DATABASE IF NOT EXISTS blogDB;

USE blogDB;

CREATE TABLE IF NOT EXISTS enduser (
    user_id INT UNSIGNED AUTO_INCREMENT,
    name VARCHAR (50) NOT NULL UNIQUE,
    privilege ENUM ('admin', 'moderator', 'user', 'lurker') DEFAULT 'lurker',
    email VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(35) NOT NULL,
    avatar MEDIUMBLOB,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id)
    );

CREATE TABLE IF NOT EXISTS blog (
    title VARCHAR(50),
    background MEDIUMBLOB,
    templatestyle ENUM ('book', 'food',	'marketing', 'music', 'news', 'personal', 'travel') DEFAULT 'personal',
    templatecolor ENUM ('black', 'brown', 'green', 'purple', 'red', 'yellow') DEFAULT 'black',
    blog_creator INT UNSIGNED,
    PRIMARY KEY (title),
    FOREIGN KEY (blog_creator) REFERENCES enduser (user_id)
    );

CREATE TABLE IF NOT EXISTS content (
    content_id INT UNSIGNED AUTO_INCREMENT,
    title VARCHAR (50) NOT NULL,
    post VARCHAR (500),
    tags VARCHAR (20),
    post_status ENUM ('draft', 'pending', 'publish', 'future') DEFAULT 'draft',
    published_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    can_comment_under BOOL DEFAULT TRUE,
    content_creator INT UNSIGNED,
    blog_content VARCHAR (50),
    PRIMARY KEY (content_id),
    FOREIGN KEY (content_creator) REFERENCES enduser (user_id),
    FOREIGN KEY (blog_content) REFERENCES blog (title)
    );

CREATE TABLE IF NOT EXISTS comment (
    comment_id INT UNSIGNED AUTO_INCREMENT,
    comment_post VARCHAR (200),
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    comment_creator INT UNSIGNED,
    comment_under INT UNSIGNED,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (comment_creator) REFERENCES enduser (user_id),
    FOREIGN KEY (comment_under) REFERENCES content (content_id)
    );