package com.quantity.repository;

import com.quantity.exception.DatabaseException;
import com.quantity.model.QuantityMeasurementEntity;
import com.quantity.util.DBConnection;

import java.sql.*;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = """
            INSERT INTO quantity_measurement (operation, result, error)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.toString());
            ps.setString(2, entity.toString());
            ps.setString(3,
                    entity.hasError() ? "ERROR" : null);

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("DB Save Failed", e);
        }
    }
}