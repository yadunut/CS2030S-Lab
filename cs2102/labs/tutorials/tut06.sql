DROP TABLE IF EXISTS exams;
CREATE TABLE IF NOT EXISTS
    exams
(
    SID   INT,
    CID   INT,
    score INT,
    PRIMARY KEY (SID, CID)
);

CREATE OR REPLACE FUNCTION max_min(IN stu_id INT, OUT max_cid INT, OUT min_cid INT)
    RETURNS RECORD AS
$$
DECLARE
    max_score INT; min_score INT;
BEGIN
    SELECT e.score, e.cid
    INTO max_cid, max_score
    FROM exams e
    WHERE e.sid = stu_id
      AND e.score = (SELECT MAX(score) from exams);

    SELECT e.score, e.cid
    INTO min_cid, min_score
    FROM exams e
    WHERE e.sid = stu_id
      AND e.score = (SELECT MIN(score) from exams);
    IF min_score = max_score THEN
        min_cid := NULL;
    end if;
/* write your code here */
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION revised_avg(IN stu_id INT, OUT r_avg FLOAT)
    RETURNS FLOAT AS
$$
DECLARE
    max_score   INT;
    min_score   INT;
    count_score INT;
    sum_score   INT;
BEGIN
    SELECT MAX(score), MIN(score), COUNT(score), SUM(score)
    INTO max_score, min_score, count_score, sum_score
    FROM exams;

    IF count_score < 3 THEN
        r_avg := NULL;
    ELSE
        r_avg := (sum_score - min_score - max_score) / (count_score - 2);
    END IF;
END;
/* write your code here */
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION list_r_avg()
    RETURNS TABLE
            (
                stu_id INT,
                ravg   FLOAT
            )
AS
$$
DECLARE
    curs CURSOR FOR (SELECT sid, score
                     FROM Exams
                     ORDER BY sid);
    curr        RECORD;
    sum_score   INT;
    count_score INT;
    max_score   INT;
    min_score   INT;
/* add other variables here */
BEGIN
    --     to validate first row
    stu_id := -1;
    OPEN curs;
    LOOP
        FETCH curs INTO curr;
        IF curr.sid != stu_id OR NOT FOUND THEN
            IF stu_id != -1 THEN
                IF (count_score < 3) THEN
                    ravg := NULL;
                ELSE
                    ravg := (sum_score - max_score - min_score) / (count_score - 2);
                END IF;
                RETURN NEXT;
            END IF;
            EXIT WHEN NOT FOUND;
            stu_id := curr.sid;
            max_score := curr.score;
            min_score := curr.score;
            sum_score := curr.score;
            count_score := 1;
        ELSE
            sum_score := curr.score + sum_score;
            count_score := count_score + 1;
            IF max_score < curr.score THEN max_score := curr.score; END IF;
            IF min_score > curr.score THEN min_score := curr.score; END IF;
        END IF;
    END LOOP;
    CLOSE curs;
/* write your code here */
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION list_scnd_highest()
    RETURNS TABLE
            (
                stu_id       INT,
                scnd_highest INT
            )
AS
$$
DECLARE
    curs CURSOR FOR (SELECT sid, score
                     FROM Exams
                     ORDER BY sid);
    curr        RECORD;
    max_score   INT;
    count_score INT;
BEGIN
    stu_id := -1;
    OPEN curs;
    LOOP
        FETCH curs INTO curr;
        IF curr.sid != stu_id OR NOT FOUND THEN
            IF stu_id <> -1 THEN
                IF (count_score < 2) THEN scnd_highest := NULL; END IF;
                RETURN NEXT;
            end if;
            EXIT WHEN NOT FOUND;
            stu_id := curr.sid;
            max_score := curr.score;
            scnd_highest := -1;
            count_score := 1;
        ELSE
            IF curr.score > max_score THEN
                scnd_highest := max_score;
                max_score := curr.score;
            ELSEIF curr.score > scnd_highest THEN
                scnd_highest := curr.score;
            END IF;
            count_score := count_score + 1;
        END IF;
    END LOOP;
    CLOSE curs;
    RETURN;
END;
$$ LANGUAGE plpgsql;