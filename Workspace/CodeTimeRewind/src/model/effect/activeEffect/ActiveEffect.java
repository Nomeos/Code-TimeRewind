package model.effect.activeEffect;

import java.util.Random;

import model.effect.Effect;
import model.entity.Entity;

public class ActiveEffect extends Effect {

	protected Random rnd;
	protected double pourcent;
	protected int numberTurnEffectActive;

	public ActiveEffect(double defensereduction, double pourcent, int numberTurnEffectActive) {
		this.pourcent = pourcent;
		this.numberTurnEffectActive = numberTurnEffectActive;
	}

	public void Start(Entity c, Entity e) {
		this.rnd = new Random();
		if (this.rnd.nextInt(1) >= pourcent) {

		}
	}
}
