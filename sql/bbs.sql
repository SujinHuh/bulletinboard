drop table bbs;

CREATE TABLE bbs
(
    seq         INT NOT NULL AUTO_INCREMENT,
    bbs_cd      Varchar(20),
    title       VARCHAR(100),
    content     VARCHAR(2000),
    name        VARCHAR(20),
    email       VARCHAR(20),
    member_seq  int,
    cnt         int        default 0,   -- 이건보여지는값
    private_yn  varchar(1) default 'N', -- 이건 옵션값 // 화면에보여지는옵션값
    delete_yn   varchar(1) default 'N', -- 이것도 옵션 // 관리되는옵션값
    create_date timestamp  DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp,
    PRIMARY KEY (seq)
) ENGINE = InnoDB
  CHARSET = utf8;


-- bbs.email, member.email 동일한 것으로  bbs.memberseq 값을 구하기

SELECT * FROM bbs;

DELETE FROM bbs;

Select seq, title, name, cnt, create_date FROM bbs Where bbs_cd = 'FR' AND delete_yn = 'N' order by seq DESC;

SELECT seq, title, content FROM bbs where seq = 4 AND delete_yn = 'N' AND private_yn = 'N';

# UPDATE bbs SET 변경할 값 where 열
update bbs set title = '제목수정1' ,content ='내용수정을 했지욤1', update_date = now() where seq = 9;

# delete
update bbs set delete_yn = 'Y',update_date = now() where seq = 8 AND member_seq = 1;
delete from bbs where seq = 1 AND member_seq = 1;