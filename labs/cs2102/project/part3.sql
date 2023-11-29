CREATE OR REPLACE PROCEDURE create_practice_quiz(
    quiz_name VARCHAR,
    creator_id VARCHAR,
    num_questions INT,
) AS
$$
DECLARE
    quiz_id INT;
    c CURSOR FOR (SELECT * FROM quiz_questions);
    quiz_qn RECORD;
    random_no INT;
    num_qns INT = (SELECT COUNT(*) FROM questions);
BEGIN
    INSERT INTO quizzes (id, name, published, total_points, avail_from, avail_to, time_limit, mandatory, status,
                         creator)
    values ((SELECT id + 1 FROM quizzes ORDER BY id DESC LIMIT 1), -- get last id
            quiz_name,
            true,
            0,
            NOW(),
            NULL,
            NULL,
            FALSE,
            'public',
            creator_id)
    RETURNING id
        INTO quiz_id;
    OPEN c;
    FOR val IN 1..num_questions LOOP
        random_no = FLOOR(random() * (num_questions));
    END LOOP;
    CLOSE c;
END;
$$
    LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE create_practice_quiz (
    quiz_name varchar,
    creator_id varchar,
    num_questions int
)
LANGUAGE plpgsql
AS $$
BEGIN
    WITH quiz AS (
        INSERT INTO quizzes (
             id,
             name,
             published,
             num_attempts,
             avail_from,
             total_points,
             mandatory,
             status,
             creator
        )
        VALUES (
            (SELECT count(*) + 1 FROM quizzes),
            quiz_name,
            true,
            0,
            now(),
            0,
            false,
            'public',
            creator_id
        )
        RETURNING
            id,
            status,
            creator
    )
    INSERT INTO quiz_questions (
        quiz_id,
        question_id,
        points,
        mandatory,
        position
    )
    SELECT
        q.id,
        qn.id,
        0,
        false,
        ROW_NUMBER() OVER (ORDER BY random())
    FROM
        questions qn,
        quiz q
    WHERE
        qn.valid = true
        AND (
            qn.status = 'public'
            OR qn.creator = q.creator
        )
    ORDER BY random()
    LIMIT num_questions;
END
$$;

SELECT FLOOR(random() * (SELECT COUNT(*) FROM questions));
