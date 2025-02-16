CREATE DATABASE transfers;
GRANT ALL PRIVILEGES ON DATABASE transfers TO admin;
\connect transfers;
CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE public.enterprises (
	id SERIAL,
	cuit varchar NOT NULL,
	company_name varchar NOT NULL,
	accession_date varchar NOT NULL,
	CONSTRAINT enterprises_pk PRIMARY KEY (id)
);

CREATE TABLE public.transfers (
	id SERIAL,
	amount float NULL,
	credit_account varchar NOT NULL,
	debit_account varchar NOT NULL,
	enterprises_pk bigint NOT NULL,
	CONSTRAINT transfers_pk PRIMARY KEY (id),
	CONSTRAINT enterprises_fk FOREIGN KEY (enterprises_pk) REFERENCES public.enterprises(id) ON DELETE CASCADE ON UPDATE CASCADE
);