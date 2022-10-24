drop table board;

CREATE TABLE bbs (
                        seq       INT NOT NULL AUTO_INCREMENT,
                        type        Varchar(2),
                        title        VARCHAR(100),
                        content  VARCHAR(2000),
                        cnt         int defalut 0,
                        create_date        timestamp DEFAULT CURRENT_TIMESTAMP,
                        update_date       timestamp,
                        PRIMARY KEY(seq)
) ENGINE=InnoDB CHARSET=utf8;