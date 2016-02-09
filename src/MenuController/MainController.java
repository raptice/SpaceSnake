package MenuController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainController implements ActionListener {
	private GUI theGui;
	
	public MainController(){
		this.theGui = new GUI(this);
		
		theGui.showStartupMenu(this);
		theGui.showGame(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Controller ActionEvent");
	}
}
