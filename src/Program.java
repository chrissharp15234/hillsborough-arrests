import java.io.IOException;

/**
 * Created by Guest User on 10/13/2017.
 */
public class Program {

    public static void main(String[] args)throws IOException, InterruptedException {

        //ImageGrabber.getImages();
        //ImageGrabber.putImagesInFolder();
        InfoGrabber.getInfo();
        SQDBConnection.selectAllFromDB();
    }
}
