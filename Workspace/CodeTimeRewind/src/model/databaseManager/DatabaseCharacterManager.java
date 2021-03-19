package model.databaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.effect.activeEffect.debuff.DebuffEffect;
import model.effect.activeEffect.debuff.DefenseReduction;
import model.effect.passiveEffect.PassiveEffect;
import model.spell.MultipleTargetSpell;
import model.spell.SingleTargetSpell;
import model.spell.Spell;

public class DatabaseCharacterManager {
	private String connectionStatement;
	private Connection connection;
	private Statement statement;

	private static DatabaseCharacterManager instance;

	public static DatabaseCharacterManager getInstance() {
		return instance == null ? instance = new DatabaseCharacterManager() : instance;
	}

	public Image getCharacterPicture(String characterName) {
		Image image = null;
		try {

			image = new Image("/res/entity/" + characterName + "/" + characterName + ".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

	public List<Spell> takeAllCharacterSpells(String entityName) {
		List<Spell> ls = new ArrayList<Spell>();
		switch (entityName) {
		case "Nom-eos":

			ls.add(new SingleTargetSpell(null, new ArrayList<PassiveEffect>(Arrays.asList(new))));
			ls.add(new SingleTargetSpell(new ArrayList<DebuffEffect>(Arrays.asList(new DefenseReduction(0.5, 2)))));
			ls.add(new MultipleTargetSpell());
			break;
		default:

		}
		return ls;
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
