SELECT * FROM book;
SELECT * FROM book WHERE author = Eckel;
SELECT name FROM book;
SELECT COUNT(*) FROM book;
SELECT DISTINCT name FROM book;

SELECT * FROM book WHERE category_id in (1,3);
SELECT * FROM book WHERE category_id > 1;
SELECT * FROM book WHERE author LIKE 'Eck%';
SELECT * FROM journal WHERE end_date < '2020-09-17';
SELECT *  FROM reader WHERE adress IS NOT NULL;
SELECT * FROM journal WHERE start_date BETWEEN '2012-01-01' AND '2014-01-01';
SELECT * FROM book ORDER BY name ASC;
SELECT * FROM book  UNION ALL SELECT * FROM book WHERE author = 'Shildt';

SELECT b.name, b.author, b.publisher, cat.name as category_name FROM book b JOIN category cat ON b.category_id=cat.id;
SELECT DATEDIFF(day,start_date,CASE WHEN end_date IS NULL THEN NOW() ELSE end_date END CASE) AS count_OF_DAYS_IN_USAGE FROM journal; 
SELECT b.name, b.author, b.publisher, cat.name as category_name FROM book b JOIN category cat ON b.category_id=cat.id GROUP BY b.id HAVING b.category_id>1'
