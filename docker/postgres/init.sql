CREATE TABLE dockerfiles(
                            id COUNTER,
                            path_to_repo TEXT NOT NULL,
                            commit_hash CHAR(40) NOT NULL,
                            tag_id SMALLINT NOT NULL,
                            description TEXT,
                            maintainer_id BYTE,
                            last_verified DATETIME DEFAULT now(),
                            PRIMARY KEY(id)
);

CREATE TABLE tag(
                    tag_id COUNTER,
                    tag TEXT NOT NULL,
                    id INT,
                    PRIMARY KEY(tag_id),
                    FOREIGN KEY(id) REFERENCES dockerfiles(id)
);

CREATE TABLE maintainer(
                           maintainer_id BYTE,
                           maintainer_name VARCHAR(100),
                           id INT,
                           PRIMARY KEY(maintainer_id),
                           FOREIGN KEY(id) REFERENCES dockerfiles(id)
);

CREATE TABLE package(
                        package_id COUNTER,
                        package_name TEXT NOT NULL,
                        path_to_package TEXT NOT NULL,
                        PRIMARY KEY(package_id)
);

CREATE TABLE dockerfile_package(
                                   id INT,
                                   package_id INT,
                                   PRIMARY KEY(id, package_id),
                                   FOREIGN KEY(id) REFERENCES dockerfiles(id),
                                   FOREIGN KEY(package_id) REFERENCES package(package_id)
);
CREATE INDEX idx_tag ON dockerfiles(tag);
CREATE INDEX idx_commit_hash ON dockerfiles(commit_hash);