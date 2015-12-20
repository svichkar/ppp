--Creating table with all references
CREATE TABLE student_group(group_id BIGINT PRIMARY KEY, group_name VARCHAR(256) NOT NULL);
CREATE TABLE status (status_id TINYINT PRIMARY KEY, status_name VARCHAR(256) NOT NULL);
CREATE TABLE term(term_id BIGINT PRIMARY KEY);
ALTER TABLE term ADD term_name VARCHAR(256) NOT NULL;
CREATE TABLE student (student_id BIGINT IDENTITY PRIMARY KEY,
                 first_name VARCHAR(256) NOT NULL,
                 last_name VARCHAR(256) NOT NULL,
                 admission_date DATE,
                 group_id BIGINT NOT NULL REFERENCES student_group(group_id),
                 status_id TINYINT NOT NULL REFERENCES status(status_id),
                 term_id BIGINT REFERENCES term(term_id)
);
ALTER TABLE student ALTER COLUMN admission_date SET NOT NULL;
-- Create index for accelerating search by student's last name 
CREATE INDEX last_name_indx ON student(last_name);
CREATE TABLE grade(grade_id TINYINT PRIMARY KEY, grade_name VARCHAR(256) NOT NULL);
CREATE TABLE subject(subject_id BIGINT PRIMARY KEY, 
                  subject_name VARCHAR(256) NOT NULL,
                  term_id BIGINT REFERENCES term(term_id));
-- Create index for accelerating search by subject name 
CREATE INDEX subject_indx ON subject(subject_name);
CREATE TABLE journal(journal_id BIGINT IDENTITY PRIMARY KEY, 
                  student_id BIGINT REFERENCES student(student_id),
                  subject_id BIGINT REFERENCES subject(subject_id),
                  grade_id TINYINT REFERENCES grade(grade_id));