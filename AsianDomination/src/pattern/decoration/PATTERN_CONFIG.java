package pattern.decoration;

import java.util.HashMap;
import java.util.Map;

import pattern.detection.PATTERN_TYPE;

public class PATTERN_CONFIG {
	private static Map<PATTERN_TYPE, HashMap<String, String>> patternTypes;
	private static PATTERN_CONFIG uniqueInstance;

	private PATTERN_CONFIG() {
		patternTypes = new HashMap<PATTERN_TYPE, HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<>();
		map.put("style", "filled");
		map.put("fillcolor", "red");
		map.put("type", "adapter");
		patternTypes.put(PATTERN_TYPE.ADAPTER_ADAPTER, map);

		map = new HashMap<>();
		map.put("style", "filled");
		map.put("fillcolor", "red");
		map.put("type", "adaptee");
		patternTypes.put(PATTERN_TYPE.ADAPTER_ADAPTEE, map);

		map = new HashMap<>();
		map.put("style", "filled");
		map.put("fillcolor", "red");
		map.put("type", "target");
		patternTypes.put(PATTERN_TYPE.ADAPTER_TARGET, map);

		map = new HashMap<>();
		map.put("style", "filled");
		map.put("fillcolor", "yellow");
		map.put("type", "Component");
		patternTypes.put(PATTERN_TYPE.COMPOSITE_COMPONENT, map);

		map = new HashMap<>();
		map.put("style", "filled");
		map.put("fillcolor", "yellow");
		map.put("type", "Composite");
		patternTypes.put(PATTERN_TYPE.COMPOSITE_COMPOSITE, map);

		map = new HashMap<>();
		map.put("style", "filled");
		map.put("fillcolor", "yellow");
		map.put("type", "Leaf");
		patternTypes.put(PATTERN_TYPE.COMPOSITE_LEAF, map);

		map = new HashMap<>();
		map.put("style", "filled");
		map.put("fillcolor", "green");
		map.put("type", "component");
		patternTypes.put(PATTERN_TYPE.DECORATOR_COMPONENT, map);

		map = new HashMap<>();
		map.put("style", "filled");
		map.put("fillcolor", "green");
		map.put("type", "decorator");
		patternTypes.put(PATTERN_TYPE.DECORATOR_DECORATOR, map);

		map = new HashMap<>();
		map.put("style", "filled");
		map.put("fillcolor", "green");
		map.put("type", "concrete");
		patternTypes.put(PATTERN_TYPE.DECORATOR_CONCRETE, map);

		map = new HashMap<>();
		map.put("style", "solid");
		map.put("fillcolor", "blue");
		map.put("type", "Singleton");
		patternTypes.put(PATTERN_TYPE.SINGLETON, map);
	}

	public static synchronized PATTERN_CONFIG getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new PATTERN_CONFIG();
		}
		return uniqueInstance;
	}

	public void setConfig(PATTERN_TYPE type, GraphVizStyleTargetClass clazz) {
		HashMap<String, String> pattern = patternTypes.get(type);
		for (String str : pattern.keySet()) {
			clazz.addConfig(str, pattern.get(str));
		}
	}
}
