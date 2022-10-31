CREATE TABLE common_code
(
    group_cd    VARCHAR(20),
    cd     VARCHAR(20),
    cd_nm VARCHAR(20),
    register_id varchar(20),
    update_id varchar(20),
    create_date timestamp DEFAULT CURRENT_TIMESTAMP,
    update_date timestamp,
    primary key(group_cd, cd)
) ENGINE=InnoDB CHARSET=utf8;

drop table common_code;
select * from common_code;
-- notice, nt 약어로? FREE FR 이렇게? 이건취향차이니까
insert into common_code( group_cd,cd, cd_nm,register_id) values('BBS', 'FR', '자유게시판', 'sujin');
insert into common_code( group_cd,cd, cd_nm,register_id) values('BBS', 'NT', '공지사항', 'sujin');

select * from common_code;
