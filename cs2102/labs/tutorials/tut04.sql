-- How many loans involve an owner and a borrower from the same department?
SELECT COUNT(*)
FROM loan l,
     student owner,
     student borrower
WHERE l.owner = owner.email
AND l.borrower = borrower.email
AND owner.department = borrower.department;

-- For each faculty, print the number of loans that involve an owner and a borrower from this faculty?
SELECT owner.department, COUNT(*)
FROM loan l,
     student owner,
     student borrower
WHERE l.owner = owner.email
  AND l.borrower = borrower.email
  AND owner.department = borrower.department
GROUP BY owner.department;

-- What are the average and the standard deviation of the duration of a loan? Round the results to the nearest integer.
SELECT ROUND(AVG(l.returned - l.borrowed+1),0), ROUND(stddev_pop(l.returned - l.borrowed+1),0)
FROM loan l;

-- (a) Print the titles of the different books that have never been borrowed. Use a nested query.

SELECT b.title
FROM book b
WHERE b.isbn13 NOT IN (
    SELECT l.book
    FROM loan l
);

-- (b) Print the name of the different students who own a copy of a book that they have never lent to anybody.
SELECT s.name
FROM student s
WHERE s.email IN (
    SELECT c.owner
    FROM copy c
    WHERE (c.owner, c.book,c.copy)NOT IN (
        SELECT l.owner,l.book,l.copy
        FROM loan l
    ) );

-- For each department, print the names of the students who lent the most.
SELECT s.name,s.department, COUNT(*)
FROM student s,loan l
WHERE l.owner = s.email
GROUP BY s.name, s.department
HAVING COUNT(*) >= ALL
(SELECT COUNT(*) FROM student s1, loan l1 WHERE l1.owner = s1.email AND s.department = s1.department GROUP BY s1.email);

-- Print email and name of different students who borrowed all books authored by adam smith
SELECT s.email, s.name
FROM student s
WHERE NOT EXISTS(
    SELECT *
    FROM book b
    WHERE b.authors = 'Adam Smith'
    AND NOT EXISTS(
        SELECT *
        FROM loan l
        WHERE l.book = b.isbn13
        AND l.borrower = s.email
    )
)