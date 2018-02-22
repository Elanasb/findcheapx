package lt.findxcheap.pirmas.controller;

import lt.findxcheap.pirmas.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

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

        //logika
        String webPageEbay = "https://www.ebay.com/sch/" + search;
        String webPageAmazon = "https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=" + search;
        String docEbay = null;
        String docAmazon = null;
        try

        {

            docEbay = Jsoup.connect(webPageEbay).get().html();
            docAmazon = Jsoup.connect(webPageAmazon).get().html();

            Document am = Jsoup.parse(docAmazon);
            Document eb = Jsoup.parse(docEbay);
            Elements ebayList = eb.select("#ResultSetItems");
            Elements amazonList = am.select("#resultsCol");
            Main obj = new Main();
            obj.atidarytiFaila("EBAY.html");
            obj.irasimas(ebayList,"EBAYLIST.html");



        } catch (
                IOException e)

        {
            e.printStackTrace();
        }



        return "comparison";

    }
}


