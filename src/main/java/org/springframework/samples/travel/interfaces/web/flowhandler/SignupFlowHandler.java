package org.springframework.samples.travel.interfaces.web.flowhandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.execution.repository.NoSuchFlowExecutionException;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

public class SignupFlowHandler extends AbstractFlowHandler {

    private static final String REDIRECT_URL = "contextRelative:/users/signin";

    @Override
    public String handleExecutionOutcome(FlowExecutionOutcome outcome, HttpServletRequest request,
                                         HttpServletResponse response) {
        return REDIRECT_URL;
    }

    @Override
    public String handleException(FlowException e, HttpServletRequest request, HttpServletResponse response) {
        if (e instanceof NoSuchFlowExecutionException) {
            return REDIRECT_URL;
        } else {
            throw e;
        }
    }

}
