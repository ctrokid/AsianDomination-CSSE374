package pattern.decoration;

import java.util.HashMap;
import java.util.Map;

import api.IProjectModel;
import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class PatternConfig {
	private static Map<PATTERN_TYPE, HashMap<String, String>> patternTypes;
	private static PatternConfig uniqueInstance;

	private PatternConfig() {
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
		map.put("color", "blue");
		map.put("type", "Singleton");
		patternTypes.put(PATTERN_TYPE.SINGLETON, map);
	}

	public static synchronized PatternConfig getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new PatternConfig();
		}
		return uniqueInstance;
	}

	public void decorate(PATTERN_TYPE type, ITargetClass clazz, IProjectModel model) {
		GraphVizStyleTargetClass local;
		
		if(GraphVizStyleTargetClass.class.isAssignableFrom(clazz.getClass())){
			local = (GraphVizStyleTargetClass)clazz;
		} else {
			System.out.println(clazz.getClassName());
			local = new GraphVizDefaultSytleDecorator(clazz);
			model.decorateClass(local);
		}
		
		HashMap<String, String> pattern = patternTypes.get(type);
		for (String str : pattern.keySet()) {
			local.addConfig(str, pattern.get(str));
		}
	}
}
