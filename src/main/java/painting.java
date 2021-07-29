import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class painting {
    String url;
    String ascii;
    String title;
    boolean valid;
    public painting(String myUrl, String myTitle) throws IOException {
        URL url;
        title = myTitle;
        url = new URL(myUrl);
        ascii = "";
        InputStream in = url.openStream();
        BufferedImage img = ImageIO.read(in);


        int detail = 50;
        int detailx = (int) (detail*3*(((double)img.getWidth())/img.getHeight()));
        int detaily = detail;


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

    }
    public String toString() {
        return ascii+title;
    }
}