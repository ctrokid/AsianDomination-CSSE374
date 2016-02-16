package gui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GUIReadConfigFile {
	private Map<String, String> properties = new HashMap<>();
	private Properties prop;
	public void writeToFile(Map<String, String> propertiesDataMap) {
		OutputStream output = null;

		try {
			output = new FileOutputStream("resources/config.properties");
			// set the properties value
			for(String k: properties.keySet()){
				prop.setProperty(k, propertiesDataMap.get(k));
			}
			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public Map<String, String> getPropertiesDataMap(){
		return this.properties;
	}
	public void readFromFile() {

		prop = new Properties();
		InputStream input = null;
		try {

			String filename = "config.properties";
			input = getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			prop.load(input);

			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				this.properties.put(key, value);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
