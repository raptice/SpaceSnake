
import GUI.GUI;
import controller.MainController;
public class SpaceSnake {
	public static void main(String[] args) {
		GUI view = new GUI();
		@SuppressWarnings("unused")
		MainController mainController = new MainController(view);
	}
}
