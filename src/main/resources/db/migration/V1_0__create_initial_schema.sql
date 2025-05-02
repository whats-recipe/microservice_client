-- Remover a criação explícita do schema, pois configuramos no flyway.schemas
-- CREATE SCHEMA IF NOT EXISTS user_service;

CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
                             );

CREATE TABLE IF NOT EXISTS country (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(100) UNIQUE NOT NULL,
    code VARCHAR(2) UNIQUE NOT NULL
    );

INSERT INTO country (name, code) VALUES
                                     ('Portugal', 'PT'),
                                     ('Spain', 'ES'),
                                     ('France', 'FR');