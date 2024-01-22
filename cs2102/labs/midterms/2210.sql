SELECT COUNT(*)
FROM subzones
WHERE population > 15000;

SELECT max_floor, COUNT(max_floor)
FROM hdb_blocks
GROUP BY max_floor
ORDER BY COUNT(max_floor) desc
LIMIT 1;


SELECT *
FROM areas a
WHERE region = 'central'
  AND NOT EXISTS (
    SELECT 1
    FROM mrt_stations m,subzones s
    WHERE m.subzone = s.name
      AND s.area = a.name
);

SELECT sz.area, COUNT(stop.code) AS num_stops
FROM mrt_stops stop,
     mrt_stations s,
     subzones sz
WHERE stop.station = s.name
  AND s.subzone = sz.name
GROUP BY sz.area
ORDER BY num_stops DESC
LIMIT 3;

SELECT DISTINCT m1.station
FROM mrt_connections c, mrt_stops m1, mrt_stops m2
WHERE c.from_code = m1.code
  AND c.to_code = m2.code
  AND m1.line = 'ew'
  AND m2.line != 'ew';

SELECT sz.area, COUNT(DISTINCT so.line) AS num_lines
FROM mrt_stops so,
     mrt_stations st,
     subzones sz
WHERE so.station = st.name
  AND st.subzone = sz.name
GROUP BY sz.area
HAVING COUNT(DISTINCT so.line) >= 3
ORDER BY num_lines DESC;

SELECT tab.area, COUNT(*) AS num_lines
FROM (
         SELECT s.area, h.line
         FROM mrt_stops h,
              mrt_stations m,
              subzones s
         WHERE h.station = m.name
           AND m.subzone = s.name
         GROUP BY s.area, h.line
         ) tab
GROUP BY tab.area
HAVING COUNT (*) >= 3 ORDER BY num_lines DESC