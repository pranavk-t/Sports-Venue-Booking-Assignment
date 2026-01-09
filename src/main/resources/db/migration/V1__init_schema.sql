
CREATE TABLE sports (
    id BIGINT PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE venues (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255),
    created_at DATETIME NOT NULL
);


CREATE TABLE slots (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    venue_id BIGINT NOT NULL,
    sport_id BIGINT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,

    CONSTRAINT fk_slot_venue
        FOREIGN KEY (venue_id) REFERENCES venues(id),

    CONSTRAINT fk_slot_sport
        FOREIGN KEY (sport_id) REFERENCES sports(id),

    CONSTRAINT chk_slot_time
        CHECK (start_time < end_time),

    CONSTRAINT uk_venue_time
        UNIQUE (venue_id, start_time, end_time)
);

CREATE INDEX idx_slot_search
ON slots (sport_id, start_time, end_time, status);


CREATE TABLE bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    slot_id BIGINT NOT NULL UNIQUE,
    status VARCHAR(20) NOT NULL,
    booked_at DATETIME NOT NULL,

    CONSTRAINT fk_booking_slot
        FOREIGN KEY (slot_id) REFERENCES slots(id)
);
