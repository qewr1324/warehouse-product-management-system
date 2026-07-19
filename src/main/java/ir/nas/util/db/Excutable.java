package ir.nas.util.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Excutable
 */
public interface Excutable<T>
{
    T excute(PreparedStatement ps) throws SQLException;
}
