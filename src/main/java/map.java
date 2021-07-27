import java.io.*;
import java.net.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class map {
    char[][] board;
    painting[] paintings;
    String[] titles;
    int sizey;
    int sizex;
    int numPaintings;
    int y;
    int x;
    private boolean valid = false;
    //read and initialize map and create all required painting objects
    public map(String mapName) {
        try {
            String thisLine;
            BufferedReader mapIn = new BufferedReader(new FileReader(mapName));
            sizey = Integer.parseInt(mapIn.readLine());
            sizex = Integer.parseInt(mapIn.readLine());
            numPaintings = Integer.parseInt(mapIn.readLine());
            board = new char[sizey][sizex];
            for(int i = 0; i < sizey; i++) {
                thisLine = mapIn.readLine();
                for(int j = 0; j < sizex; j++) {
                    char thisChar = thisLine.charAt(j);
                    board[i][j] = thisChar;
                    if(thisChar=='p') {
                        y = i;
                        x = j;
                        board[i][j] = 'x';
                    }
                }
            }
            paintings = new painting[numPaintings];
            titles = new String[numPaintings];
            for(int i = 0; i < numPaintings; i++) {
                int index = capitalToIndex(mapIn.readLine().charAt(0));
                titles[index] = new String(mapIn.readLine());
                paintings[index] = new painting(mapIn.readLine(),titles[index]);

            }
            valid = true;

        }catch(FileNotFoundException e) {
            System.out.println("Error finding file.");
        }catch (IOException e) {
            System.out.println("Error reading line.");
        }catch (Exception e){
            System.out.println("Unknown error.");
        }
    }
    //the input letter for the exhibition is returned as an index in the paintings array
    public int capitalToIndex(char a) {
        int temp = ((int)a)-65;
        if(temp > numPaintings || temp < 0) {
            return -1;
        }
        return temp;

    }
    //the exhibition given is found in the map, and the coordinates are returned in an array
    public int[] getPaintingCoords(char command){
        for(int i = 0; i < sizey; i++){
            for(int j = 0; j < sizex; j++){
                if(board[i][j] == command){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
    public boolean isValid(){
        return valid;
    }
    //interpret the user input for direction
    public int command(char command) {
        int temp = capitalToIndex(command);
        if(temp!=-1) {
            int[] coords = getPaintingCoords(command);
            if(coords!=null){
                y=coords[0];
                x=coords[1];
                return 5;
            }
            return 6;
        }
        //up
        else if(command=='w') {
            if(move(-1, 0))
                return 1;
            return 7;
        }
        //left
        else if(command=='a') {
            if(move(0, -1))
                return 1;
            return 7;
        }
        //down
        else if(command=='s') {
            if(move(1, 0))
                return 1;
            return 7;
        }
        //right
        else if(command=='d') {
            if(move(0, 1))
                return 1;
            return 7;
        }
        //view action
        else if(command=='f') {
            temp = capitalToIndex(board[y][x]);
            if(temp==-1) {
                //f was pressed on an empty space
                return 2;
            }
            else {
                //print the painting
                System.out.println(paintings[temp]);
                return 3;
            }

        }
        //quit
        else if(command=='q'){
            return 8;
        }
        return 4;
    }
    //adjust the current user position
    public boolean move(int my, int mx) {
        if(board[y+my][x+mx]!='w') {
            y = y+my;
            x = x+mx;
            return true;
        }
        return false;
    }
    //print out the map
    public String toString() {
        String out = "";
        for(int i = 0; i < sizey; i++) {
            for(int j = 0; j < sizex; j++) {
                if (i==y&&j==x)
                    out = out+('^');
                else if(board[i][j]=='x')
                    out = out+(' ');
                else if(board[i][j]=='w')
                    out = out+('x');
                else
                    out = out+board[i][j];
                out = out+' ';
            }
            out = out + '\n';
        }
        return out;
    }
}