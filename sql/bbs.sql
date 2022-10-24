drop table bbs;

CREATE TABLE bbs
(
    seq         INT NOT NULL AUTO_INCREMENT,
    type        Varchar(2),
    title       VARCHAR(100),
    content     VARCHAR(2000),
    name        VARCHAR(20),
    member_seq int ,
    cnt         int default 0,  -- 이건보여지는값
    private_yn  varchar(1) default 'N', -- 이건 옵션값 // 화면에보여지는옵션값
    delete_yn   varchar(1) default 'N', -- 이것도 옵션 // 관리되는옵션값
    create_date timestamp DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp,
    PRIMARY KEY (seq)
) ENGINE=InnoDB CHARSET=utf8;

