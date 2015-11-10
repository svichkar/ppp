CREATE TABLE student (
student_id BIGINT IDENTITY PRIMARY KEY,
first_name VARCHAR(256) NOT NULL,
last_name VARCHAR(256) NOT NULL,
date_birthday DATE NOT NULL,
date_entry DATE NOT NULL,
student_group_id BIGINT,
term_id BIGINT,
status_id BIGINT
);

CREATE TABLE student_group (
student_group_id BIGINT IDENTITY PRIMARY KEY,
group_name VARCHAR(256) NOT NULL
);

CREATE TABLE term (
term_id BIGINT IDENTITY PRIMARY KEY,
term_alias VARCHAR(256) NOT NULL
);

CREATE TABLE status (
status_id BIGINT IDENTITY PRIMARY KEY,
status_name VARCHAR(256) NOT NULL
);

CREATE TABLE subject(
subject_id BIGINT IDENTITY PRIMARY KEY,
subject_name VARCHAR(256) NOT NULL,
term_id BIGINT
);

CREATE TABLE journal(
journal_id BIGINT IDENTITY PRIMARY KEY,
student_id BIGINT,
subject_id BIGINT,
rate VARCHAR(128) NOT NULL
);

ALTER TABLE student
ADD FOREIGN KEY (student_group_id)
REFERENCES student_group (student_group_id);

ALTER TABLE student
ADD FOREIGN KEY (term_id)
REFERENCES term (term_id);

ALTER TABLE student
ADD FOREIGN KEY (status_id)
REFERENCES status (status_id);

ALTER TABLE subject
ADD FOREIGN KEY (term_id)
REFERENCES term (term_id);

ALTER TABLE journal
ADD FOREIGN KEY (student_id)
REFERENCES student (student_id);

ALTER TABLE journal
ADD FOREIGN KEY (subject_id)
REFERENCES subject (subject_id);
