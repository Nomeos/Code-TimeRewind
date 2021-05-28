package model.rarity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
/**
 * This is a subclass of rarity that contains the legendary rarity
 * 
 * @author Mathieu Rabot
 *
 */
public class Legendary extends Rarity {

	@Transient
	private static final String NAME = "Legendary";
	@Transient
	private static final double CHANCEOFSUMMONING = 0.1;

	/**
	 * This is the constructor of this class
	 */
	public Legendary() {
		super(NAME, CHANCEOFSUMMONING);
	}

}
