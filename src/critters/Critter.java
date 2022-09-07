package critters;
/*
 * CRITTERS GUI Critter.java
 * <Samip Poudel>
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import critters.InvalidCritterException;
import critters.Params;
import javafx.geometry.HPos;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/*
 * See the PDF for descriptions of the methods and fields in this
 * class.
 * You may add fields, methods or inner classes to Critter ONLY
 * if you make your additions private; no new public, protected or
 * default-package code or data can be added to Critter.
 */

public abstract class Critter {

    /* START --- NEW FOR PROJECT 5 */
    public enum CritterShape {
        CIRCLE,
        SQUARE,
        TRIANGLE,
        DIAMOND,
        STAR
    }

    /* the default color is white, which I hope makes critters invisible by default
     * If you change the background color of your View component, then update the default
     * color to be the same as you background
     *
     * critters must override at least one of the following three methods, it is not
     * proper for critters to remain invisible in the view
     *
     * If a critter only overrides the outline color, then it will look like a non-filled
     * shape, at least, that's the intent. You can edit these default methods however you
     * need to, but please preserve that intent as you implement them.
     */
    public javafx.scene.paint.Color viewColor() {
        return javafx.scene.paint.Color.WHITE;
    }

    public javafx.scene.paint.Color viewOutlineColor() {
        return viewColor();
    }

    public javafx.scene.paint.Color viewFillColor() {
        return viewColor();
    }

    public abstract CritterShape viewShape();

    protected final String look(int direction, boolean steps) {
    	
    	for(Critter critter: population) {
	    	energy -= Params.LOOK_ENERGY_COST;
	    	
			    	if(!steps) {
			    		critter.step(1, direction);
			    		int x = x_coord;
			    		int y = y_coord;
			    		return look4(this, x, y);
			    		
			    		
			    	}
			    	
			    	//look if its true == 2step // run
			    	if(steps) {
			    		critter.step(2, direction);
			    		int x = x_coord;
			    		int y = y_coord;
			    		return look4(this, x, y);
			    	}
	    	//}
			    	//return "";
    	}
    	
    	return "";
    	
    }
    
    private static String look4(Critter critter, int x, int y) {
    	
    	for(Critter c: population) {
    		if(c.x_coord == x && c.y_coord == y && c != null) {
    			return c.toString();
    		}
    		
    	}
    	
    	return "";
    }
    
    
    

    /**
     * Prints out how many Critters of each type there are on the
     * board.
     *
     * @param critters List of Critters.
     */
    public static String runStats(List<Critter> critters) {
    	//TODO: send to GUI
    	String str = "";
//        System.out.print("" + critters.size() + " critters as follows -- ");
    	str += "" + critters.size() + " critters as follows -- ";
        Map<String, Integer> critter_count = new HashMap<String, Integer>();
        for (Critter crit : critters) {
            String crit_string = crit.toString();
            critter_count.put(crit_string,
                    critter_count.getOrDefault(crit_string, 0) + 1);
        }
        String prefix = "";
        for (String s : critter_count.keySet()) {
//            System.out.print(prefix + s + ":" + critter_count.get(s));
        	str += prefix + s + ":" + critter_count.get(s);
            prefix = ", ";
        }
        str += "\n";
        
        return str;
    }


