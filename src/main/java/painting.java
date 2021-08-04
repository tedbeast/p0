import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.imageio.ImageIO;

public class painting implements Callable<String> {
    String urlString;
    String ascii;
    String title;
    URL url;
    boolean valid;

    public painting(String myUrl, String myTitle){
        urlString = myUrl;
        title = myTitle;
        ascii = "";
    }

    public String call() throws IOException {
        url = new URL(urlString);
        InputStream in = url.openStream();
        BufferedImage img = ImageIO.read(in);

        int detail = 29;
        int detailx = (int) (detail*3*(((double)img.getWidth())/img.getHeight()));
        int detaily = detail;
        //generate ascii, with white represented by space and black represented by @
        for(int i = 0; i < detaily-1; i++) {
            for(int j = 0; j < detailx-1; j++) {
                Color color = new Color(img.getRGB(j*(img.getWidth())/detailx, i*(img.getHeight())/detaily));
                double val = (color.getRed()+color.getBlue()+color.getGreen())/3;
                if(val>160) {
                    ascii+=' ';
                }
                else if(val>140) {
                    ascii+=('.');
                }
                else if(val>120) {
                    ascii+=('*');
                }
                else if(val>100) {
                    ascii+=('+');
                }
                else if(val>80) {
                    ascii+=('^');
                }
                else if(val>60) {
                    ascii+=('&');
                }
                else if(val>40) {
                    ascii+=('8');
                }
                else if(val>20) {
                    ascii+=('#');
                }
                else
                    ascii+=('@');

            }
            ascii+='\n';
        }
        valid = true;
        return ascii+title;
    }
}