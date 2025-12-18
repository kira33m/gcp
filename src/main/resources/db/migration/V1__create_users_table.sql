CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(50),
    email VARCHAR(255) UNIQUE,
    pinfl VARCHAR(50) NOT NULL UNIQUE,
    age INTEGER,
    gender VARCHAR(20),
    document_type VARCHAR(50) NOT NULL,
    photo VARCHAR(500),
    issue_date DATE,
    expiry_date DATE,
    citizenship VARCHAR(100)
);

CREATE INDEX idx_users_pinfl ON users(pinfl);
CREATE INDEX idx_users_email ON users(email);