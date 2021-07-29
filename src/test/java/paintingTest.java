import org.junit.*;

import java.io.IOException;

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
    @Test
    public void connectionTest(){
        try {
            myPainting = new painting(url, title);
        }catch(Exception e){
            fail();
        }
    }
    @Test
    public void badURLTest(){
        try {
            myPainting = new painting(badURL, title);
            fail();
        }catch(Exception e){

        }
    }
    @Test
    public void drawingTest(){
        try {
            myPainting = new painting(url, title);
            System.out.println(myPainting);
        } catch (IOException e) {
            fail();
        }
    }

}
