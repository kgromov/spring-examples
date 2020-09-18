package guru.springframework.repositories;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Table;

public abstract class AbstractRepository<T> {
    private static final String SELECT_ALL = "SELECT * FROM %s";
    private static final String SELECT_BY_ID = "SELECT * FROM %s WHERE id = ?";
    private static final String SELECT_BY_DESCRIPTION = "SELECT * FROM %s WHERE description = ?";
    private static final String DELETE_BY_ID = "DELETE FROM %s WHERE id = ?";

    @Autowired
    @Getter
    private JdbcTemplate jdbcTemplate;

    public Iterable<T> findAll() {
        String query = String.format(SELECT_ALL, getTableName());
        return jdbcTemplate.query(query, getRowMapper());
    }

    public T findById(Long id) {
        String query = String.format(SELECT_BY_ID, getTableName());
        return jdbcTemplate.queryForObject(query, getRowMapper(), id);
    }

    public T findByDescription(String description) {
        String query = String.format(SELECT_BY_DESCRIPTION, getTableName());
        return jdbcTemplate.queryForObject(query, getRowMapper(), description);
    }

    public void remove(Long id) {
        String query = String.format(DELETE_BY_ID, getTableName());
        jdbcTemplate.update(query, id);
    }

    private String getTableName() {
        Class<T> entityClass = getEntityClass();
        if (entityClass.isAnnotationPresent(Table.class)) {
            return entityClass.getAnnotation(Table.class).name();
        }
        return entityClass.getSimpleName();
    }

    protected abstract Class<T> getEntityClass();

    protected abstract RowMapper<T> getRowMapper();

    public abstract void saveOrUpdate(T entity);
}
