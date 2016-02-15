package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class SingletonDecorator extends GraphVizStyleTargetClass {

	public SingletonDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
	}
}
