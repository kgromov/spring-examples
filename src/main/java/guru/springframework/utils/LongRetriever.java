package guru.springframework.utils;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("intRetriever")
public class LongRetriever implements NumberRetriever<Long> {
    @Override
    public Long getByColumnName(ResultSet resultSet, String columnName) throws SQLException {
        long value = resultSet.getInt(columnName);
        return resultSet.wasNull() ? null : value;
    }

    @Override
    public Long getByColumnIndex(ResultSet resultSet, int columnIndex) throws SQLException {
        long value = resultSet.getInt(columnIndex);
        return resultSet.wasNull() ? null : value;
    }
}
