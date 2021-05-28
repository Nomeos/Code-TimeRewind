package model.effect.activeEffect.buff;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.effect.activeEffect.ActiveEffect;
import model.livingEntity.LivingEntity;

@NoArgsConstructor
@Getter
@Setter
/**
 * This is a subclass of Active effect that contains every buff effect
 * 
 * @author Mathieu Rabot
 *
 */
public abstract class BuffEffect extends ActiveEffect {

	protected boolean isActivatedBeginning;

	/**
	 * This is the constructor of this class
	 * 
	 * @param displayEffect          This is the way the effect will be display on
	 *                               the screen
	 * @param numberTurnEffectActive This is the number of turn that the buff is
	 *                               active
	 * @param isAppliedBeginning     This a boolean that say if the buff is apply at
	 *                               the beginning of the turn or a the end
	 * @param isActivatedBeginning   This a boolean that say if the buff is activate
	 *                               at the beginning of the turn or a the end
	 */
	public BuffEffect(String displayEffect, int numberTurnEffectActive, boolean isAppliedBeginning,
			boolean isActivatedBeginning) {
		super(displayEffect, numberTurnEffectActive, isAppliedBeginning);
		this.isActivatedBeginning = isActivatedBeginning;
	}

	/**
	 * This method apply the effect of the buff to the current character
	 * 
	 * @param target current entity that will have the effect
	 */
	public abstract void applyEffect(LivingEntity target);

}
