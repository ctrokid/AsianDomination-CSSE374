package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class CompositeDecorator extends GraphVizStyleTargetClass {

	public CompositeDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
		this.graphVizStyle.addConfig("style", "filled");
		this.graphVizStyle.addConfig("fillcolor", "yellow");
		switch (pattern) {
			case COMPOSITE_COMPONENT:
//				setPatternString("\\n\\<\\<Component\\>\\>");
				graphVizStyle.addConfig("type", "Component");
				break;
			case COMPOSITE_COMPOSITE:
//				setPatternString("\\n\\<\\<Composite\\>\\>");
				graphVizStyle.addConfig("type", "Composite");
				break;
			case COMPOSITE_LEAF:
//				setPatternString("\\n\\<\\<Leaf\\>\\>");
				graphVizStyle.addConfig("type", "Leaf");
				break;
			default:
				break;
		}
	}

}
