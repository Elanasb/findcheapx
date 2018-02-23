package lt.findxcheap.pirmas.controller;

import javafx.scene.control.Tab;
import lt.findxcheap.pirmas.ItemVO;
import lt.findxcheap.pirmas.Main;
import lt.findxcheap.pirmas.Table;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class ComparisonController {
    public static final String FailovardasEbay = "src/main/resources/templates/ebay.html";
    public static final String FailovardasAmazon = "src/main/resources/templates/amazon.html";


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
        //  String webPageAmazon = "https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=" + search;
        String docEbay = null;
        //   String docAmazon = null;

        try

        {

            docEbay = Jsoup.connect(webPageEbay).get().html();

            Document eb = Jsoup.parse(docEbay);
            Elements ebayList = eb.select("#ListViewInner > li");


            Element Image = ebayList.get(0).select("a > img").get(0);
            Element Title = ebayList.get(0).select(" h3 > a").get(0);
            Element Price = ebayList.get(0).select("ul.lvprices.left.space-zero > li.lvprice.prc").get(0);

            Table tab = new Table();
            tab.item.add(new ItemVO(Image,Title,Price));





        } catch (
                IOException e)

        {
            e.printStackTrace();
        }


        return "comparison";

    }


}


