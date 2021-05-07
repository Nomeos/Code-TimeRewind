package model.enemy_Per_Stage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.livingEntity.enemy.Enemy;
import model.stage.Stage;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Enemy_Per_Levels")
public class Enemy_Per_Stage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enemy_Per_Level_Id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "stage_Id")
	private Stage stage;
	
	@ManyToOne
	@JoinColumn(name = "enemy_Id")
	private Enemy enemy;
	
	private int level;
	
	public Enemy_Per_Stage(Stage levelTable, Enemy enemyTable, int level) {
		this.stage = levelTable;
		this.enemy = enemyTable;
		this.level = level;
	}
}
