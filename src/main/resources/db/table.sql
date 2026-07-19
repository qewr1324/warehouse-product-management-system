-- Active: 1780413880560@@127.0.0.1@5432@warehouse_product_management_system
CREATE DATABASE warehouse_product_management_system;

CREATE TABLE product (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(10, 2) CHECK (price >= 0),
    quantity INTEGER CHECK (quantity >= 0)
);

CREATE TABLE supplier (
    if BIGSERIAL PRIMARY KEY,
    company_name VARCHAR(50) NOT NULL,
    phone VARCHAR(11) CHECK (LENGTH(phone) = 11)
);
