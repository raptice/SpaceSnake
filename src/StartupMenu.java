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

public class StartupMenu extends GameComponent{		

	/*
	 * Constructors that generates the menu
	 */
	public StartupMenu ()
	{
		System.out.println("Startup menu created");		
		this.buildMenu();
	}
	public StartupMenu (ActionListener AL)
	{
		this();
		this.addActionListener(AL);
	}
	
	private void buildMenu()
	{
		
		JPanel titlePanel = new JPanel(new GridLayout(0,1));
		titlePanel.setOpaque(false);

		JLabel label = new JLabel("Menu", SwingConstants.CENTER);
		label.setFont(new Font(Font.SERIF, Font.BOLD, 80));
		titlePanel.add(label);
		
		
		JPanel buttonPanel = new JPanel(new GridLayout(0,1));
		buttonPanel.setOpaque(false);
		
		JButton button1 = new JButton("Button1");
		button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEvent(e);
            	//fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Command1",Calendar.getInstance().getTime().getTime(),0));
            }
        });
		buttonPanel.add(button1);
		
		JButton button2 = new JButton("Button2");
		button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Command2",Calendar.getInstance().getTime().getTime(),0));
            }
        });
		buttonPanel.add(button2);
		
		JButton button3 = new JButton("Button3");
		button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Command3",Calendar.getInstance().getTime().getTime(),0));
            }
        });
		buttonPanel.add(button3);
		buttonPanel.setSize(100, 100);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setOpaque(false);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
		//menuPanel.setBounds(0, 0, 150, 250);
		//menuPanel.setSize(300, 200);
		//menuPanel.setMinimumSize(new Dimension(200,300));
		//menuPanel.setMaximumSize(new Dimension(200,300));
		menuPanel.setPreferredSize(new Dimension(300,250));
		
		menuPanel.add(titlePanel);
		menuPanel.add(buttonPanel);
		
		this.setOpaque(false);
		//this.setBackground(new Color(255,255,0));
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		//this.setLayout(new GridBagLayout());
		//this.setPreferredSize(new Dimension(200,150));
		//this.setSize(200, 200);
		this.add(menuPanel);
		
		//this.pack();
		//thePanel.setVisible(true);
		this.setVisible(true);
		//thePanel.setLocation(this.getWidth()/2-thePanel.getWidth()/2, this.getHeight()/3-thePanel.getHeight()/3);
	}
	
}
