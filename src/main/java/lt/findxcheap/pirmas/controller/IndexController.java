package lt.findxcheap.pirmas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // <-- būtina
public class IndexController {
    @RequestMapping("/")
    String index(
            @RequestParam(
                    value = "search",
                    required = false,
                    defaultValue = ""
            )
            String search,
            Model model
    ) {
        model.addAttribute("search", search);
        return "index";
    }
}
