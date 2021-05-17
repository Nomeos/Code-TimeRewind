package model.livingEntity.enemy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.NoArgsConstructor;
import model.rarity.Rarity;
import model.spell.Spell;

@Entity
@NoArgsConstructor
public class Skeleton extends Enemy {
	@Transient
	private List<Spell> spells = new ArrayList<Spell>();
	@Transient
	private final static String NAME = "Skeleton";
	@Transient
	private final static String DESCRIPTION = "Petit squelette argneux";
	@Transient
	private final static int LEVEL = 1;
	@Transient
	private final static int HEALTH = 100;
	@Transient
	private final static int DEFENSE = 10;
	@Transient
	private final static int ATTACK = 150;
	@Transient
	private final static int SPEED = 0;

	public Skeleton(Rarity rarity) {
		super(NAME, LEVEL, HEALTH, DEFENSE, ATTACK, SPEED, DESCRIPTION, rarity);

	}
	

}