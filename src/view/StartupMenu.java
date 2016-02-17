package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import util.Command;
import util.Config;
import util.Parser;


/**
 * This is a class that just contains the startup menu
 * 
 * @author Gustav
 * @version 2016-02-05
 */

@SuppressWarnings("serial")
public class StartupMenu 
extends GameComponent{		

	/**
	 * Constructor that generates the menu
	 */
	public StartupMenu ()
	{
		System.out.println("Startup menu created");		
		this.buildMenu();
	}
	
	
	/**
	 * Constructor that adds an ActionListener and creates the menu.
	 * @param AL		The ActionListener to which events get sent
	 */
	public StartupMenu (ActionListener AL)
	{
		this();
		this.addActionListener(AL);
	}
	
	
	/**
	 * Where the menu actually gets created. Only called from the constructor.
	 */
	private void buildMenu()
	{
		MenuPanel menuPanel = new MenuPanel(this, Config.get("Startup_menu_title"));
		menuPanel.setBackground(Parser.ColorFromString(Config.get("Startup_bg_color")));
		menuPanel.setBorderColor(Parser.ColorFromString(Config.get("Startup_border_color")));
		
		Color bg_color = Parser.ColorFromString(Config.get("Startup_button_bg_color"));
		Color border_color = Parser.ColorFromString(Config.get("Startup_button_border_color"));
		Color highlight_color = Parser.ColorFromString(Config.get("Startup_button_highlight"));
		menuPanel.addButton(Config.get("Startup_menu_newgame"), Command.START_NEW_GAME, bg_color, border_color, highlight_color);
		menuPanel.addButton(Config.get("Startup_menu_loadgame"), Command.LOAD_GAME, bg_color, border_color, highlight_color);
		menuPanel.addButton(Config.get("Startup_menu_exit"), Command.EXIT, bg_color, border_color, highlight_color);
		
		this.add(menuPanel);
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());
		this.setVisible(true);
	}
	
}
