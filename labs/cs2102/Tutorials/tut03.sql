
-- 1a
SELECT distinct s.department
FROM student s;

-- Print the different departments in which students are enrolled
select distinct department
from student;

-- Email of students who borrowed or lent copy before uni
SELECT DISTINCT s.email
FROM student s, loan l
WHERE (s.email = l.borrower OR s.email = l.owner) AND s.year > l.borrowed;

-- For each copy borrowed / returned, print duration. Order in ASC ISBN13 and desc duration
SELECT l.returned - l.borrowed as duration, l.book
FROM loan l
WHERE returned IS NOT NULL
order by l.book ASC, duration DESC;

-- For each book published by wiley that has not been returned ,print title, name, faculty, (name, faculty of borrower)
select b.title,
       owner.name,
       owner.faculty,
       borrower.name,
       borrower.faculty
from loan l,
     book b,
     student owner, student borrower
WHERE b.isbn13 = l.book
  AND owner.email = l.owner
  AND borrower.email = l.borrower
  AND b.publisher = 'Wiley'
  AND  returned is null;

-- For each book published by wiley that has not been returned ,print title, name, faculty, (name, faculty of borrower)select b.title, owner.name, owner.faculty, borrower.name, borrower.faculty
SELECT b.title,
       owner.name,
       owner.faculty,
       borrower.name,
       borrower.faculty
FROM loan l
INNER JOIN book b on b.isbn13 = l.book
INNER JOIN student owner on owner.email = l.owner
INNER JOIN student borrower on borrower.email = l.borrower
WHERE b.publisher = 'Wiley'
AND returned is null;


-- Print email of different studnt who borrowed or lent a book on the day they joined uni
SELECT s.email
FROM loan l, student s
WHERE s.email = l.borrower AND l.borrowed = s.year
UNION
SELECT s.email
FROM loan l, student s
WHERE s.email = l.owner AND l.borrowed = s.year;

-- OR
SELECT DISTINCT s.email
FROM loan l, student s
WHERE (s.email = l.borrower OR s.email = l.owner)
AND l.borrowed = s.year;

-- Print email of different studnt who borrowed or lent a book on the day they joined uni
SELECT s.email
FROM loan l, student s
WHERE s.email = l.borrower AND l.borrowed = s.year
INTERSECT
SELECT s.email
FROM loan l, student s
WHERE s.email = l.owner AND l.borrowed = s.year;
-- Print the emails of the students who borrowed but did not lend a copy of a book on the day that they joined the university
SELECT s.email
FROM loan l, student s
WHERE s.email = l.borrower AND l.borrowed = s.year
EXCEPT
SELECT s.email
FROM loan l, student s
WHERE s.email = l.owner AND l.borrowed = s.year;
-- Print the ISBN13 of the books (not the copies) that have never been borrowed
SELECT b.ISBN13
FROM book b
EXCEPT
SELECT l.book
FROM loan l;

SELECT b.ISBN13
FROM book b LEFT OUTER JOIN loan l ON b.isbn13 = l.book
WHERE l.book IS NULL;
