package ca.uwindsor.ices.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping({"/history"})
    public String history() {
        return "history";
    }
    @RequestMapping({"/activation"})
    public String activation() {
        return "activation";
    }
}
