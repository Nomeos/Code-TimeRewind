package model.effect.activeEffect.debuff;

import lombok.Getter;
import lombok.Setter;
import model.livingEntity.LivingEntity;

@Getter
@Setter
public class DefenseReduction extends DebuffEffect{
	private final static float DEFENSEREDUCTION = 0.30f;
	private final static String DISPLAYEFFECT = "-15% Defense";
	private final static boolean ISACTIVATEDBEGINNING = true;
	
	
	public DefenseReduction(double pourcentChance,int numberTurnEffectActive,boolean isAppliedBeginning) {
		super(DISPLAYEFFECT,pourcentChance,numberTurnEffectActive,isAppliedBeginning,ISACTIVATEDBEGINNING);

	}
	
	@Override
	public void applyEffect(LivingEntity target) {
		target.setDefense(Math.round(target.getDefense() - (target.getDefense() * DEFENSEREDUCTION)));
	}
}
