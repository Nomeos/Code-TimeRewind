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
public class Guiwi extends Character {
	@Transient
	private List<Spell> spells = new ArrayList<Spell>();
	@Transient
	private final static String NAME = "Guiwi";
	@Transient
	private final static String DESCRIPTION = "Guiwi pense qu''a la destruction, &n il cherche quelqu''un pouvant le battre mais il sait &n que personne n''y arrivera.";
	@Transient
	private final static int LEVEL = 1;
	@Transient
	private final static int HEALTH = 200;
	@Transient
	private final static int DEFENSE = 10;
	@Transient
	private final static int ATTACK = 500;
	@Transient
	private final static int SPEED = 90;
	@Transient
	private final static int WIDTH = 195;
	@Transient
	private final static int HEIGHT = 222;

	public Guiwi(Rarity rarity) {
		super(NAME, LEVEL, HEALTH, DEFENSE, ATTACK, SPEED, DESCRIPTION, rarity, WIDTH, HEIGHT);
	}

}
