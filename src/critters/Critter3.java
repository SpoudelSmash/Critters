package critters;
/*
 * CRITTERS Critter3.java
 * <Samip Poudel>
*/



import java.util.List;



/**
 * Critter3
 * This critter behaves like a Charmander 
 * It will run away from squirtle but engaged in a fight with others
 */

public class Critter3 extends Critter {
	
	boolean shiny;
	
	public Critter3() {
		if(getRandomInt(50) < 10) {
			shiny = true;
		}
		else shiny = false;
	}
	
	@Override
    public String toString() {
        return "3";
    }
	
	 
	@Override
	public void doTimeStep() {
		//Look randomly
		String l = look(getRandomInt(8), true);
		if(l == "") {
			//nothing there then walk right
			walk(0);
		}
		else {
			run(getRandomInt(8));
		}
		//reproduce
        if(this.getEnergy() > 100) {
        	Critter3 child = new Critter3();
            
            reproduce(child, Critter.getRandomInt(8));
        }
	}

	@Override
    /**
     * Critter3(Charmander) will fight if energy > 10 or opponent is a clover. Critter3 will not fight Critter4(Squirtle)
     * If it doesn't want to fight, it will attempt to escape with a chance to fail
     * @param opponent
     * @return willFight  whether Critter decides to fight
     */
	public boolean fight(String opponent) {
		if(opponent.equals("4")) {
			run(getRandomInt(8));
		}
		//Eat clovers
			if(opponent.equals("@")) {
				return true;
			}
		if (getEnergy() > 10) return true; return false;
		}


    public static String runStats(List<Critter> critters3) {

    	String str = "";
//      System.out.print("" + critters.size() + " critters as follows -- ");
    	str += "" + critters3.size() +" total Critter 3's   " + "\n";
    	return str;
    }
	
	public void test(List<Critter> l) {
		
	}

	@Override
	public CritterShape viewShape() {
		return CritterShape.STAR;
	}
	
	@Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.RED;
    }
    
    @Override
    public javafx.scene.paint.Color viewFillColor() {
        return javafx.scene.paint.Color.ORANGE;
    }

}
