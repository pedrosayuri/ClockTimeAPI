-- ./sql/init.sql
-- psql -h localhost -U admin -d clock_time_api -f ./sql/init.sql

CREATE TABLE IF NOT EXISTS job (
    id SERIAL PRIMARY KEY,
    description_job VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO job (description_job, created_at) VALUES ('ADMIN_1', NOW());
INSERT INTO job (description_job, created_at) VALUES ('ADMIN_2', NOW());
INSERT INTO job (description_job, created_at) VALUES ('WORKER_1', NOW());
INSERT INTO job (description_job, created_at) VALUES ('WORKER_2', NOW());