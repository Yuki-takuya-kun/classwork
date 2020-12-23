import java.util.Vector;
/*
 * Created on Jul 6, 2003
 *
 */

/**
 * Crocodile - to simulate artificial life. Eats small fish.
 * 
 * @author iCarnegie av
 *
 */
public class Crocodile extends LivingBeing {
	
	/**
	 * The crocodile is born "alive". 
	 * Then it dies, becoming a corpse. 
	 */
	private static final String ALIVE = "alive";

	/**
	 * The crocodile is born "alive". 
	 * Then it dies, becoming a "dead" corpse. 
	 */
	private static final String DEAD = "dead";


	/**
	 * Energy expended to wade during a block of time.
	 */
	private static final int ENERGY_TO_LOOK_FOR_FOOD=10;

	private static final int ENERGY_TO_WADE = 50;
	/**
	 * Energy expended to eat once.
	 */
	private static final int ENERGY_TO_EAT = 10;
		
	/**
	 * Lowest possible energy needed for a baby to survive. 
	 */
	private static final int BABY_MIN_ENERGY = 1000;
	
	/**
	 * Maximum energy that a baby can store. 
	 */
	private static final int BABY_MAX_ENERGY = 2000;

	/**
	 * For each block of time, the min energy grows by a certain amount
	 */
	private static final int MIN_ENERGY_GROWTH_INCREMENT = 5;
	
	/**
	 * For each block of time, the max energy grows by a certain amount
	 */
	private static final int MAX_ENERGY_GROWTH_INCREMENT = 10; 

	// Concept example: final. since it is a constant 
	// Concept example: static. since only one value is needed 
	// 						irrespective of number of object instances 
	/**
	 * String constant - used to indicate the direction crocodile is facing.
	 */
	private static final String RIGHT = "right";

	/**
	 * String constant - used to indicate the direction crocodile is facing.
	 */
	private static final String LEFT = "left";

	/**
	 * String constant - used to indicate the direction crocodile is facing.
	 */
	private static final String UP = "up";

	/**
	 * String constant - used to indicate the direction crocodile is facing.
	 */
	private static final String DOWN = "down";

	/**
	 * Name of species
	 */
	private static final String SPECIES = "Crocodile";

	/**
	 * Row-wise location of the crocodile
	 */
	private int row;

	/**
	 * Column-wise location of the crocodile
	 */
	private int column;

	/**
	 * Is the crocodile dead or alive?
	 */
	private String deadOrAlive;
	
	/**
	 * Amount of energy the crocodile has.
	 */
	private int energy;

	/**
	 * Age expressed as blocks of time lived
	 */
	private int age = 0;

	/**
	 * Name of this crocodile.
	 */
	private final String name;

	/**
	 * The simulation to which this crocodile belongs.
	 * This is needed so the crocodile can send a message 
	 * to simulation and ask
	 * for prey (or predator) in the neighboring locations. 
	 * Prey is food. Food is good!
	 */
	private Simulation simulation;

	/**
	 * Minimum energy level needed to survive.
	 * The minimum could increase as the individual grows.
	 */
	private int minEnergy;
	
	/**
	 * Maximum energy level that the crocodile could carry.
	 * The maximum could change as the individual grows.
	 */
	private int maxEnergy;
	
	/**
	 * Which direction am I facing.
	 */
	private String direction=RIGHT;

	/**
	 * Number of Crocodiles created so far
	 */
	private static int nCrocodilesCreated = 0;

	/**
	 * Construct and initialize a Crocodile.
	 * 
	 * @param initialRow - the row at which the crocodile is located
	 * @param initialColumn - the column at which the crocodile is located
	 * @param initialSimulation - the simulation that the crocodile belongs to
	 */
	public Crocodile(
		int initialRow,
		int initialColumn,
		Simulation initialSimulation) {
		this.simulation=initialSimulation;
		this.deadOrAlive=ALIVE;
		this.row=initialRow;
		this.column=initialColumn;
		this.minEnergy=BABY_MIN_ENERGY;
		this.maxEnergy=BABY_MAX_ENERGY;
		this.energy = BABY_MAX_ENERGY;
		this.age = 0;
		name = SPECIES + nCrocodilesCreated;
		++nCrocodilesCreated;

	}

	/**
	 * Get the row at which the crocodile is located 
	 * 
	 * @return - the row of the crocodile's location. 
	 */		
	public int getRow() {
		return row;
	}

	/**
	 * Get the column at which the crocodile is located
	 * 
	 * @return - the column of the crocodile's location. 
	 */		
	public int getColumn() {
		return column;
	}

	/**
	 * Get the crocodile's age
	 * 
	 * @return the age of the crocodile expressed in blocks of time
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Color of the crocodile expressed in hex notation.
	 * For example, the "green-est" color is "#00FF00",
	 * "blue-est" is "#0000FF", the "red-est" is "#FF0000".
	 * 
	 * @return the rgb color in hex notation. preceded by a pound character '#'
	 */
	public String getColor() {
		return "#FFFFFF"; // default is white.
	}

	/**
	 * Get the name of this crocodile
	 * 
	 * @return the name of the crocodile.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the minimum energy needed to live.
	 * 
	 * @return the minimum energy needed for the crocodile to live.
	 */
	private int getMinEnergy() {
		return minEnergy;
	}
	
	/**
	 * get the maximum energy that the crocodile can carry.
	 * 
	 * @return the maximum energy the crocodile can carry.
	 */
	private int getMaxEnergy() {
		return maxEnergy;
	}
	
