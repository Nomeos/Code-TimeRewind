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
	

	public ActiveEffect(String displayEffect, int numberTurnEffectActive, boolean isAppliedBeginning) {
		this.isAppliedBeginning = isAppliedBeginning;
		this.numberTurnEffectActive = numberTurnEffectActive;
		this.displayEffect = displayEffect;
		
	}

}
