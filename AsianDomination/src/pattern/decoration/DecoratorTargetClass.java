package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class DecoratorTargetClass extends GraphVizStyleTargetClass {

	public DecoratorTargetClass(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
		switch (pattern) {
			case DECORATOR_COMPONENT: 
				this.graphVizStyle.addConfig("type", "component");
				break;
			case DECORATOR_DECORATOR:
				this.graphVizStyle.addConfig("type", "decorator");
				break;
			case DECORATOR_CONCRETE:
				this.graphVizStyle.addConfig("type", "concrete");
				break;
			default:
				break;
		}
	}
	
}
