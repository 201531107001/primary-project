drop table if exists student;
create table student(
	uid	int not null primary key auto_increment,
	username varchar(20),
	password varchar(20),
	age int,
	sex int
);