CREATE TABLE dentist_visit (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
dentist_name VARCHAR(50) NOT NULL,
patient_name VARCHAR(50) NOT NULL,
visit_date DATE NOT NULL,
visit_time TIME NOT NULL
);

CREATE TABLE possible_dentist_visit (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
dentist_name VARCHAR(50) NOT NULL,
visit_date DATE NOT NULL,
visit_time TIME NOT NULL
);

INSERT INTO possible_dentist_visit (dentist_name, visit_date, visit_time) VALUES ('Arst1', DATEADD('day', 1, curdate()), DATEADD('hour', 1, now()));
INSERT INTO possible_dentist_visit (dentist_name, visit_date, visit_time) VALUES ('Arst2', DATEADD('day', 2, curdate()), DATEADD('hour', 2, now()));
INSERT INTO possible_dentist_visit (dentist_name, visit_date, visit_time) VALUES ('Arst3', DATEADD('day', 3, curdate()), DATEADD('hour', 3, now()));

INSERT INTO possible_dentist_visit (dentist_name, visit_date, visit_time) VALUES ('Arst4', DATEADD('day', 4, curdate()), DATEADD('hour', 4, now()));
INSERT INTO possible_dentist_visit (dentist_name, visit_date, visit_time) VALUES ('Arst5', DATEADD('day', 5, curdate()), DATEADD('hour', 5, now()));
INSERT INTO possible_dentist_visit (dentist_name, visit_date, visit_time) VALUES ('Arst6', DATEADD('day', 6, curdate()), DATEADD('hour', 6, now()));

INSERT INTO possible_dentist_visit (dentist_name, visit_date, visit_time) VALUES ('Arst7', DATEADD('day', 7, curdate()), DATEADD('hour', 7, now()));
INSERT INTO possible_dentist_visit (dentist_name, visit_date, visit_time) VALUES ('Arst8', DATEADD('day', 8, curdate()), DATEADD('hour', 8, now()));
INSERT INTO possible_dentist_visit (dentist_name, visit_date, visit_time) VALUES ('Arst9', DATEADD('day', 9, curdate()), DATEADD('hour', 9, now()));
