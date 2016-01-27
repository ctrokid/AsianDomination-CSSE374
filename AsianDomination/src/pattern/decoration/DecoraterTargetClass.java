package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class DecoraterTargetClass extends AbstractTargetClassDecorator {

	public DecoraterTargetClass(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
		// TODO Auto-generated constructor stub
	}

}
