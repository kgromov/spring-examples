package guru.springframework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataBaseConfig {
    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        String[] profiles = env.getActiveProfiles();
    }

    @Bean("dataSource")
    @Profile({"default", "test"})
    public DataSource dataSourceForTest() {
        return new EmbeddedDatabaseBuilder()
            .generateUniqueName(true)
            .setType(EmbeddedDatabaseType.H2)
            .setScriptEncoding("UTF-8")
            .ignoreFailedDrops(true)
//            .addScript("schema.sql")
            .addScripts("data.sql")
            .build();
    }

    @Bean(name = "dataSource")
    @Profile("prod")
    public DataSource dataSourceForProd() {
        return DataSourceBuilder.create()
            .driverClassName(env.getProperty("db.driver"))
            .url(env.getProperty("db.url"))
            .username(env.getProperty("db.user"))
            .password(env.getProperty("db.pass"))
            .build();
    }

    @Bean(name = "transactionManager")
    @Profile("test")
    public PlatformTransactionManager transactionManagerForTest(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
