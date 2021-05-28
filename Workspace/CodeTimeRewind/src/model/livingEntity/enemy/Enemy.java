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
/**
 * This is a subclass of LivingEntity that contains every enemies
 * 
 * @author Mathieu Rabot
 *
 */
public class Enemy extends LivingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enemy_Id")
	private int id;

	@OneToMany(mappedBy = "enemy", cascade = CascadeType.ALL)
	private Set<EnemyPerStage> enemy_Per_Level = new HashSet<>();

	/**
	 * This is the constructor of this class
	 * 
	 * @param name        This is the name of the character
	 * @param level       This is the level of the character
	 * @param health      This is the health of the character
	 * @param defense     This is the defense of the character
	 * @param attack      This is the attack of the character
	 * @param speed       This is the speed of the character
	 * @param description This is the description of the character
	 * @param rarity      This is the rarity of the character
	 * @param width       This is the width of the character sprite
	 * @param height      This is the height of the character sprite
	 */
	public Enemy(String name, int level, int health, int defense, int attack, int speed, String description,
			Rarity rarity, int width, int height) {
		super(name, level, health, defense, attack, speed, description, rarity, width, height);
	}

}
