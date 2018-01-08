package net.codejava.javaee;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;

public class BasicWebCrawler {

    private HashSet<String> links;
    private ArrayList<String> allLinks;

    public BasicWebCrawler() {
        links = new HashSet<String>();
        allLinks = new ArrayList<String>();
    }

    public void getPageLinks(String URL) {
        
        if (!links.contains(URL)) {
            try {
                if (links.add(URL)) {
                	if(URL.matches(".*(Donald Trump|DONALD TRUMP|donald trump|trump|Trump|TRUMP).*") && allLinks.size()<25)
                			allLinks.add(URL);
                	
                    System.out.println(URL);
                }

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");

                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    public static void main() {
        new BasicWebCrawler().getPageLinks("http://edition.cnn.com/");
    }

}