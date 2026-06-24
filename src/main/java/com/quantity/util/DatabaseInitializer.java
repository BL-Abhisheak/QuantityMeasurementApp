package com.quantity.util;

import java.beans.Statement;
import java.sql.Connection;

public class DatabaseInitializer {

    public static void init() {

        String sql = """
            CREATE TABLE IF NOT EXISTS quantity_measurement (
                id INT AUTO_INCREMENT PRIMARY KEY,
                operation VARCHAR(50),
                result VARCHAR(255),
                error VARCHAR(255),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;

        try (Connection conn = DBConnection.getConnection();
             java.sql.Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (Exception e) {
            throw new RuntimeException("Table creation failed", e);
        }
    }
}
