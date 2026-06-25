CREATE TABLE IF NOT EXISTS quantity_measurement_entity (
                                                           id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                           this_value              DOUBLE NOT NULL,
                                                           this_unit               VARCHAR(50) NOT NULL,
    this_measurement_type   VARCHAR(50) NOT NULL,
    that_value              DOUBLE,
    that_unit               VARCHAR(50),
    that_measurement_type   VARCHAR(50),
    operation               VARCHAR(50) NOT NULL,
    result_string           VARCHAR(255),
    result_value            DOUBLE,
    result_unit             VARCHAR(50),
    result_measurement_type VARCHAR(50),
    error_message           VARCHAR(500),
    is_error                BOOLEAN NOT NULL DEFAULT FALSE,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Index on operation for faster lookup by operation type
CREATE INDEX IF NOT EXISTS idx_operation ON quantity_measurement_entity (operation);

-- Index on measurement type for faster lookup by type
CREATE INDEX IF NOT EXISTS idx_measurement_type ON quantity_measurement_entity (this_measurement_type);

-- Index on error flag for retrieving error records
CREATE INDEX IF NOT EXISTS idx_is_error ON quantity_measurement_entity (is_error);

-- History / Audit table
CREATE TABLE IF NOT EXISTS quantity_measurement_history (
                                                            id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                            entity_id   BIGINT,
                                                            action      VARCHAR(50) NOT NULL,
    action_at   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    details     VARCHAR(1000)
    );