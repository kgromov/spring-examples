package guru.springframework.bencmarks;

import guru.springframework.repositories.data.UnitOfMeasureRepository;
import guru.springframework.repositories.jpa.UomJpaRepository;
import guru.springframework.repositories.template.UomJdbcTemplateRepository;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class UomRepositoryBenchmark extends AbstractSpringComponentBenchmark {
    private static UnitOfMeasureRepository unitOfMeasureRepository;
    private static UomJpaRepository uomJpaRepository;
    private static UomJdbcTemplateRepository uomJdbcTemplateRepository;

    @Autowired
    public void setUnitOfMeasureRepository(UnitOfMeasureRepository unitOfMeasureRepository) {
        UomRepositoryBenchmark.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Autowired
    public void setUomJpaRepository(UomJpaRepository uomJpaRepository) {
        UomRepositoryBenchmark.uomJpaRepository = uomJpaRepository;
    }

    @Autowired
    public void setUomJdbcTemplateRepository(UomJdbcTemplateRepository uomJdbcTemplateRepository) {
        UomRepositoryBenchmark.uomJdbcTemplateRepository = uomJdbcTemplateRepository;
    }

    @Benchmark
    public void findAll_SpringData(Blackhole blackhole) {
        blackhole.consume(unitOfMeasureRepository.findAll());
    }

    @Benchmark
    public void findAll_SpringJpa(Blackhole blackhole) {
        blackhole.consume(uomJpaRepository.findAll());
    }

    @Benchmark
    public void findAll_JdbcTemplate(Blackhole blackhole) {
        blackhole.consume(uomJdbcTemplateRepository.findAll());
    }
}
