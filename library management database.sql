CREATE DATABASE library_db;

USE library_db;

CREATE TABLE books (
    book_id VARCHAR(20) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    is_available BOOLEAN NOT NULL
);
