package model.button;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
/**
 * This is the class that manage all buttons in the game
 * 
 * @author Mathieu Rabot
 *
 */
public class Button {
	private int width;
	private int height;
	private float x;
	private float y;
	private Image image;
	private Image imageHit;
	private boolean isPressed = false;

	/**
	 * This is the first constructor for this class that takes all the parameters
	 * that the button needs
	 * 
	 * @param image    this is the principal image of this button
	 * @param imageHit this is the image if the the button is press
	 * @param x        this is the X position of the button
	 * @param y        this is the Y position of the button
	 * @param WIDTH    This is the width of the button
	 * @param HEIGHT   This is the height of the button
	 */
	public Button(Image image, Image imageHit, float x, float y, int WIDTH, int HEIGHT) {
		this.image = image;
		this.imageHit = imageHit;
		this.x = x;
		this.y = y;
		this.height = HEIGHT;
		this.width = WIDTH;
	}

	/**
	 * This is the first constructor for this class that takes all the parameters
	 * that the button needs without it's position
	 * 
	 * @param image    this is the principal image of this button
	 * @param imageHit this is the image if the the button is press
	 * @param WIDTH    This is the width of the button
	 * @param HEIGHT   This is the height of the button
	 */
	public Button(Image image, Image imageHit, int WIDTH, int HEIGHT) {
		this.image = image;
		this.imageHit = imageHit;
		this.height = HEIGHT;
		this.width = WIDTH;

	}

	/**
	 * This method draw this button on the player screen
	 */
	public void draw() {
		if (isPressed) {
			this.imageHit.draw(x, y);
		} else {
			this.image.draw(x, y);
		}

	}

	/**
	 * This method initialize the position of the button and draw it to the player
	 * screen
	 * 
	 * @param x this is the X position of the button
	 * @param y this is the Y position of the button
	 */
	public void draw(int x, int y) {
		this.x = x;
		this.y = y;

		if (isPressed) {
			this.imageHit.draw(x, y);
		} else {
			this.image.draw(x, y);
		}

	}

	/**
	 * This method verify if the button is hover by the mouse
	 * 
	 * @param x this is the X position of mouse
	 * @param y this is the Y position of mouse
	 * @return it returns a boolean if the button is hovered
	 */
	public boolean isHovering(float x, float y) {
		return this.x < x && (this.x + this.width) > x && this.y < y && (this.y + this.height) > y;
	}
}
