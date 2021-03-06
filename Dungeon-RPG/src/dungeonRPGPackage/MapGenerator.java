package dungeonRPGPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import dungeonRPGPackage.Map.Tile;

/**
 * Class that parses a textfile to generate a Map for the Dungeon RPG game
 * @author rrienton
 *
 */
public class MapGenerator {
	
	final static String GRASS = "0";
	final static String ROCK = "1";
	final static String NONE = "2";
	final static String FLOOR = "3";
	final static String WATER = "4";
	final static String TREE = "5";
	final static String LAVA = "6";
	final static String DRAGON = "7";
	final static String SHOP = "8";
	
	/**
	 * Generates a Map based on a textfile
	 * @param textFile	string name of .txt file where map is described
	 * @return	new Map for the dungeon rpg
	 */
	public Map generateMap(String textFile, int entranceLocX, int entranceLocY, int exitLocX, int exitLocY){
		Map dungeonMap = new Map(entranceLocX,entranceLocY,exitLocX,exitLocY);
		
		//parse the text file
		String[][] mapArray = parseText(textFile);
		
		//setup the map tiles
		for (int row = 0; row < Map.ARRAYSIZE; row++) {
            for (int col = 0; col < Map.ARRAYSIZE; col++) {
            	//System.out.println(mapArray[row][col]);
            	switch (mapArray[col][row]) {
            		case GRASS:	
            			dungeonMap.getTileArray()[row][col] = Tile.GRASS;
            			break;
            		case ROCK:	
            			dungeonMap.getTileArray()[row][col] = Tile.ROCK;
            			break;
            		case NONE:	
            			dungeonMap.getTileArray()[row][col] = Tile.NONE;
            			break;
            		case FLOOR:
            			dungeonMap.getTileArray()[row][col] = Tile.FLOOR;
            			break;
            		case WATER:
            			dungeonMap.getTileArray()[row][col] = Tile.WATER;
            			break;
            		case TREE:
            			dungeonMap.getTileArray()[row][col] = Tile.TREE;
            			break;
            		case LAVA:
            			dungeonMap.getTileArray()[row][col] = Tile.LAVA;
            			break;
            		case DRAGON:
            			dungeonMap.getTileArray()[row][col] = Tile.DRAGON;
            			break;
            		case SHOP:
            			dungeonMap.getTileArray()[row][col] = Tile.SHOP;
            			break;
            	}
            }
        }
		
		return dungeonMap;
	}
	
	private String[][] parseText(String textFile){
		FileReader input = null;
		try {
			input = new FileReader(textFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		String[][] array = new String[Map.ARRAYSIZE][Map.ARRAYSIZE];
		int row = 0;
		
		try {
			while ( (myLine = bufRead.readLine()) != null)
			{    
				array[row++] = myLine.split(" ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			bufRead.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
}