    /**
     * DisplayWorld
     * @param pane
     */
    public static void displayWorld(Object pane) {
        // TODO Implement this method
    	GridPane world = (GridPane) pane;
    	
    	int size = 500;
    	int cellSize = size / Params.WORLD_WIDTH;
    	Double cs = (double) cellSize;
    	int h = Params.WORLD_HEIGHT;
    	int w = Params.WORLD_WIDTH ;
    	world.setPrefHeight(size);
    	world.setPrefWidth(size);
    	
    	//world.getHgap(pane);
    	for(int i = 0; i < w; i++) {
    		RowConstraints row = new RowConstraints();
    		row.setVgrow(Priority.SOMETIMES);
    		world.getRowConstraints().add(row);
    	}
    	
    	for(int j = 0; j < h; j++) {
    		ColumnConstraints col = new ColumnConstraints();
    		col.setHgrow(Priority.SOMETIMES);
    		world.getColumnConstraints().add(col);
    	}
    	
    	//should add the Char of the critter to the world
    	for(Critter critter : population) {
    		int x = critter.x_coord;
    		int y = critter.y_coord;
    		Shape s = null;
    		switch(critter.viewShape()) {
			case CIRCLE: 
				Circle c = new Circle();
				
				s = c;
				c.setRadius(cellSize/2);
				break;
			case DIAMOND:
				Rectangle d = new Rectangle();

				d.setRotationAxis(new Point3D(0, 0, 1));
				d.setRotate(90);
				d.setFill(critter.viewFillColor());
				d.setWidth(cellSize);
				d.setHeight(cellSize);
				
				s = d;
				
				break;
			case SQUARE: 
				Rectangle r = new Rectangle();
				
				r.setWidth(cellSize);
				r.setHeight(cellSize);
//				System.out.println("Square");
				
				s = r;
			
				break;
			case STAR:
				Polygon st = new Polygon();
				
				st.getPoints().setAll(
						0.0, cs*.3,
						cs*.4, cs*.3,
						cs*.5, 0.0,
						cs*.6, cs*.3,
						cs, cs*.3,
						cs*.7, cs*.6,
						cs*.8, cs,
						cs*.5, cs*.8,
						cs*.2, cs,
						cs*.3, cs*.6);
				
				s = st;
//				st.getPoints().addAll(dim);
				break;
			case TRIANGLE:
				Polygon tri = new Polygon();
				tri.getPoints().setAll(
						0.0, cs,
						cs/2, 0.0,
						cs, cs);
				
				s = tri;
				break;
			default:
				break;
    		
    		}
    		
    		s.setFill(critter.viewFillColor());
			s.setStroke(critter.viewOutlineColor());
			
			world.add(s, x, y);
			GridPane.setValignment(s, VPos.CENTER);
			GridPane.setHalignment(s, HPos.CENTER);
    	}
    }

	/* END --- NEW FOR PROJECT 5
			rest is unchanged from Project 4 */

    private int energy = 0;

    private int x_coord;
    private int y_coord;
    private boolean moved;
    
    private static int timestep = 0; //current time-step of world
    

    private static List<Critter> population = new ArrayList<Critter>();
    private static List<Critter> babies = new ArrayList<Critter>();

    /* Gets the package name.  This assumes that Critter and its
     * subclasses are all in the same package. */
    private static String myPackage;

    static {
        setMyPackage(Critter.class.getPackage().toString().split(" ")[1]);
    }

    private static Random rand = new Random();

    
    /**
     * Generates a random integer
     * 
     * @param max	maximum integer that can generate
     * @return	a random integer from 0-max
     */
    public static int getRandomInt(int max) {
        return rand.nextInt(max);
    }

    /**
     * Seeds the random number generator
     * @param new_seed	seed number
     */
    public static void setSeed(long new_seed) {
        rand = new Random(new_seed);
        System.out.println("set seed: " + new_seed); //DEBUGGING
    }

    /**
     * create and initialize a Critter subclass.
     * critter_class_name must be the unqualified name of a concrete
     * subclass of Critter, if not, an InvalidCritterException must be
     * thrown.
     *
     * @param critter_class_name
     * @throws InvalidCritterException
     */
    public static void createCritter(String critter_class_name)
            throws InvalidCritterException {
    	Class<?> critter;
    	try {
    		critter = Class.forName(getMyPackage() +"." + critter_class_name);
    		@SuppressWarnings("deprecation")
			Critter newCritter = (Critter) critter.newInstance();
    		
    		//Initialize critter
    		newCritter.energy = Params.START_ENERGY;
    		newCritter.x_coord = getRandomInt(Params.WORLD_WIDTH);
    		newCritter.y_coord = getRandomInt(Params.WORLD_HEIGHT);
    		newCritter.moved = false;
    		
    		population.add(newCritter);
    		
    	}
    	catch(ClassNotFoundException | IllegalAccessException | InstantiationException e3){
    		throw new InvalidCritterException(critter_class_name);
    		
    	}
    }

