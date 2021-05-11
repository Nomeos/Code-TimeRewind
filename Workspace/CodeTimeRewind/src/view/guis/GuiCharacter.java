package view.guis;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import controller.CharacterController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.account.Account;
import model.button.Button;

@Getter
@Setter
@NoArgsConstructor
public class GuiCharacter extends Gui {

	private Account player;
	private int smallButtonHeight;
	private int smallButtonWidth;
	private int lobbyButtonXPosition;
	private int lobbyButtonYPosition;
	private int zoneLibreStatsXPosition;
	private int zoneLibreStatsYPosition;
	private int zoneLibreStatsHeight;
	private int character_experience_point;
	private int character_health;
	private int character_defense;
	private int character_attack;
	private int character_speed;
	private Image character_image;
	private int stateId;
	private float character_max_experience_point;
	private int character_level;
	private float backgroundProgressBarWidth;
	private Image backgroundImage;
	private Image zoneLibreStats;
	private Image backgroundProgressBar;
	private Image backgroundSort1;
	private Image backgroundSort2;
	private Image backgroundSort3;
	private Shape progressBar;
	private Font characterNameFont;
	private TrueTypeFont characterNameTTF;

	private String backgroundSortPath = "/res/zones/ZoneSort.png";
	private String centuryFont = "Century Gothic";
	private String character_description;
	private List<Button> listOfCurrentButton;
	private CharacterController controller;

	public GuiCharacter(int state) {
		super(state);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.controller = new CharacterController(this);
		this.buttonNeeded = new int[] { 8 };

		this.lobbyButtonXPosition = 50;
		this.lobbyButtonYPosition = (gc.getHeight() - 110);
		this.zoneLibreStatsXPosition = 50;
		this.zoneLibreStatsYPosition = 240;
		this.zoneLibreStatsHeight = 350;

		this.backgroundImage = new Image("/res/Half_Sunset.png");

		this.backgroundProgressBarWidth = 276;

		this.zoneLibreStats = new Image("/res/zones/ZoneLibre.png");

		this.characterNameFont = new Font(centuryFont, Font.BOLD, 50);
		this.characterNameTTF = new TrueTypeFont(characterNameFont, true);

		this.backgroundProgressBar = new Image("/res/zones/ProgressionBar.png");

		this.backgroundSort1 = new Image(backgroundSortPath);
		this.backgroundSort2 = new Image(backgroundSortPath);
		this.backgroundSort3 = new Image(backgroundSortPath);

		this.listOfCurrentButton = new ArrayList<Button>();
		for (int i : this.buttonNeeded) {
			this.listOfCurrentButton.add(this.getListOfButtons().get(i));
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);
		g.drawImage(zoneLibreStats, zoneLibreStatsXPosition, zoneLibreStatsYPosition);
		g.drawImage(backgroundSort1, this.lobbyButtonXPosition, this.lobbyButtonYPosition - 260);
		g.drawImage(backgroundSort2, this.lobbyButtonXPosition, this.lobbyButtonYPosition - 175);
		g.drawImage(backgroundSort3, this.lobbyButtonXPosition, this.lobbyButtonYPosition - 90);

		for (Button button : this.listOfCurrentButton) {
			button.draw();
		}

		if (this.player != null) {
			g.setFont(characterNameTTF);

			//g.drawString(this.player.getListOfOwnedCharacter().get(0).getName(), 75, 50);
			g.setFont(new TrueTypeFont(new Font(centuryFont, Font.BOLD, 40), true));
			//g.drawString("Level : " + this.player.getListOfOwnedCharacter().get(0).getLevel() + "/40", 75, 120);
			g.setFont(new TrueTypeFont(new Font(centuryFont, Font.BOLD, 20), true));
			g.drawString("Attack : " + this.character_attack, zoneLibreStatsXPosition + 20,
					zoneLibreStatsYPosition + 20);
			g.drawString("Health : " + this.character_health, zoneLibreStatsXPosition + 20,
					zoneLibreStatsYPosition + 60);
			g.drawString("Defense : " + this.character_defense, zoneLibreStatsXPosition + 20,
					zoneLibreStatsYPosition + 100);
			g.drawString("Speed : " + this.character_speed, zoneLibreStatsXPosition + 20,
					zoneLibreStatsYPosition + 140);
			g.drawString("Description :", zoneLibreStatsXPosition + 10,
					this.zoneLibreStatsYPosition + this.zoneLibreStatsHeight + 10);
			g.setFont(new TrueTypeFont(new Font(centuryFont, Font.ITALIC, 16), true));
			drawString(g, this.character_description, zoneLibreStatsXPosition + 10,
					this.zoneLibreStatsYPosition + this.zoneLibreStatsHeight + 20);

			this.progressBar = new Rectangle(80, 180, calculateProgressBarSize(), 45);
			g.setColor(Color.black);

			g.draw(progressBar);
			g.setColor(new Color(0x3bb062));

			g.fill(progressBar);
			g.setColor(Color.white);

			this.backgroundProgressBar.draw(75, 175);
			g.resetFont();

			//g.drawString(this.player.getListOfOwnedCharacter().get(0).getOldExperience() + " / "
			//		+ this.player.getListOfOwnedCharacter().get(0).getMaxExperience(), 195, 192);

			this.character_image.draw(850, 700, 200, 250);

		}

	}

	public float calculateProgressBarSize() {
		float result = 0;

		result = (float) this.character_experience_point / (float) this.character_max_experience_point
				* this.backgroundProgressBarWidth;
		return result;
	}

	public void drawString(Graphics g, String text, int x, int y) {

		for (String line : text.split("&n")) {
			y += g.getFont().getHeight(text);
			g.drawString(line, x, y);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (this.player == null) {
			this.player = Game.getInstance().getPlayerAccount();
			/*this.character_health = this.player.getListOfOwnedCharacter().get(0).getHealth();
			this.character_attack = this.player.getListOfOwnedCharacter().get(0).getAttack();
			this.character_defense = this.player.getListOfOwnedCharacter().get(0).getDefense();
			this.character_level = this.player.getListOfOwnedCharacter().get(0).getLevel();
			this.character_speed = this.player.getListOfOwnedCharacter().get(0).getSpeed();
			this.character_experience_point = this.player.getListOfOwnedCharacter().get(0).getOldExperience();
			this.character_max_experience_point = this.player.getListOfOwnedCharacter().get(0).getMaxExperience();
			this.character_description = this.player.getListOfOwnedCharacter().get(0).getDescription();
			this.character_image = this.player.getListOfOwnedCharacter().get(0).getImage();*/
		}

	}

	@Override
	public void keyReleased(int key, char c) {
		// Do nothing because the user will not release or press any key.
	}

	@Override
	public void keyPressed(int key, char c) {
		// Do nothing because the user will not release or press any key.
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				b.setPressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				b.setPressed(false);
			}
		}
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				this.controller.activeButton(b);
			}
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// Do nothing because no action while use the mouse mouvement feature.
	}

	@Override
	public int getID() {
		return 4;
	}

}
