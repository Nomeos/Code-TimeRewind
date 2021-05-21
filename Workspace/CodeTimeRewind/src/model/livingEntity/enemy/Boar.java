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
public class Boar extends Enemy {
	@Transient
	private List<Spell> spells = new ArrayList<Spell>();
	@Transient
	private final static String NAME = "Boar";
	@Transient
	private final static String DESCRIPTION = "Gros cochon violent";
	@Transient
	private final static int LEVEL = 1;
	@Transient
	private final static int HEALTH = 300;
	@Transient
	private final static int DEFENSE = 50;
	@Transient
	private final static int ATTACK = 10;
	@Transient
	private final static int SPEED = 20;
	@Transient
	private final static int WIDTH = 198;
	@Transient
	private final static int HEIGHT = 150;
	

	public Boar(Rarity rarity) {
		super(NAME, LEVEL, HEALTH, DEFENSE, ATTACK, SPEED, DESCRIPTION, rarity, WIDTH, HEIGHT);
	}


}