-- How many loans involve
SELECT b.title
FROM book b
WHERE b.isbn13 != ALL (
    SELECT l.book FROM loan l
);
-- 2b
SELECT s.name
FROM student s
WHERE s.email = ANY (
    SELECT c.owner
    FROM copy c
    WHERE (c.owner, c.book, c.copy) NOT IN (
        SELECT l.owner, l.book, l.copy
        FROM loan l
    )
);

-- 2c (When you see the most/the least, there's a standard way
-- of solving this

SELECT s.department, s.name, COUNT(*)
FROM student s, loan l
WHERE l.owner = s.email
GROUP BY s.department, s.name, s.email
HAVING COUNT(*) >= ALL (
    SELECT COUNT(*)
    FROM student s1, loan l1
    WHERE l1.owner = s1.email
      AND s.department = s1.department
    GROUP BY s1.email
)
ORDER BY COUNT(*) DESC;

-- 2d

SELECT s.name, s.email
FROM student s
WHERE s.email = ALL (
    SELECT c
    FROM book b
    WHERE b.authors LIKE '%Adam Smith%'
    )