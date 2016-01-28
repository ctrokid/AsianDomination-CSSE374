package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public class AdapteeDecorator extends AbstractTargetClassDecorator {

	private AdapteeDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		super(pattern, _associatedClassName, _decoratedClass);
		// TODO Auto-generated constructor stub
	}
	
	public AdapteeDecorator(ITargetClass _decoratedClass){
		super(PATTERN_TYPE.ADAPTER_ADAPTEE, "", _decoratedClass);
	}
	
	
}
