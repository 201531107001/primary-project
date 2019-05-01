insert into SHIRO_USER values('gqm','990219');
insert into SHIRO_USER values('gql','990219');
insert into SHIRO_USER values('gqp','990219');
insert into SHIRO_USER values('guest','guest');

insert into SHIRO_USER_ROLE values('gqm','admin');
insert into SHIRO_USER_ROLE values('gql','role1');
insert into SHIRO_USER_ROLE values('gqp','role2');
insert into SHIRO_USER_ROLE values('guest','guest');

insert into SHIRO_ROLE_PERMISSION values('admin','*');
insert into SHIRO_ROLE_PERMISSION values('role1','delete');
insert into SHIRO_ROLE_PERMISSION values('role1','add');
insert into SHIRO_ROLE_PERMISSION values('role1','look');
insert into SHIRO_ROLE_PERMISSION values('role2','update');
insert into SHIRO_ROLE_PERMISSION values('role2','look');
insert into SHIRO_ROLE_PERMISSION values('guest','look');