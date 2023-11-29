SELECT r.bib, s.nr, SUM(rs.points)
FROM riders r, results_sprints rs, stages s
WHERE r.bib = rs.rider
AND rs.stage <= s.nr
GROUP BY r.bib, s.nr
ORDER BY r.bib ASC, s.nr ASC;

-- Q9
SELECT SUM(s.length)
FROM stages s
WHERE s.nr <=10;

-- Q10
SELECT DISTINCT l.name, l.country
FROM stages s, locations l
WHERE s.start = l.name
OR s.finish = l.name;

SELECT l.name, l.country
FROM locations l
WHERE l.name IN (SELECT s.start FROM stages s)
OR l.name IN (SELECT s.finish FROM stages s);

-- Q11
SELECT r.bib, r.name
FROM riders r
WHERE r.bib NOT IN (
    SELECT ri.rider
    FROM results_individual ri
    WHERE stage = 1
);

-- Q12
SELECT r.bib, r.name
FROM riders r
EXCEPT
SELECT r.bib, r.name
FROM riders r, results_individual ri
WHERE r.bib = ri.rider
AND ri.stage = 1;

-- Q13
SELECT r.bib, r.name
FROM riders r
WHERE NOT EXISTS (
    SELECT 1
    FROM results_individual ri
    WHERE stage = 1
    AND r.bib = ri.rider
);
-- Q14
SELECT *
FROM riders r
LEFT OUTER JOIN results_individual ri on ri.rider = r.bib
WHERE ri.rider ISNULL;

-- Q15


-- Simple
-- Algebriaic
-- aggregate
-- nested

-- Q16
SELECT r.name, s.nr
FROM stages s, riders r
WHERE r.bib NOT IN (
    SELECT ri.rider
    FROM results_individual ri
    WHERE stage = s.nr
    AND r.bib = ri.rider
)
ORDER BY s.nr ASC, r.name ASC;

SELECT *
FROM riders r, results_individual ri
WHERE r.bib = ri.rider
GROUP BY

-- Q17
SELECT *
FROM stages s
LEFT OUTER JOIN mountains m ON s.nr = m.stage
AND m.category = '3'
ORDER BY s.nr ASC



-- SELECT DISTINCT
-- WHERE ALL
-- WHERE IN
-- WHERE NOT IN
-- WHERE EXISTS
-- WHERE NOT EXISTS
-- DOUBLE WHERE NOT EXISTS (WHERE ALL)
-- Select top N by category
--
