package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class CompositeDecorator extends AbstractTargetClassDecorator {

	public CompositeDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
		
		switch (pattern) {
			case COMPOSITE_COMPONENT:
				setPatternString("\\n\\<\\<Component\\>\\>");
				break;
			case COMPOSITE_COMPOSITE:
				setPatternString("\\n\\<\\<Composite\\>\\>");
				break;
			case COMPOSITE_LEAF:
				setPatternString("\\n\\<\\<Leaf\\>\\>");
				break;
			default:
				break;
		}
	}

}
