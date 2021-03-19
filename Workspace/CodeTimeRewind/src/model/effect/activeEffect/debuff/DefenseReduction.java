package model.effect.activeEffect.debuff;

public class DefenseReduction extends DebuffEffect{
	private final static double DEFENSEREDUCTION = 0.15;
	
	public DefenseReduction(double pourcentChance,int cooldown) {
		super(DEFENSEREDUCTION,pourcentChance, cooldown);
	}
	
}
