
import controller.MainController;
import view.View;
public class SpaceSnake {
	public static void main(String[] args) {
		View view = new View();
		@SuppressWarnings("unused")
		MainController mainController = new MainController(view);
	}
}
