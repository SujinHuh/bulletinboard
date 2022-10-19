drop table member;

CREATE TABLE member (
    seq       INT NOT NULL AUTO_INCREMENT,
    email        VARCHAR(20),
    password  VARCHAR(100),
    name        VARCHAR(20),
    role        VARCHAR(20),
    create_date        timestamp DEFAULT CURRENT_TIMESTAMP,
    update_date       timestamp,
    PRIMARY KEY(seq)
) ENGINE=InnoDB CHARSET=utf8;



select * from member;


update member set role = 'ROLE_ADMIN' where seq = 1;
update member set role = 'ROLE_PROFESSOR' where seq = 2;