package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import framework.IPhase;
import framework.StaticPhaseFactory;
import input.InputCommand;

public class UMLConfiguration {
	private Properties props;
	
	public UMLConfiguration() {
		readConfig();
	}
	
	private void readConfig() {
		props = new Properties();
		
		try {
			FileInputStream in = new FileInputStream("resources/config.properties");
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// combine input-folder classes and input-classes into one String[]
		String inputDir = props.getProperty("input-folder");
		String[] packages = props.getProperty("input-packages").split(",");
		
		List<String> classes = PackageInspector.getClasses(inputDir, packages);
		String[] inputClasses = new String[] {};
		
		if (!props.getProperty("input-classes").equals("-")) {
			inputClasses = props.getProperty("input-classes").replace(".", "/").split(",");
		}		
		
		for (String clazz : inputClasses) {
			classes.add(clazz);
		}
		
		// HACK: we are setting the new class list to override config value
		// this will be retrieved from the UML AddStrategy and these strings might need to be trimmed
		props.setProperty("input-classes", classes.toString());
	}
	
	public InputCommand getInputCommand() {
		List<IPhase> constructedPhases = getPhases();
		return new InputCommand(constructedPhases);
	}
	
	private List<IPhase> getPhases() {
		return StaticPhaseFactory.getPhases(props);
	}
}