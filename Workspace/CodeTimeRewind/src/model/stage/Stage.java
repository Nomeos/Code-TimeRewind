package model.stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.chapter.Chapter;
import model.enemy_Per_Stage.Enemy_Per_Stage;
import model.livingEntity.enemy.Enemy;

/**
 * This class contains the stage that the user can clear for continue the game
 * 
 * @author Mathieu Rabot
 *
 */
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Levels")
public class Stage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stage_Id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "xPosition")
	private int xPosition;

	@Column(name = "yPosition")
	private int yPosition;

	@OneToMany(mappedBy = "stage")
	private Set<Enemy_Per_Stage> enemy_Per_Stage = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "chapter_Id")
	private Chapter chapter;

	@Transient
	private List<Enemy> listOfEnemy = new ArrayList<Enemy>();

	/**
	 * This is the constructor of this class
	 * 
	 * @param name         It contains the name of the level
	 * @param isLevelClear It says if the user has already clear this level
	 * @param xPosition    It contains the X position of this level
	 * @param yPosition    It contains the Y position of this level
	 */
	public Stage(String name, boolean isLevelClear, int xPosition, int yPosition) {
		this.name = name;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public Stage(String name, int xPosition, int yPosition, Chapter chapter) {
		this.name = name;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.chapter = chapter;
	}

}