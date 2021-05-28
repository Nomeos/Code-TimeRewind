package model.spell;

import java.util.List;

import model.effect.activeEffect.buff.BuffEffect;
import model.effect.activeEffect.debuff.DebuffEffect;
import model.effect.passiveEffect.PassiveEffect;

/**
 * This is a subclass of spell that target only one enemy
 * 
 * @author Mathieu Rabot
 *
 */
public class SingleTargetSpell extends Spell {

	/**
	 * This is the constructor of this class
	 * 
	 * @param debuffs  This is the list of debuff that the spell has
	 * @param passives This is the list of passives that the spell has
	 * @param buffs    This is the list of buffs that the spell has
	 * @param cooldown This is the cooldown of this spell
	 */
	public SingleTargetSpell(List<DebuffEffect> debuffs, List<PassiveEffect> passives, List<BuffEffect> buffs,
			int cooldown) {
		super(debuffs, passives, buffs, cooldown);

	}

}
