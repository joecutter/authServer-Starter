--
--USER
--
drop table users if exists;
create table users(
    id bigint not null primary key,
    email varchar_ignorecase(256) not null primary key,
    username varchar_ignorecase(256) not null,
    password varchar_ignorecase(256) not null,
    enabled boolean not null
    phone varchar_ignorecase(256) not null,
    createdAt Date not null default 0;
);

drop table roles if exists;
create table roles(
    id bigint not null primary key,
    name varchar_ignorecase(256) not null,
);

drop table userAuthority if exists;
create table userAuthority (
    user_id bigint not null,
    authority_id bigint not null
    constraint fk_user_id foreign key(user_id) references users(id)
    constraint fk_authority_id foreign key(authority_id) references roles(id)
);

--
--CLIENT
--
drop table oauth_client_details if exists;
create table oauth_client_details (
    client_id varchar(256) primary key,
    resource_ids varchar(256),
    client_secret varchar(256),
    scope varchar(256),
    authorized_grant_types varchar(256),
    web_server_redirect_uri varchar(256),
    authorities varchar(256),
    access_token_validity integer,
    refresh_token_validity integer,
    additional_information varchar(4096),
    autoapprove varchar(256)
);

drop table oauth_access_token if exists;
create table oauth_access_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGVARBINARY,
  refresh_token VARCHAR(256)
);

drop table oauth_refresh_token if exists;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONGVARBINARY,
  authentication LONGVARBINARY
);