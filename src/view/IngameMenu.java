package view;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * This is a class that just contains the startup menu
 * 
 * @author Gustav
 * @version 2016-02-05
 */

@SuppressWarnings("serial")
public class IngameMenu 
extends GameComponent{		

	/**
	 * Constructor that generates the menu
	 */
	public IngameMenu ()
	{
		System.out.println("Ingame menu created");		
		this.buildMenu();
	}
	
	
	/**
	 * Constructor that adds an ActionListener and creates the menu.
	 * @param AL		The ActionListener to which events get sent
	 */
	public IngameMenu (ActionListener AL)
	{
		this();
		this.addActionListener(AL);
	}
	
	
	/**
	 * Where the menu actually gets created. Only called from the constructor.
	 */
	private void buildMenu()
	{
		
		JPanel titlePanel = buildTitle();
		JPanel buttonPanel = buildButtons();
		
		JPanel menuPanel = new JPanel();
		menuPanel.setOpaque(false);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
		menuPanel.add(titlePanel);
		menuPanel.add(buttonPanel);
		
		this.setOpaque(false);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(menuPanel);
		this.setVisible(true);
	}
	

	/**
	 * A private method that builds a JPanel with the title.
	 * @return		A JPanel with the title
	 */
	private JPanel buildTitle() {
		JPanel titlePanel = new JPanel(new GridLayout(0,1));
		titlePanel.setOpaque(false);

		JLabel label = new JLabel("Paused", SwingConstants.CENTER);
		label.setFont(new Font(Font.SERIF, Font.BOLD, 80));
		titlePanel.add(label);
		return titlePanel;
	}
	
	
	/**
	 * A private method that builds a JPanel with the buttons.
	 * @return		A JPanel with all the buttons
	 */
	private JPanel buildButtons() {
		JPanel buttonPanel = new JPanel(new GridLayout(0,1));
		buttonPanel.setOpaque(false);
		
		JButton button1 = new JButton("Return to game");
		button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //fireEvent(e);
            	fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Return to game",Calendar.getInstance().getTime().getTime(),0));
            }
        });
		buttonPanel.add(button1);
		
		JButton button2 = new JButton("Save game");
		button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Save game",Calendar.getInstance().getTime().getTime(),0));
            }
        });
		buttonPanel.add(button2);
		
		JButton button3 = new JButton("Exit game");
		button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Exit game",Calendar.getInstance().getTime().getTime(),0));
            }
        });
		buttonPanel.add(button3);
		
		buttonPanel.setPreferredSize(new Dimension(200, 100));
		buttonPanel.setMaximumSize(new Dimension(300,300));
		return buttonPanel;
	}
	
}
