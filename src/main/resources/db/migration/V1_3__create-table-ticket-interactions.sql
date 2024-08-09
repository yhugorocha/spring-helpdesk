CREATE TABLE ticket_interactions (
     id UUID PRIMARY KEY,
     ticket_id UUID NOT NULL,
     sent_by_user_id UUID NOT NULL,
     message TEXT NOT NULL,
     created_at TIMESTAMP NOT NULL,
     created_by UUID NOT NULL,
     update_at TIMESTAMP NULL,
     update_by UUID NULL
);