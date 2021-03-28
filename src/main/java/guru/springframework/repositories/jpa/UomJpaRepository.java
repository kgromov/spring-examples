package guru.springframework.repositories.jpa;

import guru.springframework.domain.UnitOfMeasure;
import org.springframework.stereotype.Repository;

@Repository
public class UomJpaRepository extends AbstractJpaRepository<UnitOfMeasure> {
    @Override
    protected Class<UnitOfMeasure> getEntityClass() {
        return UnitOfMeasure.class;
    }
}
