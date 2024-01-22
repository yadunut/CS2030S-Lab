INSERT INTO book VALUES (
'An Introduction to Database Systems',
'paperback' , 
640 , 
'English' , 
'C. J. Date' , 
'Pearson',
'0321197844' , 
'978-0321197849');


INSERT INTO student VALUES (
'TIKKI TAVI' , 
'tikki@gmail.com' , 
'2021-08-01', 
'School of Computing', 
'CS', 
NULL);

INSERT INTO student (email, name, year, faculty, department)
VALUES (
'rikki@gmail.com', 
'RIKKI TAVI', 
'2021-08-01', 
'School of Computing', 
'CS');


UPDATE student
SET department = 'Computer Science'
WHERE department = 'CS';

-- Does not work as students references loans
-- DELETE FROM student WHERE department = 'Chemistry';

-- remove foreign key constraint
ALTER TABLE loan DROP CONSTRAINT loan_borrower_fkey;
-- add new foreign key constraint
ALTER TABLE loan ADD CONSTRAINT loan_borrower_fkey
FOREIGN KEY (borrower) REFERENCES student(email) ON DELETE CASCADE DEFERRABLE INITIALLY IMMEDIATE ;
DELETE FROM student where department = 'Chemistry';

INSERT INTO copy
VALUES (
'tikki@gmail.com', 
'978-0321197849', 
1);

BEGIN TRANSACTION;
SET CONSTRAINTS ALL IMMEDIATE;
DELETE FROM book 
WHERE ISBN13 = '978-0321197849' ;
DELETE FROM copy 
WHERE book = '978-0321197849' ;
END TRANSACTION;


BEGIN TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;
DELETE FROM book WHERE ISBN13 = '978-0321197849' ;
DELETE FROM copy WHERE book = '978-0321197849' ;
END TRANSACTION;


ALTER TABLE copy ADD COLUMN available BOOLEAN DEFAULT true;

UPDATE copy
SET available = false
WHERE (owner, book, copy) IN (SELECT owner, book, copy FROM loan WHERE returned ISNULL);

-- Disagree, the departments and fields are not the same.