SELECT * FROM book;
SELECT * FROM book WHERE author = 'Eckel';
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
SELECT name FROM book WHERE id IN (SELECT book_id FROM book_instance GROUP BY book_id HAVING COUNT(id)>1);
SELECT MAX(count) FROM (SELECT bi.book_id, COUNT(j.book_instance_id) count FROM journal  j JOIN book_instance bi on j.book_instance_id=bi.id GROUP BY bi.book_id);

SELECT TOP 1 COUNT(book_instance_id) count FROM journal ORDER BY count DESC;
SELECT b.name, b.author, b.publisher, cat.name as category_name FROM book b JOIN category cat ON b.category_id=cat.id;
SELECT DATEDIFF(day,start_date,CASE WHEN end_date IS NULL THEN NOW() ELSE end_date END CASE) AS count_OF_DAYS_IN_USAGE FROM journal; 
SELECT b.name, b.author, b.publisher, cat.name as category_name FROM book b JOIN category cat ON b.category_id=cat.id GROUP BY b.id HAVING b.category_id>1;
SELECT TOP 1 b.id FROM book_instance bi JOIN book b ON bi.book_id = b.id LEFT JOIN journal j ON j.book_instance_id = bi.id WHERE b.name LIKE 'Refactoring'  AND (end_date IS NOT NULL OR (end_date IS NULL AND start_date IS NULL));
SELECT bi.book_id, COUNT(j.book_instance_id) count FROM journal  j JOIN book_instance bi on j.book_instance_id=bi.id GROUP BY bi.book_id  HAVING count = MAX(count);