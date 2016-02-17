package view;

import java.util.Observer;

import model.WorldCollection;

public interface GameObserver extends Observer
{

	public void addWorld(WorldCollection worldCollection);
	
}
