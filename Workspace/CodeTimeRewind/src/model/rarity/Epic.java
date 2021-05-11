package model.rarity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Epic extends Rarity {
	
	@Transient
	private static final String NAME = "Epic";
	@Transient
	private static final double CHANCEOFSUMMONING = 0.3;

	public Epic() {
		super(NAME, CHANCEOFSUMMONING);
	}

}
