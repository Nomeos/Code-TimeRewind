package model.effect.activeEffect.debuff;

import java.util.Random;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.effect.activeEffect.ActiveEffect;
import model.livingEntity.LivingEntity;

@NoArgsConstructor
@Getter
@Setter
/**
 * This is a subclass of Active effect that contains every debuff effect
 * 
 * @author Mathieu Rabot
 *
 */
public abstract class DebuffEffect extends ActiveEffect {

	protected double pourcentChance;
	protected Random rnd;
	protected boolean isAppliedBeginning;
	protected boolean isActivatedBeginning;

	/**
	 * This is the constructor of this class
	 * 
	 * @param displayEffect          This is the way the effect will be display on
	 *                               the screen
	 * @param numberTurnEffectActive This is the number of turn that the debuff is
	 *                               active
	 * @param isAppliedBeginning     This a boolean that say if the debuff is apply
	 *                               at the beginning of the turn or a the end
	 * @param isActivatedBeginning   This a boolean that say if the debuff is
	 *                               activate at the beginning of the turn or a the
	 *                               end
	 */
	public DebuffEffect(String DISPLAYEFFECT, double pourcentChance, int numberTurnEffectActive,
			boolean isAppliedBeginning, boolean isActivatedBeginning) {
		super(DISPLAYEFFECT, numberTurnEffectActive, isAppliedBeginning);
		this.isActivatedBeginning = isActivatedBeginning;
		this.pourcentChance = pourcentChance;

	}

	/**
	 * This method apply the effect of the buff to the current character
	 * 
	 * @param target current entity that will have the effect
	 */
	public abstract void applyEffect(LivingEntity target);

	/**
	 * This method check if the debuff is apply or not
	 * 
	 * @return It returns a boolean if the debuff is apply
	 */
	public boolean isDebuffApplied() {
		this.rnd = new Random();
		double resultRnd = Math.round((this.rnd.nextDouble() * 100) / 100);
		System.out.println(resultRnd + " >= " + pourcentChance);
		if (resultRnd >= pourcentChance) {
			return true;
		}
		return false;
	}

}
