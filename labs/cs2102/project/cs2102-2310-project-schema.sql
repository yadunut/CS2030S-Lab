
DROP TABLE IF EXISTS submitted_answers;
DROP TABLE IF EXISTS submissions;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS student_groups;
DROP TABLE IF EXISTS quiz_groups;
DROP TABLE IF EXISTS quiz_questions;
DROP TABLE IF EXISTS quizzes;
DROP TABLE IF EXISTS question_tags;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS educators;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS tags;


CREATE TABLE tags (
    text VARCHAR(255) PRIMARY KEY
);

CREATE TABLE groups (
    code INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE educators (
    staff_nr VARCHAR(5) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE students (
    student_nr VARCHAR(10) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    last_active DATE
);

CREATE TABLE questions (
    id INT PRIMARY KEY,
    statement TEXT NOT NULL,
    description TEXT,    
    type VARCHAR(255) NOT NULL CHECK(type IN ('MCQ', 'MRQ')),
    status VARCHAR(255) NOT NULL CHECK(status IN ('private', 'public')),
    valid BOOLEAN NOT NULL DEFAULT FALSE,
    creator VARCHAR(10),
    FOREIGN KEY (creator) REFERENCES educators (staff_nr)
);

CREATE TABLE question_tags (
    question_id INT,
    text VARCHAR(255),
    PRIMARY KEY (question_id, text),
    FOREIGN KEY (question_id) REFERENCES questions (id),
    FOREIGN KEY (text) REFERENCES tags(text)
);

CREATE TABLE quizzes (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    published BOOLEAN NOT NULL DEFAULT FALSE,
    num_attempts INT DEFAULT NULL,                         -- NULL=unlimited
    avail_from DATE DEFAULT NOW(),                         -- by default immediately available
    avail_to DATE,                                         -- NULL=no expiry date/time
    total_points INT NOT NULL DEFAULT 0,                   -- needs to be automatically set!
    time_limit INT DEFAULT NULL CHECK (time_limit > 0),    -- time limit in seconds (NULL=unlimited)
    mandatory BOOLEAN NOT NULL DEFAULT TRUE,
    status VARCHAR(255) NOT NULL CHECK(status IN ('private', 'public')),
    creator VARCHAR(10) NOT NULL,
    FOREIGN KEY (creator) REFERENCES educators (staff_nr),
    CONSTRAINT availability_check CHECK (avail_to IS NULL OR avail_to >= avail_from)
);

CREATE TABLE quiz_questions (
    quiz_id INT,
    question_id INT,
    points INT NOT NULL DEFAULT 0 CHECK (points >= 0),
    mandatory BOOLEAN NOT NULL DEFAULT TRUE,
    position INT NOT NULL,
    PRIMARY KEY (question_id, quiz_id),
    FOREIGN KEY (question_id) REFERENCES questions (id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes (id),
    CONSTRAINT unique_questions_position UNIQUE (quiz_id, position)
);

CREATE TABLE quiz_groups (
    quiz_id INT,
    group_code INT,
    PRIMARY KEY (quiz_id, group_code),
    FOREIGN KEY (quiz_id) REFERENCES quizzes (id),
    FOREIGN KEY (group_code) REFERENCES groups (code)
);

CREATE TABLE student_groups (
    student_nr VARCHAR(10),
    group_code INT,
    PRIMARY KEY (student_nr, group_code),
    FOREIGN KEY (student_nr) REFERENCES students (student_nr),
    FOREIGN KEY (group_code) REFERENCES groups (code)
);

CREATE TABLE answers (
    question_id INT,
    answer_id INT,
    content TEXT NOT NULL,
    position INT NOT NULL,
    correct BOOLEAN NOT NULL,
    PRIMARY KEY (question_id, answer_id),
    FOREIGN KEY (question_id) REFERENCES questions (id) ON DELETE CASCADE,
    CONSTRAINT unique_answer_position UNIQUE (question_id, position)
);

CREATE TABLE submissions (
    id INT PRIMARY KEY,
    quiz_id INT NOT NULL,
    student_nr VARCHAR(5) NOT NULL,
    attempt INT NOT NULL CHECK (attempt > 0),
    FOREIGN KEY (quiz_id) REFERENCES quizzes (id),
    FOREIGN KEY (student_nr) REFERENCES students (student_nr)
);

CREATE TABLE submitted_answers (
    question_id INT,
    answer_id INT,
    submission_id INT,
    PRIMARY KEY (question_id, answer_id, submission_id),
    FOREIGN KEY (question_id, answer_id) REFERENCES answers (question_id, answer_id),
    FOREIGN KEY (submission_id) REFERENCES submissions (id)
);

