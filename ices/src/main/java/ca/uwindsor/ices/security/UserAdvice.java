package ca.uwindsor.ices.security;



import java.security.Principal;
import java.util.Optional;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserAdvice {

    @ModelAttribute("userName")
    public String setUserName(Principal principal){
        return Optional.ofNullable(principal).map(Principal::getName).orElse("");
    }
}
