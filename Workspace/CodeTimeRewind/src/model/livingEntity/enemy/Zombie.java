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
/**
 * This is a subclass of enemy that contains the enemy zombie
 * 
 * @author Mathieu Rabot
 *
 */
public class Zombie extends Enemy {
	@Transient
	private List<Spell> spells = new ArrayList<Spell>();
	@Transient
	private final static String NAME = "Zombie";

	@Transient
	private final static String DESCRIPTION = "Petit zombie espiegle";
	@Transient
	private final static int LEVEL = 1;
	@Transient
	private final static int HEALTH = 150;
	@Transient
	private final static int DEFENSE = 20;
	@Transient
	private final static int ATTACK = 100;
	@Transient
	private final static int SPEED = 0;
	@Transient
	private final static int WIDTH = 138;
	@Transient
	private final static int HEIGHT = 200;

	/**
	 * This is the constructor of this class
	 * 
	 * @param rarity This is the rarity of this enemy
	 */
	public Zombie(Rarity rarity) {
		super(NAME, LEVEL, HEALTH, DEFENSE, ATTACK, SPEED, DESCRIPTION, rarity, WIDTH, HEIGHT);

	}

}