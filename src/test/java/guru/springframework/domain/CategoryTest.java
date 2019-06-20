package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

<<<<<<< HEAD
import static org.junit.Assert.*;

/**
 * Created by konstantin on 17.06.2019.
 */
public class CategoryTest {
=======
import static org.junit.Assert.assertEquals;

/**
 * Created by jt on 6/17/17.
 */
public class CategoryTest {

>>>>>>> 5e36016fbb595a16e848efb7dcc49b957c811589
    Category category;

    @Before
    public void setUp(){
        category = new Category();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() throws Exception {
    }

    @Test
    public void getRecipes() throws Exception {
    }
<<<<<<< HEAD
}
=======

}
>>>>>>> 5e36016fbb595a16e848efb7dcc49b957c811589
