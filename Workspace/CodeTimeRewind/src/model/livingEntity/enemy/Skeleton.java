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
 * This is a subclass of enemy that contains the enemy skeleton
 * 
 * @author Mathieu Rabot
 *
 */
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
	@Transient
	private final static int WIDTH = 124;
	@Transient
	private final static int HEIGHT = 200;

	/**
	 * This is the constructor of this class
	 * 
	 * @param rarity This is the rarity of this enemy
	 */
	public Skeleton(Rarity rarity) {
		super(NAME, LEVEL, HEALTH, DEFENSE, ATTACK, SPEED, DESCRIPTION, rarity, WIDTH, HEIGHT);

	}
	

}