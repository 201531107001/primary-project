drop table if exists SHIRO_USER;
create table SHIRO_USER(
user_name varchar(20),
password varchar(20)
);

drop table if exists SHIRO_USER_ROLE;
create table SHIRO_USER_ROLE(
user_name varchar(20),
role_name varchar(10)
);
--'admin','guest','role1','role2'

drop table if exists SHIRO_ROLE_PERMISSION;
create table SHIRO_ROLE_PERMISSION(
role_name varchar(20),
perm_name varchar(10) 
);

--'*','add','update','delete','look'