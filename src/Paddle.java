import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor {
	private Double previousX, currentX;
	
	public Paddle() {
		String path = getClass().getClassLoader().getResource("resources/paddle.png").toString();
		Image img = new Image(path);
		setImage(img);
	}
	
	/**
	 * @return Integer -1, 0, or 1 based on most recent X position compared to prior X position, indicating positive negative or no motion
	 */
	public int getMotion() {
		return currentX.compareTo(previousX);
	}
	
	@Override
	public void act() {
		previousX = currentX;
		if (getWorld().isKeyDown(KeyCode.LEFT)) {
			move(-10, 0);
		}
		if (getWorld().isKeyDown(KeyCode.RIGHT)) {
			move(10, 0);
		}
		currentX = getX();
	}

}