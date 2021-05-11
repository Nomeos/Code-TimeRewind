package model.rarity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.livingEntity.LivingEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Rarities")
public class Rarity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rarity_Id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "chance_Of_Summoning")
	private double chanceOfSummoning;
	
	@OneToMany(mappedBy = "rarity", cascade = CascadeType.ALL)
	private Set<LivingEntity> livingEntity = new HashSet<>();

	public Rarity(String name, double chanceOfSummoning) {
		this.name = name;
		this.chanceOfSummoning = chanceOfSummoning;
		
	}

	
	
	
	
	
}