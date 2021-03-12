package model.databaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class DatabaseCharacterManager {
	private String connectionStatement;
//	private String sqlQuery, currentCategory = "";
	private Connection connection;
	private Statement statement;
//	private ResultSet resultQueryAllAnimations;
//	private List<Animation> animations = new ArrayList<>();
//	private List<Image> images = new ArrayList<>();
//	private Image[] imageArray;
	private static DatabaseCharacterManager instance;

	public static DatabaseCharacterManager getInstance() {
		return instance == null ? instance = new DatabaseCharacterManager() : instance;
	}

	/*public List<Animation> getAllAnimations(String characterName) {
		try {
			OpenDatabaseConnection();
			this.sqlQuery = "SELECT cs.Sprite_Path, tos.name FROM Character_Sprites cs"
					+ " INNER JOIN Type_Of_Sprites tos ON cs.Type_Of_Sprite_ID = tos.Type_Of_Sprite_ID"
					+ " INNER JOIN Characters c ON c.Character_ID = cs.Character_ID" + " WHERE c.Name = '"
					+ characterName + "'";
			resultQueryAllAnimations = statement.executeQuery(sqlQuery);
			while (resultQueryAllAnimations.next()) {
				if (currentCategory.equals(resultQueryAllAnimations.getString("name"))) {
					this.images.add(new Image(resultQueryAllAnimations.getString("Sprite_Path")));

					if (resultQueryAllAnimations.isLast()) {
						this.imageArray = images.toArray(new Image[0]);
						this.animations.add(new Animation(imageArray, 100));
					}
				} else {
					if (images.size() == 0) {
						this.images.add(new Image(resultQueryAllAnimations.getString("Sprite_Path")));
						this.currentCategory = resultQueryAllAnimations.getString("name");
					} else {
						this.imageArray = images.toArray(new Image[0]);
						
						this.animations.add(new Animation(imageArray, 100));
						this.imageArray = null;
						this.images.clear();
						this.images.add(new Image(resultQueryAllAnimations.getString("Sprite_Path")));
						this.currentCategory = resultQueryAllAnimations.getString("name");

					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return animations;
	}*/
	
	public Image getCharacterPicture(String characterName) {
		Image image = null;
		try {
			
			image = new Image("/res/entity/"+characterName+"/"+characterName+".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	public Statement OpenDatabaseConnection() {

		try {

			this.connectionStatement = "jdbc:derby:C:/Projet TPI/Code-TimeRewind/Workspace/CodeTimeRewind/codetimerewinddb";
			this.connection = DriverManager.getConnection(this.connectionStatement);
			this.statement = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			return this.statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
