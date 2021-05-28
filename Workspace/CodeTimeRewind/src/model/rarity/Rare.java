package model.rarity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
/**
 * This is a subclass of rarity that contains the rare rarity
 * 
 * @author Mathieu Rabot
 *
 */
public class Rare extends Rarity {
	
	@Transient
	private static final String NAME = "Rare";
	@Transient
	private static final double CHANCEOFSUMMONING = 0.6;

	/**
	 * This is the constructor of this class
	 */
	public Rare() {
		super(NAME, CHANCEOFSUMMONING);
	}

}