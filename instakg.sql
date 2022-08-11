DROP DATABASE IF EXISTS project_instakg;
CREATE DATABASE project_instakg;
USE project_instakg;

CREATE TABLE board (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL DEFAULT NOW(),
    modify_date DATETIME DEFAULT NOW(),
    hit INT(11) UNSIGNED,
    reply_like TINYINT NOT NULL DEFAULT 0,
    on_off TINYINT DEFAULT 0
);


CREATE TABLE reply (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL DEFAULT NOW(),
    modify_date DATETIME DEFAULT NOW(),
    board_id INT(11) UNSIGNED NOT NULL,
    reply_like VARCHAR(10) NOT NULL
);

CREATE TABLE files(
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    file_name TEXT,
    board_id BIGINT UNSIGNED NOT NULL
);



SELECT * FROM Question;
SELECT * FROM Answer;
SELECT * FROM files;