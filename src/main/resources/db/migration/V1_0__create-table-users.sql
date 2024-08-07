CREATE TABLE users (
     id UUID PRIMARY KEY,
     username VARCHAR(255) UNIQUE NOT NULL,
     password VARCHAR(255) NOT NULL,
     name VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL,
     is_active BOOLEAN NOT NULL,
     created_at TIMESTAMP NOT NULL,
     created_by UUID NULL,
     update_at TIMESTAMP NULL,
     update_by UUID NULL
);