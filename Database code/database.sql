create database student_management;

use student_management;

drop table student;
drop table course;
drop table score;
drop table admin;


create table admin(
	id int NOT NULL auto_increment,
    username varchar(45) NOT NULL,
    password varchar(45) NOT NULL,
    PRIMARY KEY(id)
    );
    
    create table student(
		id int NOT NULL AUTO_INCREMENT,
        name varchar(150) NOT NULL,
        gender varchar(10) NOT NULL,
        email varchar(100) NOT NULL,
        phone varchar(15) NOT NULL,
        father_name varchar(150) NOT NULL,
        mother_name varchar(150) NOT NULL,
        address1 text NOT NULL,
        address2 text NOT NULL,
        PRIMARY KEY(id)
       );
       
      
       
       create table course(
			id int NOT NULL AUTO_INCREMENT,
            student_id int DEFAULT NULL,
            semester int DEFAULT NULL,
            course1 varchar(200) DEFAULT NULL,
            course2 varchar(200) DEFAULT NULL,
            course3 varchar(200) DEFAULT NULL,
            course4 varchar(200) DEFAULT NULL,
            course5 varchar(200) DEFAULT NULL,
			PRIMARY KEY(id),
            KEY fk_student_id(student_id),
            CONSTRAINT fk_student_id FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
       );
       
       
       
       
       
       create table score(
			id int NOT NULL AUTO_INCREMENT,
            student_id int NOT NULL,
            semester int NOT NULL,
            course1 varchar(200) NOT NULL,
            score1 double NOT NULL,
			course2 varchar(200) NOT NULL,
			score2 double NOT NULL,
			course3 varchar(200) NOT NULL,
			score3 double NOT NULL,
			course4 varchar(200) NOT NULL,
			score4 double NOT NULL,
			course5 varchar(200) NOT NULL,
			score5 double NOT NULL,
			average double NOT NULL,
            PRIMARY KEY (id),
			CONSTRAINT fk_stu_id FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE
            );
            
            
            
       
       
       