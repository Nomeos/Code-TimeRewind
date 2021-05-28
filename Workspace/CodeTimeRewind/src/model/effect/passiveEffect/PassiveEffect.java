package model.effect.passiveEffect;

import model.effect.Effect;

/**
 * This is a subclass of effect that contains every Passive effect
 * 
 * @author Mathieu Rabot
 *
 */
public class PassiveEffect extends Effect {

	protected double extraHealing;
	protected boolean isActivatedBeginning;

	/**
	 * This is the constructor for this class
	 * 
	 * @param extraHealing         This is the pourcent of life that the character
	 *                             will restore
	 * @param isActivatedBeginning This a boolean that say if the debuff is activate
	 *                             at the beginning of the turn or a the end
	 */
	public PassiveEffect(double extraHealing, boolean ISACTIVATEDBEGINNING) {

		this.isActivatedBeginning = ISACTIVATEDBEGINNING;
		this.extraHealing = extraHealing;
	}
}
