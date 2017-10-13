import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

public class ImageGrabber{

	public static void getImages() throws IOException, InterruptedException{	
				
		File file = new File ("C:/Users/Guest User/Documents/GitHub/hillsborough-arrests/imagesDB.txt");
		PrintWriter printWriter = new PrintWriter ("C:/Users/Guest User/Documents/GitHub/hillsborough-arrests/imagesDB.txt");

		for(int i=1; i < 3; i++){
			
			String url = "http://florida.arrests.org/index.php?county=14&page=" + i + "&results=56";
			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").referrer("http://www.google.com").get();			
			
			Elements images = doc.select("html body div div div div.search-results ul li div.profile-card div.picture a img");
			
			String arrestURL = null;
			for(Element image : images )
			{						
				arrestURL = image.getAllElements().toString();				
				printWriter.println ("https://florida.arrests.org" + arrestURL.substring(10,48));
				   
			}			
						
			TimeUnit.SECONDS.sleep(10);
		}				
		printWriter.close ();   
	}

	public static void putImagesInFolder() throws IOException, InterruptedException {

		BufferedReader br = new BufferedReader(new FileReader("C:/Users/Guest User/Documents/GitHub/hillsborough-arrests/imagesDB.txt"));
		try
		{
			String line = br.readLine();;

			while (line != null)
			{
				URL url = new URL(line);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6");
				InputStream is = new BufferedInputStream(connection.getInputStream());
				BufferedImage img = ImageIO.read(is);
				is.close();
				File file = new File("C:/Users/Guest User/Documents/GitHub/hillsborough-arrests/images/"
						+ line.substring(line.length() -12,line.length()));
				ImageIO.write(img, "jpg", file);
				TimeUnit.SECONDS.sleep(5);
				line = br.readLine();
			}
		}
		finally
		{
			br.close();
		}
	}
}        