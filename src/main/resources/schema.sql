create table if not exists authDB.oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256),
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(256)
) engine = innodb;

create table if not exists authDB.oauth_access_token
(
    token_id          VARCHAR(256),
    token             BLOB,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication    BLOB,
    refresh_token     VARCHAR(256)
) engine = innodb;

create table if not exists authDB.oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          BLOB,
    authentication BLOB
) engine = innodb;

create table if not exists authDB.user
(
    id                    bigint       not null primary key auto_increment,
    email                 varchar(256) not null unique,
    username              varchar(256) not null unique,
    phone                 varchar(256) not null unique,
    password              varchar(256) not null,
    enabled               boolean      not null,
    accountNonExpired     tinyint(4)   not null,
    credentialsNonExpired tinyint(4)   not null,
    accountNonLocked      tinyint(4)   not null,
    createdAt             DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) engine = innodb;

create table if not exists authDB.role
(
    id   int(11)      not null primary key auto_increment,
    name varchar(256) not null unique
) engine = innodb;

create table if not exists authDB.permission
(
    id   int(11)      not null primary key auto_increment,
    name varchar(256) not null unique
) engine = innodb;

create table if not exists authDB.user_permission
(
    permission_id int(11) default null,
    role_id       int(11) default null,
    key permission_id (permission_id),
    key role_id (role_id),
    constraint fk_permission_id foreign key (permission_id) references authDB.permission (id),
    constraint fk_role_id foreign key (role_id) references authDB.role (id)
) engine = innodb;

create table if not exists authDB.role_user
(
    role_id int(11) default null,
    user_id bigint  default null,
    key role_id (role_id),
    key user_id (user_id),
    constraint role_user_ibfk_1 foreign key (role_id) references role (id),
    constraint role_user_ibfk_2 foreign key (user_id) references user (id)
) engine = innodb;
