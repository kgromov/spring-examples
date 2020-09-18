package guru.springframework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class JdbcTemplateConfig {

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate mySqlTemplate(@Qualifier("dataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        /*jdbcTemplate.setFetchSize(1_000);
        jdbcTemplate.setMaxRows(1_000);
        jdbcTemplate.setQueryTimeout(1_000);*/
        return jdbcTemplate;
    }

    @Bean(name = "parameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("jdbcTemplate") JdbcTemplate template) {
        return new NamedParameterJdbcTemplate(template);
    }
}
