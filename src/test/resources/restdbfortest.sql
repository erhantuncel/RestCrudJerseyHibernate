
DROP TABLE IF EXISTS staffs;
DROP TABLE IF EXISTS departments;
DROP SEQUENCE IF EXISTS staffs_id_seq;
DROP SEQUENCE IF EXISTS departments_id_seq;
CREATE TABLE departments (
	id serial,
	department_name VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE staffs (
	id serial,
	first_name VARCHAR(40) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
	phone VARCHAR(10) NOT NULL,
	email VARCHAR(40),
	department_id int NOT NULL,
	registered_time timestamp,
	PRIMARY KEY (id),
	FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE CASCADE
);

INSERT INTO departments (department_name) VALUES ('Üretim');
INSERT INTO departments (department_name) VALUES ('AR-GE');
INSERT INTO departments (department_name) VALUES ('Pazarlama');

INSERT INTO staffs (first_name, last_name, phone, department_id, registered_time) VALUES ('Simge', 'CİĞERLİOĞLU', '6279548733', 1, '2019-06-28 16:18:15');
INSERT INTO staffs (first_name, last_name, phone, email, department_id, registered_time) VALUES ('Arzu', 'BULGUR', '1283663610', 'arzu@abc.com', 2, '2019-06-20 18:35:52');
INSERT INTO staffs (first_name, last_name, phone, department_id, registered_time) VALUES ('Emre', 'BİNBAY', '7543118133', 2, '2019-06-15 12:31:03');