    /**
     * Gets a list of critters of a specific type.
     *
     * @param critter_class_name What kind of Critter is to be listed.
     *        Unqualified class name.
     * @return List of Critters.
     * 
     * @throws InvalidCritterException
     */
    public static List<Critter> getInstances(String critter_class_name)
            throws InvalidCritterException {
        
    	List<Critter> list = new ArrayList<>();
    	try {
    		Class<?> critter = Class.forName(getMyPackage() +"." + critter_class_name);
    		for(Critter critter1: population) {
    			if(critter.isInstance(critter1)) {
    				list.add(critter1);
    			}
    			
    		}
    		
    	}
    	catch(ClassNotFoundException e3){
    		throw new InvalidCritterException(critter_class_name);
    		
    	}
        return list;
    }
    

    
    /**
     * clearWorld
     * Clear the world of all critters, dead and alive
     */
    public static void clearWorld() {
        
    	babies.clear();
    	population.clear();
    	
    }

    
    /**
     * clearWorld
     * Perform entire world's time step actions
     */
    public static void worldTimeStep() {
        
    	//increment ts
    	timestep++;
    	
    	//do Time Steps
    	doTimeSteps();
    	
    	//Do Fights (
    	doEncounters();
    	
    	//update resting energy & remove dead
    	Iterator<Critter> it = population.iterator();
    	while (it.hasNext()) {
    		Critter c = it.next();
    		//update energy
    		c.energy -= Params.REST_ENERGY_COST;
    		
    		if(c.energy <= 0) {
    			it.remove();
    		}
    		
    	}
    	
    	
    	//Generate Clovers
    	try {
    		genClover();
    	}
    	catch(InvalidCritterException e) {
    		
    	}
    	
    	
    	//Move babies to population
    	population.addAll(babies);
    	babies.clear();
    }

    
    /**
     * doTimeSteps for individual Critters
     * 
     */
	private static void doTimeSteps() {
    	for(Critter critter : population) {
    		critter.moved = false;
    		critter.doTimeStep();
    	}
    }
	
    /**
     * Decide what happens when 2 critters in same space
     * 
     */
    private static void doEncounters() {
    	
    	//Find Critters in same space (mapping)
    	HashMap<String, ArrayList<Critter>> worldMap = generateMapping();
    	
    	//For each spot with >= 2 critters
    	for(String coord : worldMap.keySet()) {
    		ArrayList<Critter> occupied = worldMap.get(coord);
    		int x = occupied.get(0).x_coord;
    		int y = occupied.get(0).y_coord;
    		//Can only be one left
    		while(occupied.size() > 1) {
    			Critter A = occupied.get(0);
    			Critter B = occupied.get(1);
    			
    			
    			boolean fightA = A.fight(B.toString());
    			boolean fightB = B.fight(A.toString());
    			
    			
    			//Check if critters ran or died
    			Iterator<Critter> it = occupied.iterator();
    	    	while (it.hasNext()) {
    	    		Critter c = it.next();
    	    		//died
    	    		if(c.energy <= 0) {
    	    			it.remove();
    	    		}
    	    		//moved
    	    		if(c.x_coord != x || c.y_coord != y) {
    	    			it.remove();
    	    		}
    	    		
    	    	}
    			
    			
    			
    			//Both critters still in space?
    	    	if(occupied.contains(A) && occupied.contains(B)) {
    	    		
    	    		//FIGHT
	    			int rollA = 0, rollB = 0;
	    			
	    			//Does critter A want fight?
	    			if(fightA) {
	    				rollA = getRandomInt(A.energy);
	    			}
	    				
	    			//Does critter B want fight?
	    			if(fightB) {
	    				rollB = getRandomInt(B.energy);
	    			}
	    			
	    			//A wins
	    			if(rollA > rollB) {
	    				//add half of energy from B
	    				A.energy += B.energy/2;
	    				
	    				//B dies
	    				B.energy = 0;
	    				occupied.remove(B);
	    			}
	    			
	    			//B wins (at ties too)
	    			else {
	    				B.energy += A.energy/2;
	    				
	    				//A dies
	    				A.energy = 0;
	    				occupied.remove(A);	
	    			}
    	    	}
    		}
    	}
    	
    	
    	
    	
    	
    }
    /**
     * create a mapping of critters  in pop based on positions
     * @param none
     * @returns Map<String "x,y" , Set of Crits at that x,y>
     * (two critters in same spot added to set at that spot)
     */
    private static HashMap<String, ArrayList<Critter>> generateMapping() {
    	HashMap<String, ArrayList<Critter>> worldMap = new HashMap<String, ArrayList<Critter>>();
    	
    	for(Critter critter: population) {
    		String coords = String.valueOf(critter.x_coord) + "," + String.valueOf(critter.y_coord);
    		
    		//coord exits (add critter to set in current coord)
    		if(worldMap.containsKey(coords)) {
    			ArrayList<Critter> cSet = worldMap.get(coords);
    			
    			cSet.add(critter);
    			
    			worldMap.put(coords, cSet);
    		}
    		
    		//
    		else {
    			ArrayList<Critter> cSet = new ArrayList<Critter>();
    			
    			cSet.add(critter);
    			
    			worldMap.put(coords, cSet);
    			
    			
    		}
    		
    	}
    	
    	return worldMap;
    }
    
