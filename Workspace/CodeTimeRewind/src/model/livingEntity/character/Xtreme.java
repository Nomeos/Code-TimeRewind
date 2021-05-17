package model.livingEntity.character;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.NoArgsConstructor;
import model.rarity.Rarity;
import model.spell.Spell;

@Entity
@NoArgsConstructor
public class Xtreme extends Character {
	@Transient
	private List<Spell> spells = new ArrayList<Spell>();
	@Transient
	private final static String NAME = "Xtreme";
	@Transient
	private final static String DESCRIPTION = "Un vrai joueur, toujours armé de &n sa boisson et de ses dagues, il fait &n régner la malchance autour de lui";
	@Transient
	private final static int LEVEL = 1;
	@Transient
	private final static int HEALTH = 350;
	@Transient
	private final static int DEFENSE = 50;
	@Transient
	private final static int ATTACK = 200;
	@Transient
	private final static int SPEED = 40;

	public Xtreme(Rarity rarity) {
		super(NAME, LEVEL, HEALTH, DEFENSE, ATTACK, SPEED, DESCRIPTION, rarity);
	}


}
