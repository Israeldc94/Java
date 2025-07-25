-- select *
-- from movies

-- SELECT title, imdb_rating
-- FROM movies
-- WHERE imdb_rating > 8.5 AND duration > 150

-- SELECT *
-- FROM movies
-- ORDER BY title DESC

-- SELECT movieid, title as 'Movie title', year(release_date) as release_year
-- FROM movies

-- SELECT *
-- FROM movies
-- WHERE Year(release_date) > 2005

-- SELECT *
-- FROM movies
-- WHERE title LIKE '%the%' 

-- INSERT INTO movies (title)
-- VALUES ('My Future Cool Movie')

-- SELECT *
-- FROM movies
-- WHERE release_date IS NULL

-- SELECT title, release_date, duration
-- FROM movies
-- WHERE release_date BETWEEN '2000-01-01' AND '2010-12-31'

-- SELECT title, release_date
-- FROM movies
-- WHERE Month(release_date) = 6

-- SELECT *
-- FROM movies
-- WHERE duration > 140
-- ORDER BY imdb_rating DESC

-- SELECT *, Year(release_date) as 'Year'
-- FROM Movies
-- WHERE release_date IS NOT NULL
-- ORDER BY Year(release_date), title

-- SELECT *
-- FROM movies
-- WHERE (release_date BETWEEN '1990-01-01' AND '1999-12-31') OR 
-- (release_date BETWEEN '2010-01-01' AND '2019-12-31')
