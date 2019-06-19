package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Collection;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Set<Recipe> findAll();

    Recipe findById(Long id);
}
