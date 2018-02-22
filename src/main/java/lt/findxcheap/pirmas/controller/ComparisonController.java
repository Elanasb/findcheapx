package lt.findxcheap.pirmas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComparisonController {

    @RequestMapping("/comparison")
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





        return "comparison";

    }
}


