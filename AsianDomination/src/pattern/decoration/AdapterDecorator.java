package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class AdapterDecorator extends AbstractTargetClassDecorator {

	private AdapterDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
	}
	
	public AdapterDecorator(ITargetClass _decoratedClass){
		super(PATTERN_TYPE.ADAPTER_ADAPTER, "", _decoratedClass);
	}
	
}
