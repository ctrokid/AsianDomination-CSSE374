package utils;

import java.util.HashMap;
import java.util.Map;

public class ClassStyle {
	private Map<String, String> styles;

	public ClassStyle() {
		styles = new HashMap<String, String>();
		styles.put("type", "");
		styles.put("styleRunzhi", "solid");
		styles.put("color", "black");

		// // Singleton
		// styles.put("Singleton", "style = solid, color = blue");
		//
		// // Adapter
		// String adapterStyle = "style = filled, fillcolor = red";
		// styles.put("adapter", adapterStyle);
		// styles.put("adaptee", adapterStyle);
		// styles.put("target", adapterStyle);
		//
		// // Decorator
		// String decoratorStyle = "style = filled, fillcolor = green";
		// styles.put("decorator", decoratorStyle);
		// styles.put("component", decoratorStyle);
		//
		// // Composite
		// String compositeStyle = "style = filled, fillcolor = yellow";
		// styles.put("Composite", compositeStyle);
		// styles.put("Component", compositeStyle);
		// styles.put("Leaf", compositeStyle);
	}

	public void addConfig(String config, String setting) {
		styles.put(config, setting);
	}

	public String getClassTypeWithCarrots() {
			return " \\n\\<\\<" + styles.get("type") + "\\>\\>";
	}

	public String getClassType() {
			return styles.get("type");
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String config : styles.keySet()) {
			if (config.equals("type")) {
				continue;
			}
			str.append(str + "=" + styles.get(config)+",");
		}
		return str.toString().trim();

	}
}
