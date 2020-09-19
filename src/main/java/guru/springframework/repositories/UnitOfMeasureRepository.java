package guru.springframework.repositories;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("unitOfMeasureRepository")
public class UnitOfMeasureRepository extends AbstractRepository<UnitOfMeasure>{
    @Override
    protected Class<UnitOfMeasure> getEntityClass() {
        return UnitOfMeasure.class;
    }
}
