
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class Score extends Text {
	private int scoreVal;
	public Score() {
		scoreVal = 0;
		this.setFont(new Font(30));
		this.updateDisplay();
	}
	void updateDisplay() {
		this.setText("" + scoreVal);
	}
	public int getScore() {
		return scoreVal;
	}
	public void setScore(int num) {
		scoreVal = num;
		updateDisplay();
	}
}
