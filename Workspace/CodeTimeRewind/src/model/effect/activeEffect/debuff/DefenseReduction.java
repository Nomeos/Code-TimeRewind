package model.effect.activeEffect.debuff;

import lombok.Getter;
import lombok.Setter;
import model.entity.Entity;

@Getter
@Setter
public class DefenseReduction extends DebuffEffect{
	private final static float DEFENSEREDUCTION = 0.15f;
	private final static String DISPLAYEFFECT = "-15% Defense";
	private final static boolean ISACTIVATEDBEGINNING = true;
	
	
	public DefenseReduction(double pourcentChance,int numberTurnEffectActive,boolean isAppliedBeginning) {
		super(DISPLAYEFFECT,pourcentChance,numberTurnEffectActive,isAppliedBeginning);

	}
	
	@Override
	public void applyEffect(Entity e) {
		e.setDefense(Math.round(e.getDefense() - (e.getDefense() * DEFENSEREDUCTION)));
	}
}
