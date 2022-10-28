package Game;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.javatuples.Pair;

public class Game {
	
	/*
	 * checkIfEntity() -> check if an entity is in the same position
	 * entityAction() -> sends message, possibly end the game
	 * gameTick() -> calls movePlayer(), checkIfEntity(), entityAction()
	 * 
	 */
	
	
	/*
	 * check player and treasure not on top of monster initially
	 * 
	 */
	
	/* attributes
	// - player x pos
	// - player y pos
	 * - treasure position x
	 * - treasure position y 
	 * - grid size n
	 * - starting pos
	 * - distance from treasure
	 */
	
	public int player_x;
	public int player_y;
	public int treasure_x;
	public int treasure_y;
	public int grid_size;
//	public int startPos;
	public int distTreasure;
	
	
	/*
	// methods
	// - checkIfWin
	 * - calculateDistance
	 * - moveNorth
	 * - moveSouth
	 * - moveEast
	 * - moveWest
	 * - checkBoundary
	 * - generateRandomPosition
	 * - printPlayerPos
	 * 
	 */
	
	/* ADD
	public Game(int grid_size) {
		this.grid_size = grid_size;
		this.player_x = genRandom(grid_size);
		this.player_y = genRandom(grid_size);
		this.treasure_x = genTreasureCoord(grid_size, player_x);
		this.treasure_y = genTreasureCoord(grid_size, player_y);
		this.calcDistance();
		this.printPosition();
	}
	
    private int grid_size;
    private int noOfTreas;
    private int noOfMons;
    
//    public int player_x;
//    public int player_y;
//    public int treasure_x;
//    public int treasure_y;
//    public int distTreasure;
//    
    
    /*
    // methods
    // - checkIfWin
     * - calculateDistance
     * - moveNorth
     * - moveSouth
     * - moveEast
     * - moveWest
     * - checkBoundary
     * - generateRandomPosition
     * - printPlayerPos
     *
     */
   
    public Game(int grid_size, int noOfTreas, int noOfMons) {
        
        this.grid_size = grid_size;
        this.noOfTreas = noOfTreas;
        this.noOfMons = noOfMons;
        
        Player p = new Player();
        
        //GameEntity[] gi = new GameEntity[1+noOfTreas+noOfMons];
        
        EntitiesInPlay entitiesInPlay = new EntitiesInPlay();
        
        
        
        entitiesInPlay.addEntity(p);
        
        
        for (int i=0; i < noOfTreas; i++) {
            Treasure treasure = new Treasure();
            
            
            while(entitiesInPlay.getEntity(treasure.getPosition()) != null) {
            	treasure.setPosition(grid_size);
            }
            
            entitiesInPlay.addEntity(treasure);
        }
        
        for (int i = 0; i < noOfMons; i++) {
            Monster monster = new Monster();
            
            entitiesInPlay.addEntity(monster);
        }
        
       
        
        
    }
	
	public void movePlayer(int direction) {
		
		switch(direction) {
		
		case 1: // North
			if (this.player_y >= this.grid_size - 1) {
				System.out.println("Cannot move out of bounds (north)");
			}
			else {
				this.player_y++;
			}
			break;
			
		case 2: // East
			if (this.player_x >= this.grid_size - 1) {
				System.out.println("Cannot move out of bounds (east)");
			}
			else {
				this.player_x++;
			}
			break;
			
		case 3: // South
			if (this.player_y <= 0) {
				System.out.println("Cannot move out of bounds (south)");
			}
			else {
				this.player_y--;
			}
			break;
			
		case 4: // West
			if (this.player_x <= 0) {
				System.out.println("Cannot move out of bounds (west)");
			}
			else {
				this.player_x--;
			}
			break;
			
			// Check if player encountered monster
			
		
		}
		
		this.calcDistance();
		printPosition();
		System.out.println(String.format("You are %d spaces from the treasure", this.distTreasure));
	}
	
	public void calcDistance() {
		int dist_x = Math.abs(this.player_x - this.treasure_x);
		int dist_y = Math.abs(this.player_y - this.treasure_y);
		this.distTreasure = dist_x + dist_y; 
	}
	
	public static int genTreasureCoord(int max, int pos) {
		int i_pos = genRandom(max);
		while (i_pos == pos) {
			i_pos = genRandom(max);
		}
		
		return i_pos;
	}
	
	public void printPosition() {
		double middle = grid_size/2;
		int relative_x = (int) Math.ceil(this.player_x - middle);
		int relative_y = (int) Math.ceil(this.player_y - middle);
		String horizontal_compass = (relative_x<0 ? "West" : "East");
		String vertical_compass = (relative_y<0 ? "South": "North");
		
		System.out.println(String.format("You are currently at %d %s, %d %s", Math.abs(relative_x), horizontal_compass, Math.abs(relative_y), vertical_compass));
	}
	
	public static int genRandom(int max) {
		
		return ThreadLocalRandom.current().nextInt(0,max);

	}
	
	public boolean checkIfWin() {
		this.calcDistance();
		return (this.distTreasure == 0);
	}


}
