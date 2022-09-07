package critters;
/*
 * CRITTERS Critter4.java
 * <Samip Poudel>
*/



import java.util.List;



/**
 * Critter4
 * This critter behaves like a Squirtle 
 * It will run away from Pikachu and Bulbasur
 * it'll engaged in a fight with others
 */

public class Critter4 extends Critter {
	
	boolean shiny;
	
	public Critter4() {
		if(getRandomInt(75) < 10) {
			shiny = true;
		}
		else shiny = false;
	}
	
	@Override
    public String toString() {
        return "4";
    }
	
	 
	@Override
	public void doTimeStep() {
		walk(0);
		
		//reproduce
        if(this.getEnergy() > 100) {
        	Critter4 child = new Critter4();
            
            reproduce(child, Critter.getRandomInt(8));
        }
	}

	@Override
    /**
     * Critter4(Squirtle) will fight if energy > 10 or opponent is a clover. Critter4 will not fight Critter1(Pikachu) or Critter2(Bulbasaur)
     * If it doesn't want to fight, it will attempt to escape with a chance to fail
     * @param opponent
     * @return willFight  whether Critter decides to fight
     */
	public boolean fight(String opponent) {
		if(opponent.equals("1") || opponent.equals("2")) {
			run(getRandomInt(8));
		}
		//Eat clovers
		if(opponent.equals("@")) {
			return true;
		}
		if (getEnergy() > 10) return true; return false;
		}

    public static String runStats(List<Critter> critters4) {

    	String str = "";
//      System.out.print("" + critters.size() + " critters as follows -- ");
    	str += "" + critters4.size() +" total Critter 4's   " + "\n";
    	return str;
    }

	public void test(List<Critter> l) {
		
	}
	
	@Override
	public CritterShape viewShape() {
		return CritterShape.TRIANGLE;
	}
	
	@Override
    public javafx.scene.paint.Color viewOutlineColor() {
        return javafx.scene.paint.Color.RED;
    }
    
    @Override
    public javafx.scene.paint.Color viewFillColor() {
        return javafx.scene.paint.Color.AQUA;
    }

}
