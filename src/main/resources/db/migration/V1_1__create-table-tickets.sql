CREATE TABLE tickets (
     id UUID PRIMARY KEY,
     support_user_id UUID NULL,
     subject VARCHAR(255) NOT NULL,
     description text NOT NULL,
     status VARCHAR(255) NOT NULL,
     created_at TIMESTAMP NOT NULL,
     created_by UUID NOT NULL,
     update_at TIMESTAMP NULL,
     update_by UUID NULL
);