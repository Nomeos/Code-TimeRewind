package model.rarity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rare extends Rarity {
	
	@Transient
	private static final String NAME = "Rare";
	@Transient
	private static final double CHANCEOFSUMMONING = 0.6;

	public Rare() {
		super(NAME, CHANCEOFSUMMONING);
	}

}