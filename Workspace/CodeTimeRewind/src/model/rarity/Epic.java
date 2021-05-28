package model.rarity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
/**
 * This is a subclass of rarity that contains the epic rarity
 * 
 * @author Mathieu Rabot
 *
 */
public class Epic extends Rarity {

	@Transient
	private static final String NAME = "Epic";
	@Transient
	private static final double CHANCEOFSUMMONING = 0.3;

	/**
	 * This is the constructor of this class
	 */
	public Epic() {
		super(NAME, CHANCEOFSUMMONING);
	}

}
