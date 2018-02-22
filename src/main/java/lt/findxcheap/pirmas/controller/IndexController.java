package lt.findxcheap.pirmas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // <-- būtina
public class IndexController {
    @RequestMapping("/") // Kokiu adresu bus
        // ką rodyti atsiliepiant
    String index() {
// viskas kas parašyta šioje dalyje bus
// HTML puslapio dalis
        return "index";
    }
}
