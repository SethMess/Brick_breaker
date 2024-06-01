import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
	private AnimationTimer timer;
	private Set<KeyCode> keysDown;
	public World() {
		keysDown = new HashSet<KeyCode>();
		timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				act();
				//for (Node actor : getChildren()) {
				for (int i = 0; i < getChildren().size(); i++) {
					Node actor = getChildren().get(i);
					if (actor instanceof Actor && actor != null) {
						((Actor) actor).act();
					}
				}
			}
		};
	}
	public abstract void act();
	public void start() {
		timer.start();
	}
	public void stop() {
		timer.stop();
	}
	public void keyPress(KeyCode key) {
		keysDown.add(key);
	}
	public void keyRelease(KeyCode key) {
		keysDown.remove(key);
	}
	public boolean isKeyDown(KeyCode key) {
		return keysDown.contains(key);
	}
	public void add(Actor actor) {
		getChildren().add(actor);
	}
	public void remove(Actor actor) {
		getChildren().remove(actor);
	}
	public <A extends Actor> List<A> getObjects(Class<A> cls) {
		ArrayList<A> list = new ArrayList<A>();
		for (Node node : getChildren()) {
			if (cls.isInstance(node)) {
				list.add(cls.cast(node));
			}
		}
		return list;
	}
}
