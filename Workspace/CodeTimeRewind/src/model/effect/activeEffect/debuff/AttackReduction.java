package model.effect.activeEffect.debuff;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttackReduction extends DebuffEffect{

	private final static double ATTACKREDUCTION = 0.15;
	private int numberTurnEffectActive;
	private final static String DISPLAYEFFECT = "-15% Attack";
	
	private final static boolean ISACTIVATEDBEGINNING = true;
	
	public AttackReduction(double pourcentChance,int numberTurnEffectActive, boolean isAppliedBeginning) {
		super(DISPLAYEFFECT,pourcentChance, numberTurnEffectActive, isAppliedBeginning);
	}
	public int applyEffect(int currentAttack) {
		return Math.round(currentAttack -= (currentAttack * ATTACKREDUCTION));
	}
}
