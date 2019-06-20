package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

<<<<<<< HEAD
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
    private static final Long ID = 1L;
    private static final String DESCRIPTION = "Some description";

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNull() {
=======
import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();

    }

    @Test
    public void testNullParamter() throws Exception {
>>>>>>> 5e36016fbb595a16e848efb7dcc49b957c811589
        assertNull(converter.convert(null));
    }

    @Test
<<<<<<< HEAD
    public void testEmpty() {
=======
    public void testEmptyObject() throws Exception {
>>>>>>> 5e36016fbb595a16e848efb7dcc49b957c811589
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
<<<<<<< HEAD
    public void convert()
    {
        // given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        // when
        UnitOfMeasure unitOfMeasure = converter.convert(command);
        // then
        assertNull(unitOfMeasure);
        assertEquals(ID, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }

}
=======
    public void convert() throws Exception {
        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }

}
>>>>>>> 5e36016fbb595a16e848efb7dcc49b957c811589
