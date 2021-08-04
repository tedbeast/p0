import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.fail;

public class mapTest {
    static String name1 = "testMap1";
    static String name2 = "testMap2";
    static map map1;
    static map map2;
    @BeforeClass
    public static void initializer(){
       // BasicConfigurator.configure();
    }
    @Before
    public void setUp(){
        map1 = new map(name1, null);
        map2 = new map(name2, null);
    }
    @After
    public void tearDown(){
        map1 = null;
        map2 = null;
    }
    //Empty maps should be able to complete the constructor.
    @Test
    public void emptyTest(){
        assertTrue(map1.isValid());
    }
    //The painting should be located at the proper position.
    @Test
    public void singlePaintingTest(){
        Integer[] correctCoord = {1,1};
        assertTrue(map2.getPaintingCoords('A')[0]==1 && map2.getPaintingCoords('A')[1]==1);
    }
    //Map should allow the user to move if they are moving in the direction of a free space.
    @Test
    public void moveIntoEmptyTest(){
        assertTrue(map2.command('d')==7);
    }
    //Map should not allow the user to move if they are moving in the direction of a wall.
    @Test
    public void moveIntoWallTest(){
        assertTrue(map2.command('a')==1);
    }
    //Map should inform the user if they attempt to view an empty space.
    @Test
    public void viewEmptyTest(){
        assertTrue(map2.command('f')==2);
    }
    //Map should be able to display a painting if they press 'f' on an exhibit
    @Test
    public void viewPaintingTest(){
        map2.command('a');
        map2.command('a');
        assertTrue(map2.command('f')==3);
    }
    //Map should be able to move the player to a painting location if they input an exhibit name.
    @Test
    public void jumpToPaintingTest(){
        map2.command('A');
        assertTrue(map2.command('f')==3);
    }
    //Constructor should not complete if the file does not exist.
    @Test
    public void noMapTest(){

        map map3 = new map("This file name does not exist.", null);
        assertTrue(!(map3.isValid()));
    }
}
