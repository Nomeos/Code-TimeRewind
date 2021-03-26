package model.effect.passiveEffect;

public class LifeAbsorption extends PassiveEffect{
	private final static boolean ISACTIVATEDBEGINNING = false;
	public LifeAbsorption(double extraHealing) {
		super(extraHealing,ISACTIVATEDBEGINNING);	
		
	}
	public int start(int damageInflicated) {
		return (int) Math.round(damageInflicated * this.extraHealing);
	}
}
