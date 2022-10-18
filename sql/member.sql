drop table member;

CREATE TABLE member (
    seq       INT NOT NULL AUTO_INCREMENT,
    email        VARCHAR(20),
    password  VARCHAR(100),
    username        VARCHAR(20),
    role        VARCHAR(12),
    PRIMARY KEY(seq)
) ENGINE=InnoDB CHARSET=utf8;


select * from member;


