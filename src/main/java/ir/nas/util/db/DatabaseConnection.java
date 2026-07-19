package ir.nas.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import ir.nas.db.DatabaseConfig;
import ir.nas.exception.database.DatabaseConnectionException;
import ir.nas.exception.database.DatabaseQueryException;
import ir.nas.exception.repository.DeleteQueryException;
import lombok.Cleanup;

public final class DatabaseConnection
{
    private static final Connection getConnection()
    {
        try {
            return DriverManager.getConnection(
                    DatabaseConfig.URL,
                    DatabaseConfig.USERNAME,
                    DatabaseConfig.PASSWORD);

        } catch (SQLException e) {
            throw new DatabaseConnectionException("Database Connection Failed: ".concat(e.getMessage()));
        }
    }

    public static final <T> T excuteQuery(final String query, final Excutable<T> excutable)
    {
        try {
            @Cleanup
            Connection connection = getConnection();
            @Cleanup
            PreparedStatement ps = connection.prepareStatement(query);
            return excutable.excute(ps);
        } catch (SQLException e) {
            throw new DatabaseQueryException("Database Query Failed: ".concat(e.getMessage()));
        }
    }

    public static final <T> T excuteQueryWithGenerateKey(final String query, final Excutable<T> excutable)
    {
        try {
            @Cleanup
            Connection connection = getConnection();
            @Cleanup
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            return excutable.excute(ps);
        } catch (SQLException e) {
            throw new DatabaseQueryException("Database Query Failed: ".concat(e.getMessage()));
        }
    }
}
