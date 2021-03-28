package guru.springframework.repositories.jpa;

import guru.springframework.domain.Recipe;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeJpaRepository extends AbstractJpaRepository<Recipe> {

    @Override
    protected Class<Recipe> getEntityClass() {
        return Recipe.class;
    }
}
