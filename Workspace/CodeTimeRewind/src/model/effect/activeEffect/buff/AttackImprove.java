package model.effect.activeEffect.buff;

import lombok.Getter;
import lombok.Setter;
import model.livingEntity.LivingEntity;

@Getter
@Setter
/**
 * This is a subclass of Buff that contains the attack improvement effect
 * 
 * @author Mathieu Rabot
 *
 */
public class AttackImprove extends BuffEffect {

	private final static float ATTACKAIMPROVEMENT = 0.30f;
	private final static String DISPLAYEFFECT = "+15% Attack";
	private final static boolean ISACTIVATEDBEGINNING = true;

	/**
	 * This is the constructor of this class
	 * 
	 * @param numberTurnEffectActive This is the number of turn that the buff is
	 *                               active
	 * @param isAppliedBeginning     This a boolean that say if the buff is apply at
	 *                               the beginning of the turn or a the end
	 */
	public AttackImprove(int numberTurnEffectActive, boolean isAppliedBeginning) {
		super(DISPLAYEFFECT, numberTurnEffectActive, isAppliedBeginning, ISACTIVATEDBEGINNING);
	}

	/**
	 * This method apply the effect of the buff to the current character
	 * 
	 * @param target current entity that will have the effect
	 */
	@Override
	public void applyEffect(LivingEntity target) {
		target.setAttack(Math.round(target.getAttack() + (target.getAttack() * ATTACKAIMPROVEMENT)));
	}

}
