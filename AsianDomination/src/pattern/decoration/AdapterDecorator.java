package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class AdapterDecorator extends AbstractTargetClassDecorator {

	public AdapterDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
		switch (pattern) {
		case ADAPTER_ADAPTER:
			setPatternString("\\n\\<\\<adapter\\>\\>");
			break;
		case ADAPTER_ADAPTEE:
			setPatternString("\\n\\<\\<adaptee\\>\\>");
			break;
		case ADAPTER_TARGET:
			setPatternString("\\n\\<\\<target\\>\\>");
			break;
		default:
			break;
		}
	}

}
