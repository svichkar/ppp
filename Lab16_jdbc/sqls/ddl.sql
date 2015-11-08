CREATE TABLE student
(
student_id BIGINT IDENTITY,
first_name VARCHAR(256) NOT NULL,
last_name VARCHAR(256) NOT NULL,
date_birthday DATE NOT NULL,
date_entry DATE NOT NULL,
student_group_id BIGINT NOT NULL,
term_id BIGINT NOT NULL,
status_id BIGINT NOT NULL,
);

CREATE TABLE student_group
(
student_group_id BIGINT IDENTITY,
student_group VARCHAR(256) NOT NULL,
);

CREATE TABLE term
(
term_id BIGINT IDENTITY,
alias VARCHAR(256) NOT NULL,
);

CREATE TABLE status
(
status_id BIGINT IDENTITY,
status_name VARCHAR(256) NOT NULL,
);

CREATE TABLE subject
(
subject_id BIGINT IDENTITY,
name VARCHAR(256) NOT NULL,
term_id BIGINT REFERENCES term(term_id)
);

CREATE TABLE journal
(
journal_id BIGINT IDENTITY,
student_id BIGINT REFERENCES student(student_id),
subject_id BIGINT REFERENCES subject(subject_id),
rate VARCHAR(256) NOT NULL
);

ALTER TABLE student 
ADD FOREIGN KEY (student_group_id) 
REFERENCES student_group(student_group_id);

ALTER TABLE student 
ADD FOREIGN KEY (term_id) 
REFERENCES term(term_id);

ALTER TABLE student 
ADD FOREIGN KEY (status_id) 
REFERENCES status(status_id);