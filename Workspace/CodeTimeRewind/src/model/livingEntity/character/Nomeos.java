package model.livingEntity.character;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import model.spell.Spell;

@Entity
@NoArgsConstructor
public class Nomeos extends Character{
	private List<Spell> spells = new ArrayList<Spell>();
	private final static String NAME = "Nomeos";
	

	/*public Nomeos() throws SlickException {
		super(name, level, health, defense, attack, speed, x, y, width, height, experience, Description);
		// TODO Auto-generated constructor stub
	}*/
	
}
