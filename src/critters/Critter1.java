package critters;
/*
 * CRITTERS Critter1.java
 * <Samip Poudel>
*/

import java.util.List;

/**
 * Critter1 (Pikachu) attacks when health over 10, runs otherwise
 */
public class Critter1 extends Critter {

	private boolean shiny;
	
	public Critter1(){
		//Chance of shiny
		if(getRandomInt(100) < 20) {
			shiny = true;
			
		}
		else shiny = false;
	}
	
    @Override
    public void doTimeStep() {
        walk(getRandomInt(8));
        
        //reproduce
        if(this.getEnergy() > 100) {
        	Critter1 child = new Critter1();
            
            reproduce(child, Critter.getRandomInt(8));
        }
        
        
    }

    @Override
    /**
     * Critter1(Pikachu) will fight if energy > 10 or opponent is a clover.
     * If it doesn't want to fight, it will attempt to escape with a chance to fail
     * Curious critter - will look around it's surroundings
     * @param opponent
     * @return willFight  whether Critter decides to fight
     */
    public boolean fight(String opponent) {
    	
        if (getEnergy() > 10) {
        	String l = look(getRandomInt(8), true);
        	if(l.equals("@")) {
        		return true;
        	}
        }
        
      //Eat clovers
      		if(opponent.equals("@")) {
      			return true;
      		}
      		
        else {
        	//escape
        	if(getRandomInt(100) > 50) {
    			run(getRandomInt(8));
    		}
    		return false;
        }
    }

    public String toString() {
        return "1";
    }
    
    public static String runStats(List<Critter> critters1) {

    	String str = "";
//      System.out.print("" + critters.size() + " critters as follows -- ");
    	str += "" + critters1.size() +" total Critter 1's   " + "\n";
    	return str;
    }
    
    public boolean getShiny(boolean s) {
    	if(shiny) {
    		s = true;
    		return true;
    	}
    	s = false;
    	return false;
    }

    public void test(List<Critter> l) {
    }

	@Override
	public CritterShape viewShape() {
		return CritterShape.DIAMOND;
	}
	
	@Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.RED;
    }
    
    @Override
    public javafx.scene.paint.Color viewFillColor() {
        return javafx.scene.paint.Color.YELLOW;
    }
	
}