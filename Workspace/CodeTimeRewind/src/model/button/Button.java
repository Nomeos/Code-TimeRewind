package model.button;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Button {
	private int width;
	private int height;
	private float x;
	private float y;
	private Image image;
	private Image imageHit;
	private boolean isPressed = false;

	public Button(Image image, Image imageHit, float x, float y,int WIDTH,int HEIGHT) {
		this.image = image;
		this.imageHit = imageHit;
		this.x = x;
		this.y = y;
		this.height = HEIGHT;
		this.width = WIDTH;
	}
	public Button(Image image, Image imageHit, float x, float y) {
		this.image = image;
		this.imageHit = imageHit;
		this.x = x;
		this.y = y;

	}
	public Button(Image image, Image imageHit,int WIDTH,int HEIGHT) {
		this.image = image;
		this.imageHit = imageHit;
		this.height = HEIGHT;
		this.width = WIDTH;

	}

	public void draw() {
		if (isPressed) {
			this.imageHit.draw(x, y);
		} else {
			this.image.draw(x, y);
		}

	}
	public void draw(int x, int y) {
		this.x = x;
		this.y = y;
		
		if (isPressed) {
			this.imageHit.draw(x, y);
		} else {
			this.image.draw(x, y);
		}

	}
	
	public boolean isHovering(float x, float y) {
		return this.x < x && (this.x + this.width) > x && this.y < y
				&& (this.y + this.height) > y;
	}
}
