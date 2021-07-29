import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.fail;

public class mapTest {
    static String name1 = "testMap1";
    static String name2 = "testMap2";
    static map map1;
    static map map2;
    @Before
    public void setUp(){
        map1 = new map(name1);
        map2 = new map(name2);
    }
    @After
    public void tearDown(){
        map1 = null;
        map2 = null;
    }
    @Test
    public void emptyTest(){
        assertTrue(map1.isValid());
    }
    @Test
    public void singlePaintingTest(){
        Integer[] correctCoord = {1,1};
        assertTrue(map2.getPaintingCoords('A')[0]==1 && map2.getPaintingCoords('A')[1]==1);
    }
    @Test
    public void moveIntoEmptyTest(){
        assertTrue(map2.command('d')==7);
    }
    @Test
    public void moveIntoWallTest(){
        assertTrue(map2.command('a')==1);
    }
    @Test
    public void viewEmptyTest(){
        assertTrue(map2.command('f')==2);
    }
    @Test
    public void viewPaintingTest(){
        map2.command('a');
        map2.command('a');
        assertTrue(map2.command('f')==3);
    }
    @Test
    public void jumpToPaintingTest(){
        map2.command('A');
        assertTrue(map2.command('f')==3);
    }
    @Test
    public void noMapTest(){

        map map3 = new map("This file name does not exist.");
        assertTrue(!(map3.isValid()));
    }
}
