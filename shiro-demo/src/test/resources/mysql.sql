drop table if exists USERS;
create table USERS(
username varchar(20),
password varchar(20)
);

drop table if exists user_roles;
create table user_roles(
username varchar(20),
role_name varchar(10)
);
--'admin','guest','role1','role2'

drop table if exists roles_permissions;
create table roles_permissions(
role_name varchar(20),
permission varchar(10) 
);

--'*','add','update','delete','look'

insert into USERS values('gqm','990219');
insert into USERS values('gql','921015');
insert into USERS values('gqp','940415');
insert into USERS values('guest','guest');

insert into user_roles values('gqm','admin');
insert into user_roles values('gql','role1');
insert into user_roles values('gqp','role2');
insert into user_roles values('guest','guest');

insert into roles_permissions values('admin','*');
insert into roles_permissions values('role1','delete');
insert into roles_permissions values('role1','add');
insert into roles_permissions values('role2','update');
insert into roles_permissions values('guest','look');