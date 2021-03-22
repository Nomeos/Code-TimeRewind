package model.effect.activeEffect.buff;

import model.effect.activeEffect.ActiveEffect;

public class BuffEffect extends ActiveEffect {
	protected boolean isAppliedBeginning;
	public BuffEffect(String displayEffect ,int numberTurnEffectActive, boolean isAppliedBeginning) {
		super(displayEffect, numberTurnEffectActive, isAppliedBeginning);
	}

}
