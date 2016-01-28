package utils;

import java.util.HashMap;
import java.util.Map;

public class ClassStyle {
	private Map<String, String> styles;

	public ClassStyle() {
		styles = new HashMap<String, String>();
		//Singleton
		styles.put("Singleton", "style = solid, color = blue");
		
		
		//Adapter
		String adapterStyle = "style = filled, color = red";
		styles.put("adapter", adapterStyle);
		styles.put("adaptee", adapterStyle);
		styles.put("target", adapterStyle);

		
		//Decorator
		String decoratorStyle = "style = filled, color = green";
		styles.put("decorator", decoratorStyle);
		styles.put("component", decoratorStyle);
	}

	public String getStyleByType(String type) {
		return styles.get(type);
	}
}
