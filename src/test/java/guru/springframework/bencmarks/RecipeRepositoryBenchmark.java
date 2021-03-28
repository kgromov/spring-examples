package guru.springframework.bencmarks;

import guru.springframework.repositories.jpa.RecipeJpaRepository;
import guru.springframework.repositories.data.RecipeRepository;
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
public class RecipeRepositoryBenchmark extends AbstractSpringComponentBenchmark {
    private static RecipeRepository recipeRepository;
    private static RecipeJpaRepository recipeJpaRepository;

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        RecipeRepositoryBenchmark.recipeRepository = recipeRepository;
    }

    @Autowired
    public void setRecipeJpaRepository(RecipeJpaRepository recipeJpaRepository) {
        RecipeRepositoryBenchmark.recipeJpaRepository = recipeJpaRepository;
    }


   /* @Setup(Level.Trial)
    public void setupBenchmark() {
    }
*/
    @Benchmark
    public void findAllSpringData(Blackhole blackhole) {
        blackhole.consume(recipeRepository.findAll());
    }

    @Benchmark
    public void findAllSpringJpa(Blackhole blackhole) {
        blackhole.consume(recipeJpaRepository.findAll());
    }
}