    /**
     * Generate clovers in random spaces
     * @throws InvalidCritterException
     * 
     */
    private static void genClover() throws InvalidCritterException{
    	for(int i = 0; i < Params.REFRESH_CLOVER_COUNT; i++) {
    		createCritter("Clover");
    	}
    }
    
    /** simulates the actions taken
    *	(if any) by a single critter
    */
    public abstract void doTimeStep();

    
    /**
     * 
     * @param oponent
     * @return willFight  whether Critter decides to fight
     */
    public abstract boolean fight(String oponent);

    /** a one-character long string that visually depicts your critter
     * in the ASCII interface 
     */
    public String toString() {
        return "";
    }

    /**
     * get critter energy
     */
    protected int getEnergy() {
        return energy;
    }

    /**
     * move Critter 1 step in direction
     * @param direction  # from 0-7 where 0 is right then goes counterclockwise
     */
    protected final void walk(int direction) {
        
    	energy -= Params.WALK_ENERGY_COST;
    	
    	if(!moved) {
    		step(1, direction);
        	moved = true;
    	}
    	
    }

    /**
     * move Critter 2 steps in direction
     * @param direction  # from 0-7 where 0 is right then goes counterclockwise
     * 
     */
    protected final void run(int direction) {
       
    	energy -= Params.RUN_ENERGY_COST;
    	//check if Critter moved this time-step (Can't move twice
    	if(!moved) {
    		step(2, direction);
        	moved = true;
    	}
    	

    }
    
    /**
     * step n steps in a direction
     * @param 	n  # of steps		
     * 			direction  # from 0-7 where 0 is right then goes counterclockwise
     */
    private void step(int n, int direction) {
    	switch (direction) {
    	case 0:
    		//x_coord += n;
    		x_coord = stepX(n);
    		break;

    	case 1:
    		//x_coord += n;
    		//y_coord -= n;
    		x_coord = stepX(n);
    		y_coord = stepY(-n);
    		break;
    		
    	case 2:

    		//y_coord -= n;
    		y_coord = stepY(-n);
    		break;
    	
    	case 3:
    		//x_coord -= n;
    		//y_coord -= n;
    		x_coord = stepX(-n);
    		y_coord = stepY(-n);
    		break;
    		
    	case 4:
    		//x_coord -= n;
    		x_coord = stepX(-n);
    		break;
    		
    	case 5:
    		//x_coord -= n;
    		//y_coord += n;
    		x_coord = stepX(-n);
    		y_coord = stepY(n);
    		break;
    		
    	case 6:
    		
    		//y_coord += n;
    		y_coord = stepY(n);
    		break;
    		
    	case 7:
    		//x_coord += n;
    		//y_coord += n;
    		x_coord = stepX(n);
    		y_coord = stepY(n);
    		break;
    	}
    	
    }
    
