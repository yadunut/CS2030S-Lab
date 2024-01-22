/*******************
Check database
********************/

DO
$$
DECLARE 
    table_count INTEGER = 0;
BEGIN

SELECT COUNT(*) INTO table_count FROM regions;               -- 5
IF table_count = 5 THEN
    RAISE NOTICE 'Table "regions": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "regions": FAIL (% instead of 5 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM subregions;            -- 17
IF table_count = 17 THEN
    RAISE NOTICE 'Table "subregions": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "subregions": FAIL (% instead of 17 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM countries;             -- 226
IF table_count = 226 THEN
    RAISE NOTICE 'Table "countries": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "countries": FAIL (% instead of 226 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM teams;                 -- 22
IF table_count = 22 THEN
    RAISE NOTICE 'Table "teams": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "teams": FAIL (% instead of 22 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM riders;                -- 176
IF table_count = 176 THEN
    RAISE NOTICE 'Table "riders": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "riders": FAIL (% instead of 176 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM locations;             -- 132
IF table_count = 132 THEN
    RAISE NOTICE 'Table "locations": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "locations": FAIL (% instead of 132 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM sprints;               -- 40
IF table_count = 40 THEN
    RAISE NOTICE 'Table "sprints": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "sprints": FAIL (% instead of 40 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM mountains;             -- 70
IF table_count = 70 THEN
    RAISE NOTICE 'Table "mountains": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "mountains": FAIL (% instead of 70 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM stages;                -- 21
IF table_count = 21 THEN
    RAISE NOTICE 'Table "stages": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "stages": FAIL (% instead of 21 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM results_individual;    -- 3449
IF table_count = 3449 THEN
    RAISE NOTICE 'Table "results_individual": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "results_individual": FAIL (% instead of 3449 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM results_sprints;       -- 600
IF table_count = 600 THEN
    RAISE NOTICE 'Table "results_sprints": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "results_sprints": FAIL (% instead of 600 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM results_mountains;     -- 233
IF table_count = 233 THEN
    RAISE NOTICE 'Table "results_mountains": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "results_mountains": FAIL (% instead of 233 rows)', table_count;
END IF;

SELECT COUNT(*) INTO table_count FROM results_combative;     -- 19
IF table_count = 19 THEN
    RAISE NOTICE 'Table "results_combative": OK (% rows)', table_count;
ELSE
    RAISE NOTICE 'Table "results_combative": FAIL (% instead of 19 rows)', table_count;
END IF;

END;
$$ LANGUAGE plpgsql;



