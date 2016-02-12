
import GUI.GUI;
import controller.MainController;
public class SpaceSnake {
	public static void main(String[] args) {
		GUI view = new GUI();
		//create world: Model model 	= new Model();
		
		@SuppressWarnings("unused")
		MainController mainController = new MainController(view);
		//mainController.addModel(model);
		
		
		
		/* in the GUI class
		public void addController(ActionListener mainController){
			System.out.println("View      : adding controller");
			button.addActionListener(controller);	//need instance of controller before can add it as a listener 
		}
		*/
	}
}
