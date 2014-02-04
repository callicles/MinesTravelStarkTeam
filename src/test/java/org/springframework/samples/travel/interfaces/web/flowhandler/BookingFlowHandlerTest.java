package org.springframework.samples.travel.interfaces.web.flowhandler;

import static org.fest.assertions.Assertions.assertThat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.webflow.engine.builder.FlowBuilderException;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.execution.repository.NoSuchFlowExecutionException;

public class BookingFlowHandlerTest {

    private static final String EXPECTED_REDIRECT_URL = "contextRelative:/hotels/search";

    private final BookingFlowHandler flowHandler = new BookingFlowHandler();

    @Test
    public void shouldReturnCorrectPathWhenHandlingExecutionOutcome() {
        assertThat(
                flowHandler.handleExecutionOutcome(Mockito.mock(FlowExecutionOutcome.class),
                        Mockito.mock(HttpServletRequest.class), Mockito.mock(HttpServletResponse.class))).isEqualTo(
                EXPECTED_REDIRECT_URL);
    }

    @Test
    public void shouldHandleNoSuchFlowExecutionException() {
        NoSuchFlowExecutionException noSuchFlowExecutionException = new NoSuchFlowExecutionException(null, null);
        assertThat(
                flowHandler.handleException(noSuchFlowExecutionException, Mockito.mock(HttpServletRequest.class),
                        Mockito.mock(HttpServletResponse.class))).isEqualTo(EXPECTED_REDIRECT_URL);
    }

    @Test(expected = FlowBuilderException.class)
    public void shouldReThrowExceptionOtherThanNoSuchFlowExecutionException() {
        flowHandler.handleException(new FlowBuilderException(null), Mockito.mock(HttpServletRequest.class),
                Mockito.mock(HttpServletResponse.class));
    }
}
