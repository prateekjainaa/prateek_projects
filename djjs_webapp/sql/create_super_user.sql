
-->mysqladmin -u root -p create djjs_webapp
--mysqladmin create djjs_webapp -p; 

create table pi_super_user(
  id integer AUTO_INCREMENT PRIMARY KEY ,
  userName varchar(100) NOT NULL,
   passwd varchar(250) NOT NULL,
   email varchar(100)   
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

/**
insert into pi_super_user (userName, passwd, email) values ('prateekjainaa', password('jainaa'),'prateek@jain.com');
select * from pi_super_user where (userName='prateekjainaa' and passwd=password('jainaa'));

**/

create table pi_admins(
id integer AUTO_INCREMENT PRIMARY KEY,
userName varchar(100) NOT NULL,
name varchar(100),
paswd varchar(250) NOT NULL,
email varchar(100),
createdBy int,
createdDate date,
is_active varchar(1) default 'Y',
FOREIGN KEY (createdBy) references pi_super_user(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

/**
 * 
 * insert into pi_admins(userName, name, paswd, email, createdBy, createdDate) values ('rrr', 'prateek', password('rrr'),'p@r.com',1,curdate());
 * select * from pi_admins where (userName='rrr' and name='prateek' and email='p@r.com' and createdBy=1);
 */

create table pi_allowed_countries(
id integer AUTO_INCREMENT PRIMARY KEY,
aid integer NOT NULL,
allowed_countries varchar(500),
updated_date date NOT NULL,
FOREIGN KEY (aid) references pi_admins(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

/**
 * insert into pi_allowed_countries(aid,allowed_countries,updated_date) values (1,'91',CURDATE());
 * allowed countries is a , separated list of country ids. 
 **/

create table pi_allowed_states(
id integer AUTO_INCREMENT PRIMARY KEY,
all_countries_id integer NOT NULL,
country_id integer NOT NULL,
allowed_states varchar(1000),
updated_date date NOT NULL,
FOREIGN KEY (all_countries_id) references pi_allowed_countries(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

/**
 * insert into pi_allowed_states(all_countries_id, country_id,allowed_states,updated_date) 
 * values (1,1,'1,2',CURDATE()); 
 */

create table pi_allowed_districts(
id integer AUTO_INCREMENT PRIMARY KEY,
allowed_states_id integer NOT NULL,
state_id integer NOT NULL,
allowed_districts varchar(3000),
updated_date date NOT NULL,
FOREIGN KEY (allowed_states_id) references pi_allowed_states(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

/**
 * insert into pi_allowed_districts (allowed_states_id, state_id, allowed_districts, updated_date) values (
 * 2, 1,'41,45,47,49',CURDATE());
 */

