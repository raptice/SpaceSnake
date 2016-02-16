

import controller.MainController;
import util.Config;
import view.View;
public class SpaceSnake {
	public static void main(String[] args) {
		
		//Load configuration
		Config c = new Config("config.txt");
		
		// Start the GUI:
		View view = new View();
		
		//Attach a controller to the GUI
		@SuppressWarnings("unused")
		MainController mainController = new MainController(view);
		
	}
}
