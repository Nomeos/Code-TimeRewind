package model.effect.activeEffect.buff;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefenseImprove extends BuffEffect {

	private final static double ATTACKAIMPROVEMENT = 0.15;
	private int numberTurnEffectActive;
	private final static String DISPLAYEFFECT = "+15% Defense";
	
	private final static boolean ISACTIVATEDBEGINNING = true;

	public DefenseImprove(int numberTurnEffectActive, boolean isAppliedBeginning) {
		super(DISPLAYEFFECT,numberTurnEffectActive, isAppliedBeginning);
	}

	public int applyEffect(int currentAttack) {
		return Math.round(currentAttack += (currentAttack * ATTACKAIMPROVEMENT));
	}

}
