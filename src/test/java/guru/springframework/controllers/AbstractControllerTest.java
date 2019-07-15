package guru.springframework.controllers;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public abstract class AbstractControllerTest {
    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        setControllers();
        mockMvc = MockMvcBuilders.standaloneSetup(getController())
//                .setControllerAdvice(new ControllerExceptionHandler())
                .setControllerAdvice(ControllerExceptionHandler.class)
                .build();
    }

    protected abstract void setControllers();

    protected abstract Object[] getController();
}
