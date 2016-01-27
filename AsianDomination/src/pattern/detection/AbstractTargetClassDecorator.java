package pattern.detection;

import api.ITargetClass;

public abstract class AbstractTargetClassDecorator {
	protected String _associatedClassName;
	protected ITargetClass _decoratedClass;

	public AbstractTargetClassDecorator(String _associatedClassName, ITargetClass _decoratedClass) {
		this._associatedClassName = _associatedClassName;
		this._decoratedClass = _decoratedClass;
	}

	public String getAssociatedClassName() {
		return _associatedClassName;
	}
	
	

}
