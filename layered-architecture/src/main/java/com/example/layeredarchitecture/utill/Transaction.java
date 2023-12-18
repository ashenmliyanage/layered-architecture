package com.example.layeredarchitecture.utill;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {

    static Connection connection;

    static {
        try {
            connection = DBConnection.getDbConnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Transaction() throws SQLException, ClassNotFoundException {
    }

    public static void Start() throws SQLException {
        connection.setAutoCommit(false);
    }
    public static void Roalback() throws SQLException, ClassNotFoundException {
        connection = DBConnection.getDbConnection().getConnection();
        connection.rollback();
        connection.setAutoCommit(true);
    }

    public static void end() throws SQLException {
        connection.setAutoCommit(true);
        connection.commit();
    }
}
