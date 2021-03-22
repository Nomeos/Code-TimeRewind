package model.effect.activeEffect.buff;

import lombok.Getter;
import lombok.Setter;
import model.entity.Entity;

@Getter
@Setter
public class AttackImprove extends BuffEffect {

	private final static float ATTACKAIMPROVEMENT = 0.30f;
	private final static String DISPLAYEFFECT = "+15% Attack";

	private final static boolean ISACTIVATEDBEGINNING = true;

	public AttackImprove(int numberTurnEffectActive, boolean isAppliedBeginning) {
		super(DISPLAYEFFECT, numberTurnEffectActive, isAppliedBeginning, ISACTIVATEDBEGINNING);
	}

	@Override
	public void applyEffect(Entity target) {
		target.setAttack(Math.round(target.getAttack() + (target.getAttack() * ATTACKAIMPROVEMENT)));
	}

}
