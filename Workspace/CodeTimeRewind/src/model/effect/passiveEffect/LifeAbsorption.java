package model.effect.passiveEffect;

/**
 * 
 * This is a subclass of Passive effect that contains the life absorption effect
 * 
 * @author Mathieu Rabot
 *
 */
public class LifeAbsorption extends PassiveEffect {
	private final static boolean ISACTIVATEDBEGINNING = false;

	/**
	 * This is the constructor for this class
	 * 
	 * @param extraHealing This is the pourcent of life that the character will restore
	 */
	public LifeAbsorption(double extraHealing) {
		super(extraHealing, ISACTIVATEDBEGINNING);

	}

	/**
	 * This method start the life absorption effect
	 * 
	 * @param damageInflicated That's the damage the character dealt
	 * @return It returns the life point that the character will restore
	 */
	public int start(int damageInflicated) {
		return (int) Math.round(damageInflicated * this.extraHealing);
	}
}
