package model.effect.activeEffect.buff;

import lombok.Getter;
import lombok.Setter;
import model.livingEntity.LivingEntity;

@Getter
@Setter
public class DefenseImprove extends BuffEffect {

	private final static float DEFENSEIMPROVEMENT = 0.30f;
	private int numberTurnEffectActive;
	private final static String DISPLAYEFFECT = "+15% Defense";
	
	private final static boolean ISACTIVATEDBEGINNING = true;

	public DefenseImprove(int numberTurnEffectActive, boolean isAppliedBeginning) {
		super(DISPLAYEFFECT,numberTurnEffectActive, isAppliedBeginning,ISACTIVATEDBEGINNING);
	}

	@Override
	public void applyEffect(LivingEntity target) {
		target.setDefense(Math.round(target.getDefense() + (target.getDefense() * DEFENSEIMPROVEMENT))); 
	}

}
