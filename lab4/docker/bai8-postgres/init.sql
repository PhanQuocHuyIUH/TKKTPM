-- This script runs automatically on first container startup
-- against the database specified by POSTGRES_DB

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id        SERIAL PRIMARY KEY,
    username  VARCHAR(50)  NOT NULL UNIQUE,
    email     VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create products table
CREATE TABLE IF NOT EXISTS products (
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100)    NOT NULL,
    price      DECIMAL(10, 2)  NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Seed initial data
INSERT INTO users (username, email) VALUES
    ('admin',  'admin@example.com'),
    ('user1',  'user1@example.com'),
    ('user2',  'user2@example.com');

INSERT INTO products (name, price) VALUES
    ('Product A', 29.99),
    ('Product B', 49.99),
    ('Product C', 9.99);
