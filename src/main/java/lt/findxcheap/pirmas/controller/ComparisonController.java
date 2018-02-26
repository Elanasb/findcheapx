package lt.findxcheap.pirmas.controller;

import com.sun.xml.internal.ws.util.Pool;
import lt.findxcheap.pirmas.ItemVO;
import lt.findxcheap.pirmas.Table;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class ComparisonController {
    public static final String FailovardasEbay = "src/main/resources/templates/ebay.html";
    public static final String FailovardasAmazon = "src/main/resources/templates/amazon.html";

    public ArrayList<ItemVO> ebayItems = new ArrayList<ItemVO>();
    public ArrayList<ItemVO> amazonItems = new ArrayList<ItemVO>();

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
        Document docEbay = null;
        Document docAmazon = null;

        try

        {

            docEbay = Jsoup.connect(webPageEbay).get();
            docAmazon= Jsoup.connect(webPageAmazon).get();


            Elements ebayList = docEbay.select("#ListViewInner > li");

            ebayItems = new ArrayList<ItemVO>();
            for (int i = 0; i < ebayList.size(); i++) {
                Element Image = ebayList.get(i).select("a > img").get(0);
                Element Title = ebayList.get(i).select(" h3 > a").get(0);
                Element Price = ebayList.get(i).select("ul.lvprices.left.space-zero > li.lvprice.prc").get(0);
                String StringTitle = Title.text();
                String StringImage = Image.attr("src");
                String StringPrice = Price.text();
                ebayItems.add(new ItemVO(StringTitle, StringImage, StringPrice));
            }


          


        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
        model.addAttribute("ebayItems", ebayItems);

        return "comparison";

    }


}


