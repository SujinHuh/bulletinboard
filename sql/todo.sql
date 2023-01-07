drop table todo;

CREATE TABLE todo (
    seq             INT NOT NULL AUTO_INCREMENT,
    #email           VARCHAR(20),
    member_seq      INT,
    text            VARCHAR(500),
    success_yn      VARCHAR(1) default 'N',
    delete_yn       VARCHAR(1) default 'N',
    create_date     timestamp DEFAULT CURRENT_TIMESTAMP,
    update_date     timestamp,
    PRIMARY KEY(seq)
) ENGINE=InnoDB CHARSET=utf8;


select t.member_seq, m.* from todo t
left join member m
on t.member_seq = m.seq;


select c.cd_nm, b.title, b.content from bbs b, common_code c where
                                       b.bbs_cd = c.cd
