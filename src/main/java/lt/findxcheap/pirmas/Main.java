package lt.findxcheap.pirmas;

import lt.findxcheap.pirmas.controller.IndexController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication //<---Svarbiausia dalis
public class Main {

    private FileReader _in;
    private BufferedReader _bufferis;
    private FileWriter _out;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

//        Main obj = new Main();
//
//        String search = "";
//        String webPageEbay = "https://www.ebay.com/sch/" + search;
//        String webPageAmazon = "https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=" + search;
//        String docEbay = null;
//        String docAmazon = null;



    }



}