import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class ImageGrabber{

	public static void main(String[] args)throws IOException, InterruptedException{		

		ImageGrabber.getImages();
		
	}	
	
	public static void getImages() throws IOException, InterruptedException{	
				
		File file = new File ("C:/Users/Stephen/Desktop/Java Programming/images/imagesDB.txt");
		PrintWriter printWriter = new PrintWriter ("C:/Users/Stephen/Desktop/Java Programming/images/imagesDB.txt");

		for(int i=1; i < 5; i++){
			
			String url = "http://florida.arrests.org/index.php?county=14&page=" + i + "&results=56";
			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer("http://www.google.com").get();			
			
			Elements images = doc.select("html body div div div div.search-results ul li div.profile-card div.picture a img");
			
			String arrestURL = null;
			for(Element image : images )
			{						
				arrestURL = image.getAllElements().toString();				
				printWriter.println ("http://florida.arrests.org" + arrestURL.substring(10,48));
				   
			}			
						
			TimeUnit.SECONDS.sleep(30);
		}				
		printWriter.close ();   
	}
}        