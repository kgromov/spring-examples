package guru.springframework.repositories;

import guru.springframework.domain.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryJpaRepository extends AbstractRepository<Category>{
    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}
