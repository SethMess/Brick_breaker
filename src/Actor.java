import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {
	public abstract void act();
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);
	}
	public World getWorld() {
		return (World)getParent();
	}
	public double getWidth() {
		return getBoundsInParent().getWidth();
	}
	public double getHeight() {
		return getBoundsInParent().getHeight();
	}
	public <A extends Actor> List<A> getIntersectingObjects(Class<A> cls) {
		ArrayList<A> list = new ArrayList<A>();
		for (A actor : getWorld().getObjects(cls)) {
			if (actor != this && intersects(actor.getBoundsInParent())) {
				list.add(actor);
			}
		}
		return list;
	}
	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
		for (A actor : getWorld().getObjects(cls)) {
			if (actor != this && intersects(actor.getBoundsInParent())) {
				return actor;
			}
		}
		return null;
	}
}
