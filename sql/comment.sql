CREATE TABLE comment
(
    seq        INT NOT NULL AUTO_INCREMENT,
    text       VARCHAR(200),
    bbs_seq    INT,
    member_seq INT,
    delete_yn  varchar(1) default 'N',
    create_date timestamp  DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp,
    PRIMARY KEY (seq)
)ENGINE=InnoDB CHARSET=utf8;


SELECT * FROM comment where bbs_seq = bbs_seq and delete_yn = 'N';
SELECT * FROM COMMENT WHERE bbs_seq = 4
