package lt.findxcheap.pirmas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ebayController {
    @RequestMapping("/ebay")
    String ebay() {
// viskas kas parašyta šioje dalyje bus
// HTML puslapio dalis
        return "ebay";
    }

}
