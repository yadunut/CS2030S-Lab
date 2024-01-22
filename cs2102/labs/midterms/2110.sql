-- Q1
SELECT c.name
FROM countries c
WHERE c.population > 100000000
AND c.continent = 'Africa';

SELECT c.continent, COUNT(*)
FROM countries c
WHERE NOT EXISTS (
    SELECT 1
    FROM airports a
    WHERE c.iso2 = a.country_iso2
    )
GROUP BY c.continent;

SELECT c.name, COUNT(*)
FROM borders b, countries c
WHERE c.iso2 = b.country1_iso2
GROUP BY c.iso2
ORDER BY COUNT(*) DESC
LIMIT 10;

SELECT b.country1_iso2, b.country2_iso2
FROM borders b, countries c1, countries c2
WHERE b.country1_iso2 = c1.iso2
AND b.country2_iso2 = c2.iso2
AND c1.continent = 'Asia' AND c2.continent = 'Europe';

SELECT c.name
FROM countries c
WHERE NOT EXISTS(
    SELECT *
    FROM routes r, airports a, countries c1
    WHERE r.airline_code = 'SQ'
    AND c1.continent = 'Asia'
    AND r.to_code = a.code
    AND c1.iso2 = a.country_iso2
) AND c.continent = 'Asia';