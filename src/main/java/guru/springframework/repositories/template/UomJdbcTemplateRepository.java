package guru.springframework.repositories.template;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UomJdbcTemplateRepository extends AbstractJdbcTemplateRepository<UnitOfMeasure> {
    private final AtomicLong id = new AtomicLong();

    @PostConstruct
    public void init() {
        int a =1;
    }

    @Override
    protected Class<UnitOfMeasure> getEntityClass() {
        return UnitOfMeasure.class;
    }

    @Override
    protected RowMapper<UnitOfMeasure> getRowMapper() {
        return (rs, rowNum) -> {
            UnitOfMeasure uom = new UnitOfMeasure();
            long id = rs.getLong("id");
            String description = rs.getString("description");
            uom.setId(id);
            uom.setDescription(description);
            return uom;
        };
    }

    @Override
    public void saveOrUpdate(UnitOfMeasure entity) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
        if (entity.getId() == null) {
            String sqlTxt = "insert into unit_of_measure(description) values(?, ?)";
            jdbcTemplate.update(sqlTxt, entity.getId(), entity.getDescription());
        }
        else {
            String sqlTxt = "update unit_of_measure set description = ? where id = ?";
            jdbcTemplate.update(sqlTxt, entity.getDescription(), entity.getId());
        }
    }
}