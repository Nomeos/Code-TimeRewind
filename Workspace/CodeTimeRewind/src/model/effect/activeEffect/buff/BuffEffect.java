package model.effect.activeEffect.buff;

import model.effect.activeEffect.ActiveEffect;
import model.entity.Entity;

public class BuffEffect extends ActiveEffect {

	public BuffEffect(String displayEffect, int numberTurnEffectActive, boolean isAppliedBeginning,
			boolean ISACTIVATEDBEGINNING) {
		super(displayEffect, numberTurnEffectActive, isAppliedBeginning, ISACTIVATEDBEGINNING);
	}
	public void applyEffect(Entity target) {

	}

}
