create table teacher(
    uid int primary key auto_increment,
    username varchar(20),
    password varchar(20),
    wage int,
    age int,
    sex int
);

insert into teacher (username,password,wage,age,sex) values ('cc','123456',15000,30,0);
insert into teacher (username,password,wage,age,sex) values ('zrl','123456',10000,40,0);