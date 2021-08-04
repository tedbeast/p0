import org.junit.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class paintingTest {
    static String url;
    static String badURL;
    static String title;
    static painting myPainting;
    @BeforeClass
    public static void setUp(){
        url = "https://assets.catawiki.nl/assets/2019/7/2/3/8/4/3841134c-6385-4b83-999a-cfcd285a7c18.jpg";
        badURL = "https://www.renemagritte.org/images/paintings/painting1234.jpg";
        title = "The Blank Signature, Magritte";
        myPainting = null;
    }
    @AfterClass
    public static void tearDown(){
        myPainting = null;
    }
    //Painting should be instantiated without exceptions
    @Test
    public void connectionTest(){
        try {
            myPainting = new painting(url, title);
        }catch(Exception e){
            fail();
        }
    }
    //Bad URL should throw an exception
    @Test
    public void badURLTest(){
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            myPainting = new painting(badURL, title);
            Future<String> future = (executorService.submit(myPainting));

            System.out.println(future.get());
            fail();
        } catch (Exception e) {

        }
    }
    //Can we print painting without exceptions?
    @Test
    public void drawingTest(){
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            myPainting = new painting(url, title);
            Future<String> future = (executorService.submit(myPainting));

            System.out.println(future.get());
        } catch (Exception e) {
            fail();
        }
    }
}
