import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Breakout");
		BorderPane root = new BorderPane();
		BallWorld world = new BallWorld();
		world.setPrefWidth(500);
		world.setPrefHeight(300);
		root.setCenter(world);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		Ball ball = new Ball();
		ball.setX(250);
		ball.setY(150);
		world.add(ball);
		
		Paddle paddle = new Paddle();
		paddle.setX(250);
		paddle.setY(250);
		world.add(paddle);
		
		for(int i = 0; i < 12; i++) {
			Brick b = new Brick();
			b.setX(40*i + 10);
			b.setY(100);
			world.add(b);
		}
		
		world.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				paddle.setX(event.getX() - paddle.getWidth()/2);
			}});

		world.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				world.keyPress(event.getCode());
			}});
		
		world.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				world.keyRelease(event.getCode());
			}});
		
		world.requestFocus();
		world.start();
	}

}
