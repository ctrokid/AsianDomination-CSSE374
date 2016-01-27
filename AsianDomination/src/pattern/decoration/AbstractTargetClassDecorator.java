package pattern.decoration;

import api.ITargetClass;
import pattern.detection.PATTERN_TYPE;

public abstract class AbstractTargetClassDecorator {
	protected PATTERN_TYPE pattern;
	protected String _associatedClassName;
	protected ITargetClass _decoratedClass;

	public AbstractTargetClassDecorator(PATTERN_TYPE pattern, String _associatedClassName, ITargetClass _decoratedClass) {
		this.pattern = pattern;
		this._associatedClassName = _associatedClassName;
		this._decoratedClass = _decoratedClass;
	}

	public String getAssociatedClassName() {
		return _associatedClassName;
	}
	
	public PATTERN_TYPE getPatternType(){
		return pattern;
	}
	
	

}
