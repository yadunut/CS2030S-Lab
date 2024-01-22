CREATE TABLE Offices
(
    oid     INTEGER,
    address VARCHAR(60),
    PRIMARY KEY (oid)
);

-- eid = eid of department 's manager
CREATE TABLE IF NOT EXISTS Departments
(
    did     INTEGER,
    dbudget INTEGER NOT NULL,
    oid     INTEGER NOT NULL,
    eid     INTEGER NOT NULL, -- no FK to manager
    PRIMARY KEY (did),
    FOREIGN KEY (oid) REFERENCES Offices
);

CREATE TABLE IF NOT EXISTS Employees
(
    eid INTEGER,
    did INTEGER NOT NULL,
    PRIMARY KEY (eid),
    FOREIGN KEY (did) REFERENCES Departments
);

CREATE TABLE IF NOT EXISTS Engineers
(
    eid INTEGER,
    PRIMARY KEY (eid),
    FOREIGN KEY (eid) REFERENCES Employees
);

CREATE TABLE IF NOT EXISTS Managers
(
    eid INTEGER,
    PRIMARY KEY (eid),
    FOREIGN KEY (eid) REFERENCES Employees
);

-- eid = eid of project's supervisor
CREATE TABLE IF NOT EXISTS Projects
(
    pid     INTEGER,
    pbudget INTEGER NOT NULL,
    eid     INTEGER NOT NULL,
    PRIMARY KEY (pid),
    FOREIGN KEY (eid) REFERENCES Managers
);

CREATE TABLE IF NOT EXISTS WorkType
(
    wid       INTEGER,
    max_hours INTEGER NOT NULL,
    PRIMARY KEY (wid)
);

CREATE TABLE IF NOT EXISTS Works
(
    pid   INTEGER,
    eid   INTEGER,
    wid   INTEGER,
    hours INTEGER NOT NULL,
    PRIMARY KEY (pid, eid),
    FOREIGN KEY (eid) REFERENCES Engineers,
    FOREIGN KEY (pid) REFERENCES Projects,
    FOREIGN KEY (wid) REFERENCES WorkType
        ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION not_manager()
    RETURNS TRIGGER AS
$$
DECLARE
    count NUMERIC;
BEGIN
    SELECT COUNT(*) INTO count FROM managers WHERE NEW.eid = Managers.eid;
    if count > 0 THEN RETURN NULL; ELSE RETURN NEW; END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER non_manager
    BEFORE INSERT OR UPDATE
    ON Engineers
    FOR EACH ROW
EXECUTE FUNCTION not_manager();

CREATE OR REPLACE FUNCTION check_budget()
    RETURNS TRIGGER AS
$$
DECLARE
    pbudget     numeric;
    curr_budget numeric;
BEGIN
    SELECT pbudget
    INTO pbudget
    FROM projects
    WHERE pid = NEW.pid;

    SELECT COALESCE(SUM(hours), 0) * 100
    INTO curr_budget
    FROM Works
    WHERE pid = NEW.pid
      AND eid != NEW.eid;
    if curr_budget + NEW.hours * 100 > pbudget THEN
        RETURN (NEW.pid, NEW.eid, NEW.wid, (pbudget - curr_budget) / 100);
    ELSE
        RETURN NEW;
    end if;

END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER budget_check
    BEFORE INSERT OR UPDATE
    ON Works
    FOR EACH ROW
EXECUTE FUNCTION check_budget();

CREATE OR REPLACE FUNCTION max_hour_work()
    RETURNS TRIGGER AS
$$
DECLARE
    max_hrs NUMERIC;
BEGIN
    SELECT max_hours INTO max_hrs FROM worktype WHERE wid = NEW.wid;
    IF NEW.HOURS > max_hrs THEN
        RETURN (NEW.pid, NEW.eid, NEW.wid, max_hrs);
    ELSE
        RETURN NEW;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER hours_max
    BEFORE INSERT OR UPDATE
    ON works
    FOR EACH ROW
EXECUTE FUNCTION max_hour_work();



CREATE OR REPLACE FUNCTION wid_check()
    RETURNS TRIGGER AS
$$
DECLARE
BEGIN
    RAISE NOTICE 'some user tried to';
    RAISE NOTICE 'modify/delete default';
    RAISE NOTICE 'work type';
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_wid
    BEFORE UPDATE OR DELETE
    ON WorkType
    FOR EACH ROW
    WHEN (OLD.wid = 0)
EXECUTE FUNCTION wid_check();
