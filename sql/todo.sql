drop table todo;

CREATE TABLE todo (
    seq             INT NOT NULL AUTO_INCREMENT,
    email           VARCHAR(20),
    text            VARCHAR(500),
    success_yn      VARCHAR(1) default 'N',
    delete_yn       VARCHAR(1) default 'N',
    create_date     timestamp DEFAULT CURRENT_TIMESTAMP,
    update_date     timestamp,
    PRIMARY KEY(seq)
) ENGINE=InnoDB CHARSET=utf8;


select * from todo;

