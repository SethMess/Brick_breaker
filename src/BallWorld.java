
public class BallWorld extends World {
	Score score;
	public BallWorld() {
		score = new Score();
		score.setScore(0);
		score.setX(280);
		score.setY(280);
		this.getChildren().add(score);
	}
	@Override
	public void act() {
		
	}
	public Score getScore() {
		return score;
	}
}
