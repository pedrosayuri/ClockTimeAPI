-- ./sql/init.sql
-- psql -h localhost -U admin -d clock_time_api -f ./sql/init.sql

INSERT INTO job (description_job, created_at) VALUES ('ADMIN_1', NOW());
INSERT INTO job (description_job, created_at) VALUES ('ADMIN_2', NOW());
INSERT INTO job (description_job, created_at) VALUES ('WORKER_1', NOW());
INSERT INTO job (description_job, created_at) VALUES ('WORKER_2', NOW());