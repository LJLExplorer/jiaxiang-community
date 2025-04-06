create table if not exists community_profile
(
    id           bigint auto_increment
        primary key,
    title        varchar(50)   null comment '标题',
    author_id    int           null comment '发表人id',
    community_id int           null comment '社区id',
    author_name  int           null comment '发表人名称',
    layout       tinyint       null comment '无图文章、单图文章、多图文章',
    images       varchar(1000) null comment '文章图片的url',
    comment      int           null comment '评论数量',
    views        int           null comment '浏览数量',
    created_time datetime      null comment '创建时间',
    edit_time    int           null comment '修改时间',
    publish_time int           null,
    constraint community_profile_pk_2
        unique (id),
    constraint community_profile_pk_3
        unique (author_id)
)
    comment '社区简介表';

	


create table if not exists community_profile_config 
(
    id                   bigint auto_increment
        primary key,
    community_profile_id bigint               null,
    is_comment           tinyint default 0 null comment '是否可评论(0/1)，默认0不可以',
    is_show              int     default 1 null comment '是否展示（0/1），默认1 展示(不展示相当于草稿)',
    is_delete            int     default 0 null comment '是否删除（默认0未删除）',
    constraint community_profile_config_pk_2
        unique (id),
    constraint community_profile_config_pk_3
        unique (community_profile_id)
)
    comment '社区简介配置表';
	
create table if not exists community_profile_content  
(
    id                   bigint auto_increment
        primary key,
    community_profile_id bigint   null,
    content              longtext null comment '社区简介内容',
    constraint community_profile_content_pk_2
        unique (id)
)
    comment '社区简介内容表';


create table if not exists community
(
    id          int auto_increment
        primary key,
    name        varchar(100) null comment '社区名字',
    description varchar(500) null comment '简单社区描述',
    constraint community_name_pk_2
        unique (id)
)
    comment '社区表 标注有多少个社区';





