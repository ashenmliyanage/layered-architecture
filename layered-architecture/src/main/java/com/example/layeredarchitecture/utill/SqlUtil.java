package com.example.layeredarchitecture.utill;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    public static <T> T execute(String sql, Object... arg) throws SQLException, ClassNotFoundException {

        PreparedStatement statement = DBConnection.getDbConnection().getConnection().prepareStatement(sql);

        for (int i = 0; i < arg.length; i++) {
            statement.setObject((i + 1), arg[i]);
        }

        System.out.println(statement.toString());
        if (sql.trim().toUpperCase().startsWith("SELECT")) {
            return (T) statement.executeQuery();
        } else {
            return (T) (Boolean) (statement.executeUpdate() > 0);
        }
    }
}