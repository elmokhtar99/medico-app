CREATE SEQUENCE DOCTOR_id_seq;
CREATE TABLE DOCTOR (
                                 id BIGINT DEFAULT nextval('DOCTOR_id_seq') PRIMARY KEY
);