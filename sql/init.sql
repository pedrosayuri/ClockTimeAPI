-- ./sql/init.sql
-- psql -h localhost -U admin -d clock_time_api -f ./sql/init.sql

DROP TABLE IF EXISTS job CASCADE;

CREATE TABLE job (
    id SERIAL PRIMARY KEY,
    description_job VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO job (description_job, created_at) VALUES ('ADMIN_1', NOW());
INSERT INTO job (description_job, created_at) VALUES ('ADMIN_2', NOW());
INSERT INTO job (description_job, created_at) VALUES ('WORKER_1', NOW());
INSERT INTO job (description_job, created_at) VALUES ('WORKER_2', NOW());

DROP TABLE IF EXISTS employees CASCADE;

CREATE TABLE employees (
	id serial4 NOT NULL PRIMARY KEY,
	created_at timestamp(6) NULL,
	email varchar(255) NULL,
	hire_date timestamp(6) NOT NULL,
	hourly_rate float8 NULL,
	"name" varchar(255) NULL,
	uid varchar(255) NULL,
	job_id int4 NOT NULL
);

INSERT INTO employees
(created_at, email, hire_date, hourly_rate, "name", uid, job_id)
VALUES(NOW(), 'adm@adm.com', NOW(), 0, 'Administrator', '', 2);

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
	id serial4 NOT NULL PRIMARY KEY,
	created_at timestamp(6) NULL,
	"login" varchar(255) NULL,
	"password" varchar(100) NULL,
	employee_id int4 NOT NULL
);

INSERT INTO users
(created_at, login, "password", employee_id)
VALUES(NOW(),'admin', 'admin', 1);
