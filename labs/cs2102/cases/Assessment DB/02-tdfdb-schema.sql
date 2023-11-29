/*******************
Create the schema
********************/


CREATE TABLE IF NOT EXISTS regions (
 name VARCHAR(32) PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS subregions (
 name VARCHAR(32) PRIMARY KEY,
 region VARCHAR(32) NOT NULL,
 CONSTRAINT fk_region FOREIGN KEY (region) REFERENCES regions (name)
);


CREATE TABLE IF NOT EXISTS countries (
 code CHAR(3) PRIMARY KEY,
 name VARCHAR(64) UNIQUE NOT NULL,
 subregion VARCHAR(32) NOT NULL,
 CONSTRAINT fk_subregion FOREIGN KEY (subregion) REFERENCES subregions (name)
);


CREATE TABLE IF NOT EXISTS teams (
 name VARCHAR(64) PRIMARY KEY,
 country CHAR(3) NOT NULL,
 CONSTRAINT fk_country FOREIGN KEY (country) REFERENCES countries (code) ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS riders ( 
 bib INTEGER PRIMARY KEY,
 name VARCHAR(64) NOT NULL,
 dob DATE NOT NULL,
 country CHAR(3) NOT NULL,
 team VARCHAR(64) NOT NULL,
 CONSTRAINT fk_team FOREIGN KEY (team) REFERENCES teams (name) ON UPDATE CASCADE,
 CONSTRAINT fk_country FOREIGN KEY (country) REFERENCES countries (code) ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS locations (
 name VARCHAR(64) PRIMARY KEY,
 country CHAR(3) NOT NULL,
 CONSTRAINT fk_country FOREIGN KEY (country) REFERENCES countries (code) ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS stages (
 nr INTEGER PRIMARY KEY,
 day DATE UNIQUE NOT NULL,
 start VARCHAR(64) NOT NULL,
 finish VARCHAR(64) NOT NULL,
 length NUMERIC(5,1) NOT NULL,
 type VARCHAR(32),
 CONSTRAINT fk_start FOREIGN KEY (start) REFERENCES locations (name) ON UPDATE CASCADE,
 CONSTRAINT fk_finish FOREIGN KEY (finish) REFERENCES locations (name) ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS sprints (
 stage INTEGER NOT NULL,
 location VARCHAR(64) NOT NULL,
 distance NUMERIC(5,1) NOT NULL,
 PRIMARY KEY (stage, location),
 CONSTRAINT fk_stage FOREIGN KEY (stage) REFERENCES stages (nr) ON UPDATE CASCADE,
 CONSTRAINT fk_location FOREIGN KEY (location) REFERENCES locations (name) ON UPDATE CASCADE 
);


CREATE TABLE IF NOT EXISTS mountains (
 stage INTEGER NOT NULL,
 location VARCHAR(64) NOT NULL,
 distance NUMERIC(5,1) NOT NULL,
 height NUMERIC(5,1) NOT NULL,
 length NUMERIC(3,1) NOT NULL,
 percent NUMERIC(3,1) NOT NULL,
 category CHAR(1) NOT NULL,
 PRIMARY KEY (stage, location),
 CONSTRAINT fk_stage FOREIGN KEY (stage) REFERENCES stages (nr) ON UPDATE CASCADE,
 CONSTRAINT fk_location FOREIGN KEY (location) REFERENCES locations (name) ON UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS results_individual (
 stage INTEGER NOT NULL,
 rank INTEGER NOT NULL CHECK (rank > 0),
 rider INTEGER NOT NULL,
 time INTEGER NOT NULL CHECK (time >= 0),
 bonus INTEGER NOT NULL DEFAULT 0 CHECK (bonus >= 0),
 penalty INTEGER NOT NULL DEFAULT 0 CHECK (penalty >= 0),
 CONSTRAINT fk_stage FOREIGN KEY (stage) REFERENCES stages (nr) ON UPDATE CASCADE,
 CONSTRAINT fk_rider FOREIGN KEY (rider) REFERENCES riders (bib) ON UPDATE CASCADE,
 CONSTRAINT pk_result_individual PRIMARY KEY (stage, rank, rider)
);


CREATE TABLE IF NOT EXISTS results_sprints (
 stage INTEGER NOT NULL,
 location VARCHAR(64) NOT NULL,
 rank INTEGER NOT NULL CHECK (rank > 0),
 rider INTEGER NOT NULL,
 points INTEGER NOT NULL CHECK (points > 0),
 CONSTRAINT fk_sprint FOREIGN KEY (stage, location) REFERENCES sprints (stage, location) ON UPDATE CASCADE,
 CONSTRAINT fk_rider FOREIGN KEY (rider) REFERENCES riders (bib) ON UPDATE CASCADE,
 CONSTRAINT pk_result_sprint PRIMARY KEY (stage, location, rank, rider)
);


CREATE TABLE IF NOT EXISTS results_mountains (
 stage INTEGER NOT NULL,
 location VARCHAR(64) NOT NULL,
 rank INTEGER NOT NULL CHECK (rank > 0), 
 rider INTEGER NOT NULL,
 points INTEGER NOT NULL CHECK (points > 0),
 CONSTRAINT fk_mountain FOREIGN KEY (stage, location) REFERENCES mountains (stage, location) ON UPDATE CASCADE,
 CONSTRAINT fk_rider FOREIGN KEY (rider) REFERENCES riders (bib) ON UPDATE CASCADE,
 CONSTRAINT pk_result_mountain PRIMARY KEY (stage, location, rank, rider)
);


CREATE TABLE IF NOT EXISTS results_combative (
 stage INTEGER NOT NULL,
 rider INTEGER NOT NULL,
 CONSTRAINT fk_stage FOREIGN KEY (stage) REFERENCES stages (nr) ON UPDATE CASCADE,
 CONSTRAINT fk_rider FOREIGN KEY (rider) REFERENCES riders (bib) ON UPDATE CASCADE,
 CONSTRAINT pk_winner PRIMARY KEY (stage, rider)
);