	/**
	 * Get the energy currently carried by the crocodile.
	 * 
	 * @return current energy level of the organism
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * Sets energy level.
	 * If new energy level is less than minimum energy level, the organism dies.
	 * New energy level is capped at maximum energy level.
	 */
	private void setEnergy(int newEnergy) {
		if(newEnergy>maxEnergy)
			energy=maxEnergy;
		else if(newEnergy<minEnergy)
			die();
		else
			energy=newEnergy;
	}

	/**
	 * Die: Change the deadOrAlive to DEAD.
	 */
	private void die() {deadOrAlive=DEAD;
	}

	/**
	 * Is the crocodile dead?
	 * 
	 * @return <code>true</code> if dead. <code>false</code>, otherwise.
	 */
	public boolean isDead() {
		return (deadOrAlive == DEAD);
	}

	/**
	 * Get the direction faced by the crocodile.
	 * 
	 * @return the facing direction.
	 */
	private String getDirection() {
		return direction;
	}

	/** 
	 * Is the crocodile hungry?
	 * 
	 * @return True, if hungry. False, otherwise.
	 */
	private boolean isHungry() {
		if(getEnergy()<=2*minEnergy)
			return true;
		else
		// Hungry, if current energy level is less than twice the 
		// amount needed for survival.
		return false;
	}

	/**
	 * Move the crocodile to a new row, if new row is within lake bounds.
	 * 
	 * @param newRow - the row to move to.
	 * @return the row moved to. Lake boundary limits movement. -1, if dead.
	 */
	private int moveToRow(int newRow) {
		if(newRow>=0&&newRow<=9)
			row = newRow;
		return row;
	}

	/**
	 * Move the crocodile to a new column, if new column is within lake bounds.
	 * 
	 * @param newColumn - the column to move to.
	 * @return the column moved to. Lake boundary limits movement.
	 */
	private int moveToColumn(int newColumn) {
		if(column>=0&&column<=9)
			column=newColumn;
		return column;
	}

	/**
	 * Get the species
	 * 
	 * @return a string indicating the species
	 */
	public String getSpecies() {
		return SPECIES;
	}

	/**
	 * The display mechanism to use to display a crocodile.
	 * 
	 * @return a constant defined in {@link Simulation#IMAGE Simulation} class
	 */
	public String getDisplayMechanism() {
		return "image";
	}

	/**
	 * Get the filename that contains an image of the Crocodile
	 * 
	 * @return filename of Crocodile image
	 */
	public String getImage() {

		String img=direction;
		switch(getDirection()) {
			case UP:
				img= "resource/Crocodile-up.gif";
				break;
			case DOWN:
				img= "resource/Crocodile-down.gif";
				break;
			case RIGHT:
				img= "resource/Crocodile-right.gif";
			case LEFT:
				img= "resource/Crocodile-left.gif";
		}
		return img;

	}

	Catfish catfish=null;
	private Catfish lookForFoodInNeighborhood() {
		setEnergy(energy-ENERGY_TO_LOOK_FOR_FOOD);
		Vector neighbors=simulation.getNeighbors(this.row,this.column,1);

			for(int i=0;i<neighbors.size();i++)
			{
				if(neighbors.get(i) instanceof Catfish)
				{
					catfish=(Catfish) neighbors.get(i);
					break;
				}
				else
					catfish=null;
			}
		return catfish;
	}
	/** 
	 * Wade to a new location if possible.
	 * Consumes some energy.
	 */
	private void wadeIfPossible() {
		if(isHungry())
		{
			lookForFoodInNeighborhood();

		if(catfish!=null && isHungry())
		{
			if(catfish.getColumn()==this.column-1)
			{
				direction=LEFT;
				moveToColumn(column-1);
			}
			else if(catfish.getColumn()==this.column+1)
			{
				direction=RIGHT;
				moveToColumn(column+1);
			}
			else if(catfish.getRow()==this.row-1)
			{
				direction=DOWN;
				moveToRow(row-1);
			}
			else if(catfish.getRow()==this.row+1)
			{
				direction=UP;
				moveToRow(row+1);
			}
			setEnergy(energy-ENERGY_TO_WADE);
		}
		else
		{
			switch (simulation.getRand().nextInt(4))
			{
				case 0:
					if(column==0)
						wadeIfPossible();
					else
					{
						direction=LEFT;
						moveToColumn(column-1);
						setEnergy(energy-ENERGY_TO_WADE);
					}
					break;
				case 1:
					if(column==10)
						wadeIfPossible();
					else {
						direction=RIGHT;
						moveToColumn(column+1);
						setEnergy(energy-ENERGY_TO_WADE);
					}break;
				case 2:
					if(row==0)
						wadeIfPossible();
					else{
						direction=UP;
						moveToRow(row-1);
						setEnergy(energy-ENERGY_TO_WADE);
					}break;
				case 3:
					if(row==10)
						wadeIfPossible();
					else {
						direction=DOWN;
						moveToRow(row+1);
						setEnergy(energy-ENERGY_TO_WADE);
					}

			}
		}
		}
	}

	/**
	 * Eat if food is available in the current location.
	 */
	private void eatIfPossible() {
		if(catfish!=null)
			if(isHungry() && catfish.getRow()==row && catfish.getColumn()==column)
			{
				setEnergy(catfish.getEnergy()+energy);
				catfish.die();
			}

	}

	/**
	 * Live for a block of time.
	 */
	public void liveALittle() {
		if(!isDead()) {
			++age;
			minEnergy += MIN_ENERGY_GROWTH_INCREMENT;
			maxEnergy += MAX_ENERGY_GROWTH_INCREMENT;
			wadeIfPossible();
			eatIfPossible();
		}
	}
}
