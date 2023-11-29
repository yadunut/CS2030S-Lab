CREATE OR REPLACE FUNCTION max_min(IN stu_id INT, OUT max_cid INT, OUT min_cid INT)
    RETURNS RECORD AS
$$
DECLARE
    max_score INT;
    min_score INT;
BEGIN
    SELECT e.score, e.cid
    INTO max_score, max_cid
    FROM exams e
    WHERE stu_id = e.sid
      AND e.score = (SELECT MAX(score)
                     FROM exams
                     where stu_id = sid);
    SELECT e.score, e.cid
    INTO min_score, min_cid
    FROM exams e
    WHERE stu_id = e.sid
      AND e.score = (SELECT MIN(score)
                     FROM exams
                     where stu_id = sid);

    IF max_score = min_score THEN
        min_cid = NULL;
    END IF;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION revised_avg(IN stu_id INT, OUT r_avg FLOAT)
    RETURNS FLOAT AS
$$
DECLARE
    max_score   INT;
    min_score   INT;
    sum_score   FLOAT;
    count_score INT;
BEGIN
    SELECT MAX(score), MIN(score), SUM(score), COUNT(score)
    INTO max_score, min_score, sum_score, count_score
    FROM exams
    WHERE sid = stu_id;

    if count_score < 3 THEN
        r_avg := NULL;
    ELSE
        r_avg := (sum_score - max_score - min_score) / (count_score - 2);
    end if;
END;
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
    r RECORD;
/* add other variables here */
BEGIN
    /* write your code here */
    stu_id := -1;
    OPEN curs;
    LOOP
        FETCH curs INTO r;
        if r.sid <> "".stu_idOR NOT FOUND THEN

        end if;

    end loop;
    CLOSE curs;
END;
$$ LANGUAGE plpgsql;