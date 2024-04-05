CREATE TABLE IF NOT EXISTS chats
(
    id           BIGSERIAL PRIMARY KEY,
    sender_id    UUID,
    recipient_id UUID,
    tender_id    BIGINT,
    created_at   TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS messages
(
    id           BIGSERIAL PRIMARY KEY,
    sender_id    UUID,
    recipient_id UUID,
    description  VARCHAR(1000),
    chat_id      BIGINT,
    status       VARCHAR(50),
    created_at   TIMESTAMP DEFAULT current_timestamp
);

