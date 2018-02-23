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
    private static String _failoVardasEbay = "static/ebay.html";
    private static String _failoVardasAmazon = "amazon.html";
    private FileReader _in;
    private BufferedReader _bufferis;
    private FileWriter _out;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        Main obj = new Main();

        String search = "";
        String webPageEbay = "https://www.ebay.com/sch/" + search;
        String webPageAmazon = "https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=" + search;
        String docEbay = null;
        String docAmazon = null;



    }


    public void atidarytiFaila(String failoVardas) {
        try {
            _in = new FileReader(failoVardas);
            _bufferis = new BufferedReader(_in);
        } catch (Exception e) {

        }
    }


    public void irasimas(Elements s, String failoVardas) {

        try {
            _out = new FileWriter(failoVardas, true);
            _out.write(String.valueOf(s));
            _out.close();
            System.out.println("Ivesta");

            _out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}