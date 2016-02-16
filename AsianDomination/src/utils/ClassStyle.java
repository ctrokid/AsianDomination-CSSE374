package utils;

import java.util.HashMap;
import java.util.Map;

public class ClassStyle {
	private Map<String, String> styles;
	private String color;

	public ClassStyle() {
		styles = new HashMap<String, String>();
		styles.put("type", "");
		styles.put("style", "solid");
		styles.put("color", "black");
		color = "black";
	}

	public void addConfig(String config, String setting) {
		if (config.contains("color"))
			color = setting;
		styles.put(config, setting);
	}
	
	public String getColor() {
		return color;
	}

	public String getClassTypeWithCarrots() {
		if (styles.get("type").equals(""))
			return "";
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
			str.append(config + "=" + styles.get(config) + ",");
		}
		return str.toString().trim();

	}
}
