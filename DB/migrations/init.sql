CREATE TABLE DockerConfigs(
   config_id SERIAL,
   base_image VARCHAR(100) ,
   PRIMARY KEY(config_id)
);

CREATE TABLE Packages(
   package_id SERIAL,
   package_name VARCHAR(100) ,
   PRIMARY KEY(package_id)
);

CREATE TABLE ConfigPackages(
   config_id INTEGER,
   package_id INTEGER,
   PRIMARY KEY(config_id, package_id),
   FOREIGN KEY(config_id) REFERENCES DockerConfigs(config_id),
   FOREIGN KEY(package_id) REFERENCES Packages(package_id)
);
