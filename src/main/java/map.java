import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.*;

public class map {
    private static Logger log = LogManager.getLogger(map.class);
    char[][] board;
    painting[] paintings;
    String[] titles;
    int sizey;
    int sizex;
    int numPaintings;
    int y;
    int x;
    ArrayList<Future<String>> futures;
    PrintWriter writer;
    private boolean valid = false;

    //read and initialize map and create all required painting threads
    public map(String mapName, PrintWriter writerName) {
        //BasicConfigurator.configure();
        writer = writerName;
        try {

            futures = new ArrayList<Future<String>>();
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
            if(numPaintings > 0) {
                ExecutorService executorService = Executors.newFixedThreadPool(numPaintings);
                paintings = new painting[numPaintings];
                titles = new String[numPaintings];
                for (int i = 0; i < numPaintings; i++) {
                    mapIn.readLine();
                    titles[i] = mapIn.readLine();
                    paintings[i] = new painting(mapIn.readLine(), titles[i]);
                    Future<String> tempFuture = executorService.submit(paintings[i]);
                    futures.add(tempFuture);
                }
            }
            mapIn.close();
            if(writer!=null) {
                writer.println("Player has entered the museum.");
            }
            valid = true;


        }catch(FileNotFoundException e) {
            log.error("Error finding file.");
        }catch (IOException e) {
            log.error("Error reading file.");
        }catch (Exception e){
            log.error("Unknown error.");
            e.printStackTrace();
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
                recordAndLog("Player jumped to "+command+".");
                return 5;

            }
            recordAndLog("Player tried to jump to non-existent exhibit.");
            return 6;
        }
        //up
        else if(command=='w') {
            if(move(-1, 0)) {
                recordAndLog("Moving up.");
                return 1;
            }
            recordAndLog("Couldn't move up.");
            return 7;
        }
        //left
        else if(command=='a') {
            if(move(0, -1)) {
                recordAndLog("Moving left.");
                return 1;
            }
            recordAndLog("Couldn't move left.");
            return 7;
        }
        //down
        else if(command=='s') {
            if(move(1, 0)) {
                recordAndLog("Moving down.");
                return 1;
            }
            recordAndLog("Couldn't move down.");
            return 7;
        }
        //right
        else if(command=='d') {
            if(move(0, 1)) {
                recordAndLog("Moving right.");
                return 1;
            }
            recordAndLog("Couldn't move right.");
            return 7;
        }
        //view action
        else if(command=='f') {
            temp = capitalToIndex(board[y][x]);
            if(temp==-1) {
                //f was pressed on an empty space
                recordAndLog("Player tried to view empty space.");
                return 2;
            }
            else {
                //print the painting

                try {
                    System.out.println(futures.get(temp).get());
                }catch(Exception e){

                    recordAndLog("There was something wrong with retrieving the painting URL.");
                    return 9;
                }
                recordAndLog("Player viewed painting: "+paintings[temp].title);
                return 3;
            }

        }
        //quit
        else if(command=='q'){
            recordAndLog("Player quit.");
            return 8;
        }
        recordAndLog("Player wrote invalid command.");
        return 4;
    }
    public void recordAndLog(String in){
        log.info(in);
        if(writer!=null){
            writer.println(in);
        }
    }
    //adjust the current user position
    public boolean move(int my, int mx) {
        try{
        if(board[y+my][x+mx]!='w') {
            y = y+my;
            x = x+mx;
            return true;
        }}catch(Exception e){

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