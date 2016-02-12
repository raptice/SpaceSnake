import java.awt.event.ActionListener;

import GUI.GUI;
public class SpaceSnake implements ActionListener {
	public static void main(String[] args) {
		GUI view = new GUI();
		//create world: Model model 	= new Model();
		
		MainController mainController = new MainController();
		//mainController.addModel(model);
		mainController.addView(view);
		view.addController(mainController); //addController will be in the GUI class
		
		
		
		/* in the GUI class
		public void addController(ActionListener mainController){
			System.out.println("View      : adding controller");
			button.addActionListener(controller);	//need instance of controller before can add it as a listener 
		}
		*/
	}
}
