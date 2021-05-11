package model.rarity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Legendary extends Rarity {
	
	
	@Transient
	private static final String NAME = "Legendary";
	@Transient
	private static final double CHANCEOFSUMMONING = 0.1;
	
	public Legendary() {
		super(NAME, CHANCEOFSUMMONING);
	}

}
