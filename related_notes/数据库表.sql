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
    admin    tinyint(1)  null comment '是否是管理员 1是 0不是',
    description varchar(30) null comment '用户描述',
)
    comment '用户表' charset = utf8;

-- 好友关系表
create table FriendRelation
(
    relation   int auto_increment comment '自增，关系id',
    owerId   int auto_increment comment '有人',
    friendId   int auto_increment comment '友人',
    PRIMARY KEY(relation),
    FOREIGN KEY(owerId) REFERENCES User(userId),
    FOREIGN KEY(friendId) REFERENCES User(userId)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 comment='好友关系表';
