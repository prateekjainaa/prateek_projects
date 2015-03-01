create table country( name varchar(100),
                      id int PRIMARY KEY
                      )ENGINE=InnoDB DEFAULT CHARSET=latin1; 

create table states ( name varchar(100),
                      cid int,
                      id int PRIMARY KEY,
                      FOREIGN KEY (cid) references country(id)
                     )ENGINE=InnoDB DEFAULT CHARSET=latin1; 

create table ashram_address ( aid int PRIMARY KEY,
                       description varchar(300) NOT NULL,
                       sid int,
                       name varchar(50),
					   headed_by varchar(150),
			           phone varchar(50),
                       zip   varchar(20),                      
                       FOREIGN KEY (sid) references states(id)
			     )ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table members ( mid int PRIMARY KEY,
                       first_name varchar (100),
					   last_name varchar(100),
                       guardian_name varchar(100),
                       dob DATE NOT NULL,
                       Sex varchar(1),
                       img_path varchar(250),
                       phone_landline varchar(50),
					   phone_mobile varchar(50),
                       is_swami varchar(1) NOT NULL DEFAULT 'N',
                       is_premi varchar(1) NOT NULL DEFAULT 'Y',
					   is_deekshit varchar(1) NOT NULL DEFAULT 'Y',
                       is_vip varchar(1) NOT NULL DEFAULT 'N',
                       email varchar(100),
                       relatedTO int NOT NULL,
                       sewa_ids varchar(150),
                       FOREIGN KEY (relatedTO) references ashram_address(aid)   
                      )ENGINE=InnoDB DEFAULT CHARSET=latin1;                       
                       
create table address ( id int PRIMARY KEY,
                       cid int NOT NULL REFERENCES country(id),
					   sid int NOT NULL REFERENCES states(id),
					   did int NOT NULL REFERENCES districts(id),
                       town varchar(50),
                       mid int,
                       description varchar(150) NOT NULL COMMENT 'actual address will come here',
					   is_permanent varchar(1) NOT NULL DEFAULT 'Y',
					   FOREIGN KEY (mid) references members(mid)					   
                      )ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table qualification (education varchar(300),
                            occupation varchar(150),
							other varchar(300),
                            mid int,
                            FOREIGN KEY (mid) references members(mid)
                           ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

create table deeksha ( deeksha_date DATE,
                      /*
                       * I have not related this to ashram_address(aid) because ashram sites can change. 
                       * I wish to keep orginal branch history here.
                       */
                       deeksha_branch varchar(150),
                       mid int,
					   FOREIGN KEY (mid) references members(mid)
                       ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/** table containing all possible sewas and their IDs **/
create table sewa (id int NOT NULL AUTO_INCREMENT,
display_label varchar(100) NOT NULL,
description varchar(200),
PRIMARY KEY(id))ENGINE=InnoDB DEFAULT CHARSET=latin1;

/** table containing all possible qualifications **/
create table qualificationList (id int NOT NULL AUTO_INCREMENT,
display_label varchar(150) NOT NULL,
description varchar(200),
PRIMARY KEY(id))ENGINE=InnoDB DEFAULT CHARSET=latin1;

 create table work_type_category (id INT NOT NULL PRIMARY KEY, description VARCHAR(100) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1;