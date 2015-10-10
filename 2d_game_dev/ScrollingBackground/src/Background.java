import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Background {

	private BufferedImage bgImage;	
	private int x1;
	private int x2;
	
	private int imageWidth;

	Camera camera;
	
	public Background(String filename, Camera camera) {
		
		try {
			bgImage = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageWidth = bgImage.getWidth();
		
		x1 = 0;
		x2 = imageWidth;
		
		this.camera = camera;
	}
	
	public void draw(Graphics g) {
		
		// instead of having the background actually scroll
		// a translation is applied
		// NOTE: this algorithm/code is mine so 
		// it may not be perfect/very efficient, etc.
		
		// the two images that are going to be drawn
		// have their own starting x value
		g.drawImage(bgImage, x1, 0, null);
		g.drawImage(bgImage, x2, 0, null);
		
		// if the first image is scrolled off the screen
		if(camera.getX() > x1 + imageWidth - Game.WIDTH) {
			// draw a second image where the first ends
			g.drawImage(bgImage, x1 + imageWidth, 0, null);
			// and mark the beginning of the second image
			x2 = x1 + imageWidth;
		}
		// if the second image is scrolled off the screen
		if(camera.getX() > x2 + imageWidth - Game.WIDTH) {
			// draw a third/first 
			// (since the first can no longer be seen on the screen)
			// where the second one ends
			g.drawImage(bgImage, x2 + imageWidth, 0, null);
			// and mark the beginning of the third/first image
			x1 = x2 + imageWidth;
		}
	}
	
	public int getWidth() {
		return imageWidth;
	}
	
}
