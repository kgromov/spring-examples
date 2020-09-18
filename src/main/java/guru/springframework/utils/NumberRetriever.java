package guru.springframework.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface NumberRetriever<T extends Number> {

    T getByColumnName(ResultSet resultSet, String columnName) throws SQLException;

    T getByColumnIndex(ResultSet resultSet, int columnIndex) throws SQLException;
}
