package model.effect.activeEffect.debuff;

import lombok.Getter;
import lombok.Setter;
import model.entity.Entity;

@Getter
@Setter
public class AttackReduction extends DebuffEffect{

	private final static float ATTACKREDUCTION = 0.30f;
	private int numberTurnEffectActive;
	private final static String DISPLAYEFFECT = "-15% Attack";
	
	private final static boolean ISACTIVATEDBEGINNING = true;
	
	public AttackReduction(double pourcentChance,int numberTurnEffectActive, boolean isAppliedBeginning) {
		super(DISPLAYEFFECT,pourcentChance, numberTurnEffectActive, isAppliedBeginning,ISACTIVATEDBEGINNING);
	}
	@Override
	public void applyEffect(Entity target) {
		target.setAttack(Math.round(target.getAttack() - (target.getAttack() * ATTACKREDUCTION))); 
	}
}
