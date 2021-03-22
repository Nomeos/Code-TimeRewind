package model.effect.activeEffect.debuff;

import java.util.Random;

import model.effect.activeEffect.ActiveEffect;
import model.entity.Entity;

public class DebuffEffect extends ActiveEffect {

	protected double pourcentChance;
	protected Random rnd;
	protected boolean isAppliedBeginning;
	


	public DebuffEffect() {
		super();
	}

	public DebuffEffect(String DISPLAYEFFECT, double pourcentChance, int numberTurnEffectActive,
			boolean isAppliedBeginning, boolean ISACTIVATEDBEGINNING) {
		super(DISPLAYEFFECT, numberTurnEffectActive, isAppliedBeginning,ISACTIVATEDBEGINNING);
		this.pourcentChance = pourcentChance;
		
	}

	public void applyEffect(Entity target) {

	}

	public boolean isDebuffApplied() {
		this.rnd = new Random();
		double resultRnd = Math.round((this.rnd.nextDouble() * 100) / 100);
		System.out.println(resultRnd + " >= " + pourcentChance);
		if (resultRnd >= pourcentChance) {
			return true;
		}
		return false;
	}


}