    /**
     * It makes sure moving in the X-direction models a 2-d Torus
     * @param nSteps Amount of steps to take in the x direction
     * @returns x_coord It returns the X position of the Critter
     * 
     */
    private int stepX (int nSteps) {
    	x_coord = x_coord + nSteps;
    	
    		if (x_coord <= 0 ) {
    			x_coord = x_coord + Params.WORLD_WIDTH;
    		}
    	
    		if (x_coord >= Params.WORLD_WIDTH) {
    			x_coord = x_coord - Params.WORLD_WIDTH;
    		}
    	
    	return x_coord;
    	
    }
    
    
    /**
     * It makes sure moving in the Y-direction models a 2-d Torus
     * @param nSteps Amount of steps to take in the Y direction
     * @returns y_coord It returns the Y position of the Critter
     * 
     */
    private int stepY (int nSteps) {
    	y_coord = y_coord + nSteps;
    	
    		if (y_coord <= 0 ) {
    			y_coord = y_coord + Params.WORLD_HEIGHT;
    		}
    	
    		if (y_coord >= Params.WORLD_HEIGHT) {
    			y_coord = y_coord - Params.WORLD_HEIGHT;
    		}
    	
    	return y_coord;
    	
    }

    /**
     * Create a child (new Critter object)
     * @param offspring 	a Critter child
     * 		  direction 	(with respect to parent)
     */
    protected final void reproduce(Critter offspring, int direction) {
        
    	//Check if parent has energy
    	if(this.energy >= Params.MIN_REPRODUCE_ENERGY) {
    		//Update energy (round up for parent)
    		int available = this.energy;
    		offspring.energy = available/2;
    		available -= available/2;
    		
    		
    		this.energy = available;
    		
    		//Place child
    		offspring.x_coord = this.x_coord;
    		offspring.y_coord = this.y_coord;
    		offspring.step(1, direction);
    		
    		//add to babies (will be updated end of timestep)
    		babies.add(offspring);

    	}	
    }

    
    /**
     * myPackage Getter
     */
    public static String getMyPackage() {
		return myPackage;
	}

    /**
     * myPackage Setter
     */
	public static void setMyPackage(String myPackage) {
		Critter.myPackage = myPackage;
	}

	/**
     * The TestCritter class allows some critters to "cheat". If you
     * want to create tests of your Critter model, you can create
     * subclasses of this class and then use the setter functions
     * contained here.
     * <p>
     * NOTE: you must make sure that the setter functions work with
     * your implementation of Critter. That means, if you're recording
     * the positions of your critters using some sort of external grid
     * or some other data structure in addition to the x_coord and
     * y_coord functions, then you MUST update these setter functions
     * so that they correctly update your grid/data structure.
     */
    static abstract class TestCritter extends Critter {

        protected void setEnergy(int new_energy_value) {
            super.energy = new_energy_value;
        }

        protected void setX_coord(int new_x_coord) {
            super.x_coord = new_x_coord;
        }

        protected void setY_coord(int new_y_coord) {
            super.y_coord = new_y_coord;
        }

        protected int getX_coord() {
            return super.x_coord;
        }

        protected int getY_coord() {
            return super.y_coord;
        }

        /**
         * This method getPopulation has to be modified by you if you
         * are not using the population ArrayList that has been
         * provided in the starter code.  In any case, it has to be
         * implemented for grading tests to work.
         */
        protected static List<Critter> getPopulation() {
            return population;
        }

        /**
         * This method getBabies has to be modified by you if you are
         * not using the babies ArrayList that has been provided in
         * the starter code.  In any case, it has to be implemented
         * for grading tests to work.  Babies should be added to the
         * general population at either the beginning OR the end of
         * every timestep.
         */
        protected static List<Critter> getBabies() {
            return babies;
        }
    }
    
}
