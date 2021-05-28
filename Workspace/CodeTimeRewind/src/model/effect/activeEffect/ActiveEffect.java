package model.effect.activeEffect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.effect.Effect;

@NoArgsConstructor
@Getter
@Setter
/**
 * This is a subclass of Active effect that contains every buff effect
 * 
 * @author Mathieu Rabot
 *
 */
public class ActiveEffect extends Effect {

	protected boolean isAppliedBeginning;
	protected int numberTurnEffectActive;
	protected String displayEffect;

	/**
	 * This is the constructor of this class
	 * 
	 * @param displayEffect          This is the way the effect will be display on
	 *                               the screen
	 * @param numberTurnEffectActive This is the number of turn that the effect is
	 *                               active
	 * @param isAppliedBeginning     This a boolean that say if the effect is apply
	 *                               at the beginning of the turn or a the end
	 */
	public ActiveEffect(String displayEffect, int numberTurnEffectActive, boolean isAppliedBeginning) {
		this.isAppliedBeginning = isAppliedBeginning;
		this.numberTurnEffectActive = numberTurnEffectActive;
		this.displayEffect = displayEffect;

	}

}
