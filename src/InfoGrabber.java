import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
* Created by Guest User on 10/13/2017.
*/
public class InfoGrabber {

    public static void getInfo() throws FileNotFoundException, IOException {
        Document doc;
        File file = new File("C:/Users/Guest User/Documents/GitHub/hillsborough-arrests/infoDB.txt");
        PrintWriter printwriter = new PrintWriter(file);

        String url;

        for (int i=1; i<3; i++){
            url ="http://florida.arrests.org/index.php?county=14&page=" + i + "&results=56";

            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0").referrer("http://www.google.com").ignoreHttpErrors(true).get();
            Elements titles = doc.select("div.search-results div.profile-card div.title a[href]");

            for(Element title:titles)
            {
                System.out.println(title);
                printwriter.println(title);
            }

            printwriter.close();
        }
    }
}