package model.chapter;

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
import model.stage.Stage;
/**
 * This class contains the chapter where all the levels is
 * 
 * @author Mathieu Rabot
 *
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Chapters")
public class Chapter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chapter_Id")
	private int id;
	
	@OneToMany(mappedBy = "chapter" , cascade = CascadeType.ALL)
	private Set<Stage> stages = new HashSet<>();

	private String name;

	public Chapter(String name) {
		this.name = name;
	}
	
	
	
}
