package utils;

import java.util.HashMap;
import java.util.Map;

public class ClassStyle {
	private Map<String, String> styles;
	private String defaultStyle;

	public ClassStyle() {
		defaultStyle = "style = solid, bgcolor = black";
		styles = new HashMap<String, String>();
		// Singleton
		styles.put("Singleton", "style = solid, color = blue");

		// Adapter
		String adapterStyle = "style = filled, fillcolor = red";
		styles.put("adapter", adapterStyle);
		styles.put("adaptee", adapterStyle);
		styles.put("target", adapterStyle);

		// Decorator
		String decoratorStyle = "style = filled, fillcolor = green";
		styles.put("decorator", decoratorStyle);
		styles.put("component", decoratorStyle);
		
		// Composite
		String compositeStyle = "style = filled, fillcolor = yellow";
		styles.put("Composite", compositeStyle);
		styles.put("Component", compositeStyle);
		styles.put("Leaf", compositeStyle);
	}

	public String getStyleByType(String type) {
		if (!styles.containsKey(type))
			return defaultStyle;
		return styles.get(type);
	}
}
