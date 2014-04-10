package dungeonRPGPackage;
/**
 * 
 * @author jordan
 *
 */
public class Hero {
	private Map map;
	private double maxHealth;		//the maximum health the hero has
	private double currHealth;		//how much health the hero currently has
	private Item inventory[][];		//hero's inventory which holds items like weapons and potions
	private String name;			//hero's name, picked by user
	private int x,y;				//hero's x and y coordinate on the map
	private Weapon weapon;			//hero's equipped weapon
	private Shield shield;			//hero's equipped shield
	
	/**
	 * Constructor for hero: sets default maxHealth value to 100 and initializes the inventory.
	 * Also equips a shield and weapon.
	 */
	public Hero(String name, Map map, Weapon weapon, Shield shield){
		this.name = name;
		this.maxHealth = 100+shield.getHpBoost();
		this.weapon = weapon;
		this.map = map;
		this.inventory = new Item[10][10];
	}
	
	/**
	 * 
	 * @param destX : the x-coordinate of the destination tile
	 * @param destY : the y-coordinate of the destination tile
	 * @return : 0 on success, and -1 on failure
	 */
	public int move(int destX, int destY){
		if(canMove(destX, destY) == false){
			return -1;
		}
		else{
			this.tile = this.map.tileArray[destX][destY];
			return 0;
		}
	}
	
	/**
	 * Tests whether or not the hero can move to the map tile specified
	 * by destX and destY.
	 * 
	 * @param destX : the x-coordinate of the destination tile
	 * @param destY : the y-coordinate of the destination tile
	 * @return : true if the hero can move to the tile, otherwise false
	 */
	private boolean canMove(int destX, int destY){
		if(destX < 0 || destY < 0 || destX >= this.map.getWidth() || destY >= this.map.getHeight()){
			return false;
		}
		
		return !(this.map.tileArray[destX][destY].isBlocked());
	}
	
	public Tile getTile(){
		return this.tile;
	}
	
	public void setMap(Map map){
		this.map = map;
	}
	
	public double getCurrHealth(){
		return this.currHealth;
	}
	
	public double getMaxHealth(){
		return this.maxHealth;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setCurrHealth(double health){
		this.currHealth = health;
	}
	
	public void setMaxHealth(double maxHealth){
		this.maxHealth = maxHealth;
	}
	
}
