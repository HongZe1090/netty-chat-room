-- 用户表
-- auto-generated definition
create table User
(
    userId   int auto_increment comment '自增，用户id'
        primary key,
    userName varchar(20) null comment '用户名',
    password varchar(20) null comment '密码',
    sex      varchar(20) null comment '性别',
    image    varchar(20) null comment '头像路径',
    admin    tinyint(1)  null comment '是否是管理员 1是 0不是'
)
    comment '用户表' charset = utf8;

