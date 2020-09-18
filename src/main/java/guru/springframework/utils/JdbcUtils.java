package guru.springframework.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JdbcUtils {

    public static Integer getIntegerByColumnName(ResultSet resultSet, String columnName) throws SQLException {
        int value = resultSet.getInt(columnName);
        return resultSet.wasNull() ? null : value;
    }

    public static Integer getIntegerByColumnIndex(ResultSet resultSet, int columnIndex) throws SQLException {
        int value = resultSet.getInt(columnIndex);
        return resultSet.wasNull() ? null : value;
    }

    public static Long getLongByColumnName(ResultSet resultSet, String columnName) throws SQLException {
        long value = resultSet.getInt(columnName);
        return resultSet.wasNull() ? null : value;
    }

    public static Long getLongByColumnIndex(ResultSet resultSet, int columnIndex) throws SQLException {
        long value = resultSet.getInt(columnIndex);
        return resultSet.wasNull() ? null : value;
    }
}
