import org.apache.log4j.BasicConfigurator;
import java.io.*;

import java.sql.Timestamp;
import java.util.Scanner;
public class museum {
    //user input driver
    public static void main(String[] args) {
        //inserting timestamp allows us to create infinite amount of museumhistory files.
        String timeString = (new Timestamp(System.currentTimeMillis())).toString();
        timeString = timeString.replaceAll("-","");
        timeString = timeString.replaceAll(":","");
        timeString = timeString.replaceAll(" ","");
        System.out.println(timeString);
        boolean playing = true;
        boolean mapNotFound = true;
        PrintWriter writer = null;
        map mapA;
        try {
            writer = new PrintWriter("MuseumHistory"+timeString+".txt", "UTF-8");

        }catch(Exception e){
            System.out.println("Error in creating output file. ");
        }
        //ask the user to input a valid map file until they do
        do{
            System.out.println("Please input game file");
            String mapName = getName();
            mapA = new map(mapName, writer);

            if(mapA.isValid())
                mapNotFound=false;
            else
                System.out.println("Please try another input.");

        }while(mapNotFound);
        //the user input loop
        Scanner consoleIn = new Scanner(System.in);
        while(playing) {
            System.out.println(mapA);
            System.out.println("You may enter: wasd to move, f to view an exhibition, an exhibition letter to jump to it, or q to quit.");
            String in = consoleIn.nextLine();
            if(!in.equals("")) {
                char commandIn = in.trim().charAt(0);

                int code = mapA.command(commandIn);
                if(code == 2) {
                    System.out.println("There is no exhibition there.");
                }
                else if (code == 3) {
                    System.out.println("Press enter to continue viewing the museum");
                    //wait for ANY input if command returns true (painting is printed, just wait for the user to leave)
                    consoleIn.nextLine();
                }
                else if(code == 4) {
                    System.out.println("That is not a valid input.");
                }
                else if(code == 5) {
                }
                else if(code == 6) {
                    System.out.println("That exhibition does not exist.");
                }
                else if(code == 7) {
                    System.out.println("You can't move there.");
                }
                else if(code == 8) {
                    System.out.println("Thank you for visiting the museum!");
                    playing = false;
                    writer.close();
                    System.exit(0);
                }
                else if(code == 9){
                    System.out.println("The painting URL could not be reached.");
                }
            }
        }
        consoleIn.close();
    }
    //get the name of the map file used (like map1)
    public static String getName() {

        Scanner mapIn = new Scanner(System.in);
        String mapName = mapIn.nextLine();
        return mapName;
    }
}
