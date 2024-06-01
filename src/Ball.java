import javafx.scene.image.Image;

public class Ball extends Actor {
	private double dx, dy;
	private boolean hitPaddle;
	
	public Ball() {
		String path = getClass().getClassLoader().getResource("resources/ball.png").toString();
		Image img = new Image(path);
		setImage(img);
		dx = 2;
		dy = 2;
	}
	
	@Override
	public void act() {
		move(dx, dy);
		if (getX() < 0 || (getX() + getWidth()) > getWorld().getWidth()) {
			dx *= -1;
		}
		if (getY() < 0 || (getY() + getHeight()) > getWorld().getHeight()) {
			dy *= -1;
			if((getY() + getHeight()) > getWorld().getHeight()) {
				BallWorld b = (BallWorld) getWorld();
				b.getScore().setScore(b.getScore().getScore() - 1000);
			}
		} else {
			Paddle paddle = getOneIntersectingObject(Paddle.class);
			if (paddle != null && hitPaddle == false) {
				hitPaddle = true;
				
				double paddleW = paddle.getWidth();
				double ballCenterX = getX() + getWidth()/2;
				dy *= -1;
				
				if (ballCenterX < paddle.getX()) {
					if (dx > 0) {
						dx *= -1;
					}
					dy /= 2;
				} else if (ballCenterX > paddle.getX() + paddleW) {
					if (dx < 0) {
						dx *= -1;
					}
					dy /= 2;
				} else if (paddle.getMotion() == -1) {
					// Bounce on left third during left motion or right third during right motion
					// forces the ball to move horizontally the same direction as the paddle
					if (ballCenterX < paddle.getX() + paddleW/3 && dx > 0) {
						dx *= -1;
					}
				} else if (paddle.getMotion() == 1) {
					if (ballCenterX > paddle.getX() + paddleW*2/3 && dx < 0) {
						dx *= -1;
					}
				}
			} else if (paddle == null) {
				hitPaddle = false;
			}
				
		}
		
		//brick bounce code
		
		if(getOneIntersectingObject(Brick.class) != null) {
			Brick brick = getOneIntersectingObject(Brick.class);
				if(getX() + getWidth()/2 > brick.getX() && getX() + getWidth()/2 < brick.getX() + brick.getWidth()) {
					dy *= -1;
				}
				else if(getY() + getHeight()/2 < brick.getY() && getY() + getHeight()/2 < brick.getY() + brick.getHeight()) {
					dx *= -1;
				}
				else {
					dy *= -1;
					dx *= -1;
				}
				getWorld().remove(brick);
				//
				BallWorld b = (BallWorld) getWorld();
				b.getScore().setScore(b.getScore().getScore() + 100);
		}
			
	}

}
