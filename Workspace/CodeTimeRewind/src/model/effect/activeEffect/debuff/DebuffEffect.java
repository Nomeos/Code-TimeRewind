package model.effect.activeEffect.debuff;

import model.effect.activeEffect.ActiveEffect;

public class DebuffEffect extends ActiveEffect{
	
	public DebuffEffect(double defensereduction, double pourcent, int cooldown) {
		super(defensereduction, pourcent, cooldown);
	}
}
