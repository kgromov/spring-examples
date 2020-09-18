package guru.springframework.components;

import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import guru.springframework.utils.JdbcUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Component("recipe")
public class RowMapperConfig implements RowMapper<Recipe> {

    @Override
    @SneakyThrows
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        long id = rs.getLong("id");
        String description = rs.getString("description");
        Integer prepTime = JdbcUtils.getIntegerByColumnName(rs, "prepTime");
        Integer cookTime = JdbcUtils.getIntegerByColumnName(rs, "cookTime");
        Integer servings = JdbcUtils.getIntegerByColumnName(rs, "servings");
        String source = rs.getString("source");
        String url = rs.getString("url");
        String directions = rs.getString("directions");
        Difficulty difficulty = Difficulty.valueOf(rs.getString("difficulty"));
        recipe.setId(id);
        recipe.setDescription(description);
        recipe.setPrepTime(prepTime);
        recipe.setCookTime(cookTime);
        recipe.setServings(servings);
        recipe.setSource(source);
        recipe.setUrl(url);
        recipe.setDirections(directions);
        recipe.setDifficulty(difficulty);
        return recipe;
    }

}
