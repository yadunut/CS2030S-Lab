-- Q1 (Correct)
-- Which riders ride for teams of their own country?
SELECT r.bib AS bib,
       r.name AS name,
       t.name AS team_name,
       t.country AS country
FROM riders r,
     teams t
WHERE r.team = t.name
  AND t.country = r.country;

-- Q2 (Correct)
-- Which stages started and ended in different countries?
SELECT s.nr AS stage_nr,
       l1.country as cc_start,
       l2.country as cc_end
FROM stages s,
     locations l1,
     locations l2
WHERE s.start = l1.name
  AND s.finish = l2.name
  AND l1.country != l2.country;

-- Q3 (Correct)
-- Which stages featured the highest mountains? Sort from highest to lowest max_height
SELECT m.stage AS stage_nr,
       MAX(m.height) AS max_height
FROM mountains m
GROUP BY stage_nr
ORDER BY max_height DESC;

-- Q4
-- Which stages ended with a mountain finish?
SELECT s.nr as stage_nr,
       m.category AS category,
       m.percent AS percent
FROM stages s,
     mountains m
WHERE m.location LIKE concat('%', s.finish, '%')
ORDER BY category DESC,
         percent DESC;

-- Q4 (CORRECT)
SELECT
    t.nr AS stage_nr,
    CASE
        WHEN t.category = 5 THEN 'H'
        ELSE t.category::TEXT
        END AS category,
    t.percent AS percent
FROM (
         SELECT
             nr,
             CASE
                 WHEN m.category = 'H' THEN 5
                 ELSE m.category::integer
                 END as category,
             m.percent
         FROM stages s, mountains m
         WHERE s.nr = m.stage
           AND s.length = m.distance
         ORDER BY m.category DESC, m.percent DESC
     ) t;

-- Q5 (Correct)
SELECT COUNT(*) AS num_stages
FROM stages s1,
     stages s2
WHERE s1.type = 'flat'
  AND s2.nr = s1.nr + 1
  AND s2.type = 'hilly';

-- Q6
SELECT r.name AS name,
       SUM(rs.time - rs.bonus + rs.penalty) AS total_time
FROM riders r,
     results_individual rs
WHERE r.bib = rs.rider
GROUP BY r.bib
HAVING COUNT(rs.rider) = ALL (SELECT COUNT(*) from stages)
ORDER BY total_time ASC
LIMIT 10;

-- Q6 (CORRECT)
SELECT r.name AS name,
       SUM(rs.time - rs.bonus + rs.penalty) AS total_time
FROM riders r,
     results_individual rs
WHERE r.bib = rs.rider
  AND r.bib IN (
    SELECT rider
    FROM results_individual
    WHERE stage = (SELECT MAX(nr) FROM stages)
)
GROUP BY r.bib
ORDER BY total_time ASC
LIMIT 10;



-- Q7 (Correct)
SELECT r.name AS name,
       ROUND(SUM(s.length)/ (SUM(rs.time::NUMERIC) / 3600.00), 2) as avg_speed
FROM riders r,
     results_individual rs,
     stages s
WHERE r.bib = rs.rider
  AND s.nr = rs.stage
GROUP BY r.bib
ORDER BY avg_speed desc;

-- Q8
WITH CumulativePoints AS (
    SELECT r.name AS name,
           s.day AS race_day,
           s.nr AS stage,
           SUM(COALESCE(rs.points, 0))
           OVER (PARTITION BY r.bib ORDER BY s.nr) AS points
    FROM stages s
             CROSS JOIN riders r
             LEFT JOIN results_sprints rs ON rs.stage = s.nr AND r.bib = rs.rider
)
SELECT DISTINCT cp.race_day,cp.name
FROM CumulativePoints cp
WHERE cp.points >= ALL (
    SELECT MAX(cp1.points)
    FROM CumulativePoints cp1
    WHERE cp1.stage = cp.stage
    GROUP BY cp1.stage
)
ORDER BY cp.race_day;

-- Q8 (Correct)
SELECT s.day AS race_day, r.name
FROM riders r, results_sprints rs, stages s
WHERE r.bib = rs.rider
AND rs.stage <= s.nr
GROUP BY r.bib, r.name, s.nr
HAVING SUM(rs.points) >= ALL(
    SELECT SUM(rs1.points)
    FROM riders r1, results_sprints rs1, stages s1
    WHERE r1.bib = rs1.rider
    AND rs1.stage <= s1.nr
    AND s1.nr = s.nr
    GROUP BY r1.bib, s1.nr
    )
ORDER BY s.nr;

-- Q9
SELECT s.day AS race_day
FROM
    results_individual ri,
    riders r,
    stages s
WHERE ri.rider = r.bib
  AND ri.stage = s.nr
  AND ri.rank <=50
GROUP BY s.day
HAVING COUNT(DISTINCT r.team) = (SELECT COUNT(*) from teams)
ORDER BY race_day;

-- Q9 CORRECT
SELECT s.day AS race_day
FROM stages s
WHERE NOT EXISTS(
    SELECT *
    FROM teams t
    WHERE NOT EXISTS(
        SELECT *
        FROM results_individual ri, riders r
        WHERE t.name = r.team
          AND r.bib = ri.rider
          AND ri.rank <= 50
          AND ri.stage = s.nr
    )
);

-- Q10
WITH ranked AS (
    SELECT r.team,
           SUM(ri.time + ri.penalty - ri.bonus) AS cum_time,
           row_number() over (partition by r.team order by SUM(ri.time + ri.penalty - ri.bonus) asc) as rank
    FROM riders r, results_individual ri
    WHERE ri.rider = r.bib
    group by r.team, r.name
    having COUNT(*) = (SELECT COUNT(*) FROM stages)
)
SELECT ranked.team AS team_name
FROM ranked
WHERE ranked.rank < 4
GROUP BY ranked.team
ORDER BY AVG(ranked.cum_time) ASC
LIMIT 1;

-- Q10 (Correct)
SELECT r.team AS team_name
FROM (
         SELECT r.bib, r.team, r.sum,
                row_number() OVER (PARTITION BY r.team ORDER BY r.sum ASC) AS row
         FROM (SELECT r1.bib, r1.team, SUM(ri.time + ri.bonus - ri.penalty) AS sum
               FROM riders r1, results_individual ri
               WHERE r1.bib = ri.rider
               GROUP BY r1.bib
               HAVING COUNT(*) = ALL (SELECT COUNT(*) FROM stages)) as r
     ) AS r
WHERE r.row < 4
GROUP BY r.team
ORDER BY SUM(r.sum) ASC
LIMIT 1;