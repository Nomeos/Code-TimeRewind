package model.livingEntity.enemy;

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

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enemyPerStage.EnemyPerStage;
import model.image.LifeBars;
import model.livingEntity.LivingEntity;
import model.rarity.Rarity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Enemy extends LivingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enemy_Id")
	private int id;

	@OneToMany(mappedBy = "enemy", cascade = CascadeType.ALL)
	private Set<EnemyPerStage> enemy_Per_Level = new HashSet<>();

	public Enemy(String name, int level, int health, int defense, int attack, int speed, String description,
			Rarity rarity, int width, int height) {
		super(name, level, health, defense, attack, speed, description, rarity, width, height);
	}

}
