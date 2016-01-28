package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class DecoratorTargetClass extends AbstractTargetClassDecorator {

	public DecoratorTargetClass(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
		switch (pattern) {
			case DECORATOR_COMPONENT: 
				setPatternString("\\n\\<\\<component\\>\\>");
				break;
			case DECORATOR_DECORATOR:
				setPatternString("\\n\\<\\<decorator\\>\\>");
				break;
			case DECORATOR_CONCRETE:
				setPatternString("\\n\\<\\<decorator\\>\\>");
				break;
			default:
				break;
		}
	}
	
}
