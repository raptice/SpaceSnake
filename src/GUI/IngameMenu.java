package GUI;
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
 * This is a class that just contains the ingame menu
 * Not yet implemented properly!
 * 
 * @author Gustav
 * @version 2016-02-05
 */

public class IngameMenu extends GameComponent{		

	
	/**
	 * Constructor that generates the menu
	 */
	public IngameMenu ()
	{
		System.out.println("Ingame menu created");		
		this.buildMenu();
	}
	
	
	/**
	 * Constructor that creates the menu and adds an ActionListener to which events gets sent.
	 * @param AL	The ActionListener to which events gets sent
	 */
	public IngameMenu (ActionListener AL)
	{
		this();
		this.addActionListener(AL);
	}
	
	
	/**
	 * Where the actual menu is generated. Only called from the constructor.
	 */
	private void buildMenu()
	{
		//Create the menu title
		JPanel titlePanel = new JPanel(new GridLayout(0,1));
		titlePanel.setOpaque(false);

		JLabel label = new JLabel("Menu", SwingConstants.CENTER);
		label.setFont(new Font(Font.SERIF, Font.BOLD, 100));
		titlePanel.add(label);
		
		
		//Create the buttons
		JPanel buttonPanel = new JPanel(new GridLayout(0,1));
		buttonPanel.setOpaque(false);
		
		JButton button1 = new JButton("Button1");
		button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEvent(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"Command1",Calendar.getInstance().getTime().getTime(),0));
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
		
		
		//thePanel.setSize(new Dimension(200,150));
		//this.setSize(new Dimension(200,150));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//this.setLayout(new GridBagLayout());
		//this.setPreferredSize(new Dimension(200,150));
		
		this.add(titlePanel);//,GridBagConstraints.CENTER);
		this.add(buttonPanel);//,GridBagConstraints.CENTER);
		
		this.setBounds(0, 0, 350, 250);
		//this.pack();
		//thePanel.setVisible(true);
		this.setVisible(true);
		//thePanel.setLocation(this.getWidth()/2-thePanel.getWidth()/2, this.getHeight()/3-thePanel.getHeight()/3);
	}

}
