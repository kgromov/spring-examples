package guru.springframework.utils;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("intRetriever")
public class IntegerRetriever implements NumberRetriever<Integer> {
    @Override
    public Integer getByColumnName(ResultSet resultSet, String columnName) throws SQLException {
        int value = resultSet.getInt(columnName);
        return resultSet.wasNull() ? null : value;
    }

    @Override
    public Integer getByColumnIndex(ResultSet resultSet, int columnIndex) throws SQLException {
        int value = resultSet.getInt(columnIndex);
        return resultSet.wasNull() ? null : value;
    }
}
