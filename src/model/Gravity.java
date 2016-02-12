package model;
import util.*;
import java.util.*;

public interface Gravity {
	public Vector2D calcuateGravity(Vector2D arg);
	public Vector2D getGravity();
	public void gravityPull(List<WorldObject> argList);
}
