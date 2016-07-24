package zaza.api;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import zaza.api.jsonmodel.ErrorMessage;

@ControllerAdvice
public class IllegalArgumentExceptionHandler {

    private final Log log = LogFactory.getLog(getClass());

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorMessage(e.getMessage(), "BAD_REQUEST");
    }
}
