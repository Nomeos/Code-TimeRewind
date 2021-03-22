package model.effect.activeEffect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.effect.Effect;

@NoArgsConstructor
@Getter
@Setter
public class ActiveEffect extends Effect {

	protected boolean isAppliedBeginning;
	protected int numberTurnEffectActive;
	protected String displayEffect;
	protected boolean isActivatedBeginning;

	public ActiveEffect(String displayEffect, int numberTurnEffectActive, boolean isAppliedBeginning,
			boolean isActivatedBeginning) {
		this.isAppliedBeginning = isAppliedBeginning;
		this.numberTurnEffectActive = numberTurnEffectActive;
		this.displayEffect = displayEffect;
		this.isActivatedBeginning = isActivatedBeginning;
	}

}
