package model.effect.passiveEffect;

import model.effect.Effect;

public class PassiveEffect extends Effect {

	protected double extraHealing;

	protected boolean isActivatedBeginning;
	public PassiveEffect(double extraHealing,boolean ISACTIVATEDBEGINNING) {
		
		this.isActivatedBeginning = ISACTIVATEDBEGINNING;
		this.extraHealing = extraHealing;
	}
}
