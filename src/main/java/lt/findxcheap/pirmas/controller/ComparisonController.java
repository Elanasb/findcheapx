package lt.findxcheap.pirmas.controller;

import com.sun.xml.internal.ws.util.Pool;
import lt.findxcheap.pirmas.ItemVO;
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
    public ArrayList<ItemVO> BestBuyItems = new ArrayList<ItemVO>();

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
        String webPageAmazon = "https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=" + search;
        String webPageBestBuy = "https://www.bestbuy.com/site/searchpage.jsp?st=" + search + "&_dyncharset=UTF-8&id=pcat17071&type=page&sc=Global&cp=1&nrp=&sp=&qp=&list=n&af=true&iht=y&usc=All+Categories&ks=960&keys=keys";
        Document docEbay = null;
        Document docAmazon = null;
        Document docBB = null;
        int dydis = 20;
        int ebayDydis;
        int BestDydis;

        try

        {

            docEbay = Jsoup.connect(webPageEbay).get();
            docBB = Jsoup.connect(webPageBestBuy).get();


            Elements ebayList = docEbay.select("#ListViewInner > li");
            Elements BestBuyList = docBB.select("#resultsTabPanel > div.list-items > div");

            ebayItems = new ArrayList<ItemVO>();
            BestBuyItems = new ArrayList<ItemVO>();






            for (int i = 0; i < ebayList.size() && i<20 ; i++) {
                Element Image = null;
                if (ebayList.get(i).select("a > img").size() > 0) {
                    Image = ebayList.get(i).select("a > img").get(0);
                } else {
                    continue;
                }
                Element Title = ebayList.get(i).select(" h3 > a").get(0);
                Element Price = ebayList.get(i).select("ul.lvprices.left.space-zero > li.lvprice.prc").get(0);
                Title.attr("rel", "nofollow").attr("target", "_blank");
                String StringTitle = String.valueOf(Title);
                String StringImage = "";
                if (Image != null)
                    StringImage = Image.attr("src");
                String StringPrice = Price.text();
                ebayItems.add(new ItemVO(StringTitle, StringImage, StringPrice));
            }


            for (int i = 0; i < BestBuyList.size()&& i<20; i++) {
                Element ImageBestBuy = null;
                if (BestBuyList.get(i).select("div.list-item-thumbnail > div > a > img").size() > 0) {
                    ImageBestBuy = BestBuyList.get(i).select("div.list-item-thumbnail > div > a > img").get(0);
                } else {
                    continue;
                }

                Element TitleBestBuy = BestBuyList.get(i).select("div.sku-title > h4 > a").get(0);
                TitleBestBuy.absUrl("bestbuy.com");
                Element PriceBestBuy = BestBuyList.get(i).select("div.pb-hero-price.pb-purchase-price > span").get(0);
                // make absolute url----
                String absUrl = TitleBestBuy.absUrl("href");
                TitleBestBuy.attr("href", absUrl).attr("target", "_blank").attr("rel", "nofollow");
                //------
                String StringTitleBestBuy = String.valueOf(TitleBestBuy);
                String StringImageBestBuy = "";
                if (ImageBestBuy != null) {
                    StringImageBestBuy = ImageBestBuy.attr("src");
                }
                String StringPriceBestBuy = PriceBestBuy.text();
                BestBuyItems.add(new ItemVO(StringTitleBestBuy, StringImageBestBuy, StringPriceBestBuy));

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("ebayItems", ebayItems);
        model.addAttribute("BestBuyItems", BestBuyItems);

        return "comparison";

    }


}


