package util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;


/**
 * Class that after it is initiated can be called with a static method to get properties.
 * @author gustav
 *
 */
public class Config {
	
	private static Properties configFile;
	private static boolean loaded;
	
	
	/**
	 * Constructor that takes a filename and loads its content into a static variable.
	 * @param filename
	 */
	public Config (String filename) {
		configFile = new Properties();
		try {
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			configFile.load(in);
			loaded = true;
		} catch (IOException e) {
			e.printStackTrace();
			loaded = false;
		}
	}
	
	
	/**
	 * Gets a certain property from the config file (after it is loaded).
	 * @param key	The key in the config file (no spaces)
	 * @return		The value associated with the key. 
	 */
	public static String get(String key) {
		if (!loaded) return "";
		String value = (String) configFile.get(key);
		if (value == null) return "";
		return value;
	}
	
	/**
	 * Randomizes a world size.
	 * @return		The value associated with the size of the world.
	 */
	public int randomWorldSize() {
		Random random = new Random();
		int worldSize = random.nextInt(1000) + 500;
		return worldSize;
	}
	
	
}
