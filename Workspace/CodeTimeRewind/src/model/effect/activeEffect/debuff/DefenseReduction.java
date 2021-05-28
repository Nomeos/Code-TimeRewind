package model.effect.activeEffect.debuff;

import lombok.Getter;
import lombok.Setter;
import model.livingEntity.LivingEntity;

@Getter
@Setter
/**
 * This is a subclass of Debuff that contains the defense reduction effect
 * 
 * @author Mathieu Rabot
 *
 */
public class DefenseReduction extends DebuffEffect {
	private final static float DEFENSEREDUCTION = 0.30f;
	private final static String DISPLAYEFFECT = "-15% Defense";
	private final static boolean ISACTIVATEDBEGINNING = true;

	/**
	 * This is the constructor of this class
	 * 
	 * @param pourcentChance         This is the pourcent of chance that the debuff
	 *                               will be apply
	 * @param numberTurnEffectActive This is the number of turn that the buff is
	 *                               active
	 * @param isAppliedBeginning     This a boolean that say if the buff is apply at
	 *                               the beginning of the turn or a the end
	 */
	public DefenseReduction(double pourcentChance, int numberTurnEffectActive, boolean isAppliedBeginning) {
		super(DISPLAYEFFECT, pourcentChance, numberTurnEffectActive, isAppliedBeginning, ISACTIVATEDBEGINNING);

	}

	/**
	 * This method apply the effect of the debuff to the current character
	 * 
	 * @param target current entity that will have the effect
	 */
	@Override
	public void applyEffect(LivingEntity target) {
		target.setDefense(Math.round(target.getDefense() - (target.getDefense() * DEFENSEREDUCTION)));
	}
}
