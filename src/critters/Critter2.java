package critters;
/*
 * CRITTERS Critter2.java
 * <Samip Poudel>
*/



import java.util.List;

/**
 * Critter2
 * This critter behaves like a Bulbasaur 
 * It will try to run away from Charmander but engaged with others
 */
public class Critter2 extends Critter {

	boolean shiny;
	
	public Critter2(){
		//Chance of shiny
			if(getRandomInt(100) < 20) {
				shiny = true;
				
			}
			else shiny = false;
		
	}
	
    @Override
    public void doTimeStep() {
        walk(0);
        
      //reproduce
        if(this.getEnergy() > 100) {
        	Critter2 child = new Critter2();
            
            reproduce(child, Critter.getRandomInt(8));
        }
    }

    @Override
    /**
     * Critter2(Bulb) will fight if energy > 10 or opponent is a clover. Critter2 will not fight Critter3(Charmander)
     * If it doesn't want to fight, it will attempt to escape with a chance to fail
     * @param opponent
     * @return willFight  whether Critter decides to fight
     */
    public boolean fight(String opponent) {
    	

    	if(opponent.equals("3")) {
    		//attempt run 50% chance
    		if(getRandomInt(100) > 50) {
    			run(getRandomInt(8));
    		}
    		return false;
    	}
    	//Eat clovers
			if(opponent.equals("@")) {
				return true;
			}
    	
        if (getEnergy() > 10) return true;
        
        return false;
    }

    public String toString() {
        return "2";
    }
    
    public static String runStats(List<Critter> critters2) {

    	String str = "";
//      System.out.print("" + critters.size() + " critters as follows -- ");
    	str += "" + critters2.size() +" total Critter 2's   " + "\n";
    	return str;
    }

    public void test(List<Critter> l) {
    }

    @Override
	public CritterShape viewShape() {
		return CritterShape.SQUARE;
	}
	
	@Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.RED;
    }
    
    @Override
    public javafx.scene.paint.Color viewFillColor() {
        return javafx.scene.paint.Color.GREEN;
    }
}