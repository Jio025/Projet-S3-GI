CREATE TABLE master(
                       business_id VARCHAR(50),
                       PRIMARY KEY(business_id)
);

CREATE TABLE sessions(
                         no_session VARCHAR(50),
                         business_id VARCHAR(50) NOT NULL,
                         PRIMARY KEY(no_session),
                         FOREIGN KEY(business_id) REFERENCES master(business_id)
);

CREATE TABLE app(
                    no_app VARCHAR(50),
                    no_session VARCHAR(50) NOT NULL,
                    PRIMARY KEY(no_app),
                    FOREIGN KEY(no_session) REFERENCES sessions(no_session)
);

CREATE TABLE dockerfiles(
                            ID_DockerFile VARCHAR(50),
                            PATH TEXT NOT NULL,
                            Hash VARCHAR(40) NOT NULL,
                            tag_id INT,
                            Description TEXT NOT NULL,
                            PRIMARY KEY(ID_DockerFile),
                            UNIQUE(PATH),
                            UNIQUE(Hash)
);

CREATE TABLE yaml(
                     YAML_ID VARCHAR(50),
                     PATH TEXT NOT NULL,
                     Description TEXT NOT NULL,
                     tag_id INT,
                     Hash VARCHAR(40) NOT NULL,
                     no_app VARCHAR(50) NOT NULL,
                     PRIMARY KEY(YAML_ID),
                     UNIQUE(PATH),
                     UNIQUE(Hash),
                     FOREIGN KEY(no_app) REFERENCES app(no_app)
);

CREATE TABLE Dockerfile_List(
                                ID_DockerFile VARCHAR(50),
                                YAML_ID VARCHAR(50),
                                PRIMARY KEY(ID_DockerFile, YAML_ID),
                                FOREIGN KEY(ID_DockerFile) REFERENCES dockerfiles(ID_DockerFile),
                                FOREIGN KEY(YAML_ID) REFERENCES yaml(YAML_ID)
);