package model.effect.passiveEffect;

import model.effect.Effect;

public class PassiveEffect extends Effect {

	protected double extraHealing;

	public PassiveEffect(double extraHealing) {
		this.extraHealing = extraHealing;
	}
}
