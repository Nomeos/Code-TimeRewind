package model.effect.activeEffect.buff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.effect.activeEffect.ActiveEffect;
import model.livingEntity.LivingEntity;


@NoArgsConstructor
@Getter
@Setter
public abstract class BuffEffect extends ActiveEffect {

	protected boolean isActivatedBeginning;
	public BuffEffect(String displayEffect, int numberTurnEffectActive, boolean isAppliedBeginning,
			boolean isActivatedBeginning) {
		super(displayEffect, numberTurnEffectActive, isAppliedBeginning);
		this.isActivatedBeginning = isActivatedBeginning;
	}
	public abstract void applyEffect(LivingEntity target);

}
