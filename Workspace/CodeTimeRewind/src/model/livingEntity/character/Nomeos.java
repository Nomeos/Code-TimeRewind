package model.livingEntity.character;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import lombok.NoArgsConstructor;
import model.rarity.Epic;
import model.rarity.Rarity;
import model.spell.Spell;

@Entity
@NoArgsConstructor
public class Nomeos extends Character {
	@Transient
	private List<Spell> spells = new ArrayList<Spell>();
	@Transient
	private final static String NAME = "Nomeos";
	@Transient
	private final static String DESCRIPTION = "Ce personnage est très détendu ,&n a trouvé son épée dans un champ de fleur&n et pense qu '' il a une grande destinée.";
	@Transient
	private final static int LEVEL = 1;
	@Transient
	private final static int HEALTH = 400;
	@Transient
	private final static int DEFENSE = 30;
	@Transient
	private final static int ATTACK = 100;
	@Transient
	private final static int SPEED = 0;
	@Transient
	private final static int WIDTH = 138;
	@Transient
	private final static int HEIGHT = 200;
	

	public Nomeos(Rarity rarity) {
		super(NAME, LEVEL, HEALTH, DEFENSE, ATTACK, SPEED, DESCRIPTION, rarity, WIDTH, HEIGHT);
		this.spells = new ArrayList<Spell>();
	}


}
