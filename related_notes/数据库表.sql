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

-- 日志表
create table Log
(
    id int auto_increment comment '自增，log的id',
    title char(20) comment '操作模块',
    businessType char(20) comment '业务类型',
    requestMethod char(20) comment '请求方法',
    operName char(20) comment '操作人员',
    operatorType char(20) comment '操作类别',
    operUrl char(20) comment '请求url',
    operIp char(20) comment '操作地址',
    operStatus Integer comment '操作状态' DEFAULT '0',
    operTime date comment '操作时间',
    PRIMARY KEY(id)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 comment='日志表';

-- 消息表
create table Message
(
    id int auto_increment comment '自增，log的id',
    fromId int comment '消息发送方id',
    toId int comment '消息接收方id',
    infoContent varchar(30) comment '消息内容',
    online boolean comment '是否在线，1在线，0离线' default 1,
    singleMessage boolean comment '死否私聊消息，1私聊，0群聊' default 1,
    sendTime date comment '发送时间',
    PRIMARY KEY(id)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 comment='消息表';

