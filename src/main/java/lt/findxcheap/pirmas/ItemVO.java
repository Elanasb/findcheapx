package lt.findxcheap.pirmas;

import org.jsoup.nodes.Element;

public class ItemVO {
    public Element name;
    public Element img;
    public Element price;


    public ItemVO(Element image, Element title, Element price) {
        this.price = price;
        this.img = image;
        this.name = title;
    }
}
