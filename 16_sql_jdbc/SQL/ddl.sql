CREATE TABLE student_group
  (
    group_id BIGINT PRIMARY KEY,
    group_name VARCHAR(256) NOT NULL
  );
CREATE TABLE status
  (
    status_id TINYINT PRIMARY KEY,
    status_name VARCHAR(256) NOT NULL
  );
CREATE TABLE term
  (
    term_id BIGINT PRIMARY KEY,
    term_name VARCHAR(256) NOT NULL
  );
CREATE TABLE student
  (
    student_id BIGINT IDENTITY PRIMARY KEY,
    first_name VARCHAR(256) NOT NULL,
    last_name  VARCHAR(256) NOT NULL,
    group_id BIGINT NOT NULL,
    status_id TINYINT NOT NULL,
    term_id BIGINT,
    FOREIGN KEY (group_id) REFERENCES student_group (group_id),
    FOREIGN KEY (status_id) REFERENCES status (status_id),
    FOREIGN KEY (term_id) REFERENCES term (term_id)
  );
ALTER TABLE student ADD COLUMN admission_date VARCHAR(256);
ALTER TABLE student ALTER COLUMN admission_date DATE NOT NULL;
CREATE TABLE subject
  (
    subject_id BIGINT PRIMARY KEY,
    subject_name VARCHAR(256),
    term_id BIGINT,
    FOREIGN KEY (term_id) REFERENCES term (term_id)
  );
CREATE TABLE grades
  (
    grade_id TINYINT PRIMARY KEY,
    grade_name VARCHAR(256) NOT NULL
  );

ALTER TABLE grades RENAME TO grade;
CREATE TABLE journal
  (
    journal_id BIGINT IDENTITY PRIMARY KEY,
    student_id BIGINT,
    subject_id BIGINT,
    grade_id TINYINT,
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    FOREIGN KEY (subject_id) REFERENCES subject (subject_id),
    FOREIGN KEY (grade_id) REFERENCES grade (grade_id),    
  );