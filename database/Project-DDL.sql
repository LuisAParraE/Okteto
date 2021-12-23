CREATE DATABASE Project; -- ### SHOULD I ASSIGN AN OWNER ??

CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; -- extension for uuid generation
CREATE EXTENSION IF NOT EXISTS "pgcrypto";  -- extension for password hashing, must be used on insertion

CREATE TABLE UserT (
    UserId UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    UserName varchar (40) NOT NULL UNIQUE,
    GivenName varchar (40) NOT NULL,
    Surname varchar (40) NOT NULL,
    Birthday date NOT NULL,
    UserPassword  text NOT NULL -- ### revisar si sugar anade tamano al hash
);

CREATE TABLE Project ( 
    ProjectName varchar (128) PRIMARY KEY,  -- ### Tal vez deberiamos darle un uuid al project
    ProjectDescription varchar (512) NOT NULL, 
    BeginDate date DEFAULT now()::date NOT NULL , 
    EndDate date NOT NULL, 
    IsActive boolean NOT NULL,
    ProjectOwner varchar (40) NOT NULL 
        REFERENCES UserT(UserName)
            ON DELETE CASCADE
            ON UPDATE CASCADE,

    CONSTRAINT ValidDate CHECK (BeginDate < EndDate)
);

CREATE DOMAIN statet AS Text CHECK ( VALUE IN ('to do', 'in progress', 'done') ) ;

CREATE TABLE Task ( 
    TaskId uuid DEFAULT uuid_generate_v4(),  
    ProjectName varchar (40) REFERENCES Project(ProjectName) 
        ON DELETE CASCADE
        ON UPDATE CASCADE ,
    TaskName varchar (40) NOT NULL,
    TaskDescription varchar (512), 
    TaskStatus statet NOT NULL, 

    PRIMARY KEY (TaskId, ProjectName)
);

-- Relation Tables

CREATE TABLE IsPartOf (
    ProjectName varchar (40) REFERENCES Project(ProjectName) 
        ON DELETE CASCADE
        ON UPDATE CASCADE, 
    UserId uuid REFERENCES UserT(UserId)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    PRIMARY KEY (ProjectName, UserId)
);

CREATE TABLE WorksOn (
    UserId uuid REFERENCES UserT(UserId) 
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    TaskId uuid , 
    ProjectName varchar (40) , 

    FOREIGN KEY (TaskId,ProjectName) 
        REFERENCES Task(TaskId,ProjectName)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    PRIMARY KEY (UserId, TaskId, ProjectName) 
);