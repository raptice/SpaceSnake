import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
 * Custom layout handler to be used within JComponents
 * which makes all objects fill the entire available area. 
 * 
 * @author Gustav
 * @version 2016-02-05
 */
public class FillAllLayout 
implements LayoutManager 
{
	
	/**
	 * The only constructor as this has no possible settings.
	 */
	public FillAllLayout(){}

	/**
	 * Overridden method that does nothing.
	 * 
	 * @param name	Name of the component tht could havebeen added
	 * @param comp	The component that could have been added
	 */
	@Override
	public void addLayoutComponent(String name, Component comp) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Overridden method that sets the bounds of all components of the layout holding class.
	 * It fetches the components of the parent and sets all of their bounds to the parents bounds.
	 * 
	 * @param parent	The class with the layout
	 */
	@Override
	public void layoutContainer(Container parent) {
		// TODO Auto-generated method stub
		for (Component c : parent.getComponents())
		{
			c.setBounds(parent.getBounds());
		}
		
	}

	/**
	 * Overridden method that uses the minimum size of the parent as minimum size.
	 * 
	 * @param parent	The parent of the class with this layout
	 */
	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return parent.getMinimumSize();
	}

	/**
	 * Overridden method that uses the preferred size of the parent as the preferred size.
	 * 
	 * @param parent	The parent of the class with this layout
	 */
	@Override
	public Dimension preferredLayoutSize(Container parent) {
		return parent.getPreferredSize();
	}

	/**
	 * Overridden method that does nothing.
	 * 
	 * @param comp	The component that could have been removed from the layout
	 */
	@Override
	public void removeLayoutComponent(Component comp) {	}

}
