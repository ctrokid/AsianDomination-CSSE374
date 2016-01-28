package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class TargetDecorator extends AbstractTargetClassDecorator {

	private TargetDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
	}
	
	public TargetDecorator(ITargetClass _decoratedClass) {
		super(PATTERN_TYPE.ADAPTER_TARGET, "", _decoratedClass);
	}
}
