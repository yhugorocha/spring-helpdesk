CREATE TABLE ticket_attachments (
     id UUID PRIMARY KEY,
     ticket_id UUID NULL,
     ticket_interaction_id UUID NULL,
     filename VARCHAR(255) NOT NULL,
     url VARCHAR(255) NOT NULL,
     created_at TIMESTAMP NOT NULL,
     created_by UUID NOT NULL,
     update_at TIMESTAMP NULL,
     update_by UUID NULL
);