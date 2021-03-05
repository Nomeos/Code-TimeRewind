package model.button;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Button {
	private int WIDTH;
	private int HEIGHT;
	private int x;
	private int y;
	private Image image;
	private Image imageHit;
	private boolean isPressed = false;

	public Button(Image image, Image imageHit, int x, int y,int WIDTH,int HEIGHT) {
		this.image = image;
		this.imageHit = imageHit;
		this.x = x;
		this.y = y;
		this.HEIGHT = HEIGHT;
		this.WIDTH = WIDTH;
	}
	public Button(Image image, Image imageHit, int x, int y) {
		this.image = image;
		this.imageHit = imageHit;
		this.x = x;
		this.y = y;

	}

	public void draw() {
		if (isPressed) {
			this.imageHit.draw(x, y);
		} else {
			this.image.draw(x, y);
		}

	}
	
	public boolean isHovering(int x, int y) {
		return this.x < x && (this.x + this.WIDTH) > x && this.y < y
				&& (this.y + this.HEIGHT) > y;
	}
}
