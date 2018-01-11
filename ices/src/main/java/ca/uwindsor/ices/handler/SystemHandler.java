package ca.uwindsor.ices.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.uwindsor.ices.exception.SystemException;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class SystemHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpServletRequest request;

    @Autowired
    SystemHandler(HttpServletRequest request) {
        super();
        this.request = request;
    }

    @ExceptionHandler(SystemException.class)
    public String handleSystemException(SystemException ex){

        SecurityContextHolder.clearContext();

        logger.info("SystemException Occured - URL: {}; Message: {}",
                         this.request.getRequestURL(), ex.getMessage());
        ex.printStackTrace();

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex){

        SecurityContextHolder.clearContext();

        logger.info("Exception Occured - URL: {}; Message: {}",
                         this.request.getRequestURL(), ex.getMessage());
        ex.printStackTrace();

        return "error";
    }
}
