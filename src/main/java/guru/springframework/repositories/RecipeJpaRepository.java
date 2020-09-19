package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.services.profiling.Profiling;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@Profiling(timeunit = TimeUnit.MICROSECONDS)
public class RecipeJpaRepository extends AbstractRepository<Recipe>{
    @Override
    protected Class<Recipe> getEntityClass() {
        return Recipe.class;
    }
}
