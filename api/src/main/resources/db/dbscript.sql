create table wmrms_category(category_id smallint primary key,
                              category_name varchar(255) unique);
                              
                              
insert into wmrms_category(category_id,category_name) values (1,'Regular');
insert into wmrms_category(category_id,category_name) values (2,'Contract');



create table wmrms_department(dept_id               smallint primary key,
                                dept_name               varchar(255) unique);
                                
                                
insert into wmrms_department(dept_id,dept_name) values(1,'Production');
insert into wmrms_department(dept_id,dept_name) values(2,'Drilling & Blasting');
insert into wmrms_department(dept_id,dept_name) values(3,'Manitenance');
insert into wmrms_department(dept_id,dept_name) values(4,'Geology');
insert into wmrms_department(dept_id,dept_name) values(5,'Electrical Manitenance');
insert into wmrms_department(dept_id,dept_name) values(6,'Crusher maintenance');
insert into wmrms_department(dept_id,dept_name) values(7,'Service & Security');


create table wmrms_workman(emp_no                       int             primary key,
                           status                       char(1)         not null,
                           first_name                   varchar(255)    not null, 
                           middle_name                  varchar(255), 
                           last_name                    varchar(255),
                           adhar_no                     NUMERIC(12,0),
                           dob                          DATE not null,
                           doj                          DATE,
                           dept_id                      smallint REFERENCES wmrms_department(dept_id),
                           category_id                  smallint REFERENCES wmrms_category(category_id) ,
                           identity_mark                text,
                           created_datetime             TIMESTAMP   not null,
                           created_by                   int     ,
                           last_modified_datetime       TIMESTAMP   not null,
                           last_modified_by             int);

                       
                    
                        
                        
                        
create table  wmrms_app_permission(permission_id        int primary key ,
                                   permission_name      varchar(255) not null,
                                   permission_desc      text );
                               




create table wrms_app_role(role_id                          SERIAL primary key,
                           role_name                        varchar(255) unique,
                           role_desc                        text,
                           created_by                       int     ,
                           created_datetime                 TIMESTAMP   not null,
                           last_modified_by                 int,       
                           last_modified_datetime           TIMESTAMP   not null);
                            
                               

create table wrms_app_role_permission(role_id           smallint REFERENCES wrms_app_role(role_id),
                                      permission_id     int   REFERENCES wmrms_app_permission(permission_id));


                     
create table wmrms_user(user_id                     int primary key,
                       password                     VARCHAR(80) not null,
                       status                       char(1) not null,
                       first_name                   varchar(255)    not null, 
                       middle_name                  varchar(255), 
                       last_name                    varchar(255),
                       role_id                      smallint REFERENCES wrms_app_role(role_id),
                       created_by                   int ,
                       created_datetime             TIMESTAMP   not null,
                       last_modified_by             int,
                       last_modified_datetime       TIMESTAMP   not null);

                             
                             



insert into wmrms_user(user_id,password,status,first_name,middle_name,last_name,created_datetime,last_modified_datetime) values(1111,'$2a$10$TCLgo83QxIETIHNWZnUVlehwqNfBtT0SgolKhyFpcKUOTe9MvUB5a','A','admin',null,null,current_timestamp,current_timestamp);



insert into wmrms_app_permission (permission_id,permission_name,permission_desc)
            values(1,'ALLOW_ALL','Allow all action permission');