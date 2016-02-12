package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI;


public class MainController implements ActionListener {
	private GUI theGui;
	//private Model model;
	
	public MainController(/*GUI theGui*/){
		//this.theGui = theGui; //= new GUI(this);

		theGui.showStartupMenu(this);
		theGui.showGame(this);
	}
	
	public void addView(GUI gui){
		System.out.println("Controller: adding view");
		this.theGui = gui;
		GUI.init(gui);
	}
	
	/*
	public void addModel(Model m){
		System.out.println("Controller: adding model");
		this.model = m;
	}*/
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Start new game" /*"Button1"*/) {
			System.out.println("Controller: Button1");
		}
		else if (e.getActionCommand() == "Load game" /*"Command2"*/) {
			System.out.println("Controller: Button2");
		}
		else if (e.getActionCommand() == "Exit" /*"Command3"*/) {
			System.out.println("Controller: Button3");
		}
		else {
			System.out.println("Controller: Unknown button: " + e.paramString()); //debugging
		}
	}
}
