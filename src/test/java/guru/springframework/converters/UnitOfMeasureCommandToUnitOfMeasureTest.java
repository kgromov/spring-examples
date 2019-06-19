package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

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
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
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
