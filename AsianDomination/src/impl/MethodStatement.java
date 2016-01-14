package impl;

import api.ITargetClassPart;
import visitor.IVisitor;

public class MethodStatement implements ITargetClassPart {
	private String _previousClass;
	private String _className;
	private String _methodName;
	private String _return;
	
	public MethodStatement(String _previousClass, String _className, String methodName, String _return) {
		this._previousClass = _previousClass;
		this._className = _className;
		this._methodName = methodName;
//		setMethodNameAndParameters(methodName);
		this._return = _return;
	}

	private void setMethodNameAndParameters(String methodName) {
		String[] tokens = methodName.split(" ");
		_methodName = tokens[0];
//		TODO: later. parse this BS
//		String params = tokens[1].split(regex);
	}

	public String getClassName() {
		return _className;
	}

	public String getMethodName() {
		return _methodName;
	}

	public String getReturn() {
		return _return;
	}
	
	public String getPreviousClass() {
		return _previousClass;
	}
	
	@Override
	public String toString() {
		return "MethodStatement: " + getPreviousClass() + " " + getClassName() + " " + getMethodName() + " " + getReturn();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_className == null) ? 0 : _className.hashCode());
		result = prime * result + ((_methodName == null) ? 0 : _methodName.hashCode());
		result = prime * result + ((_previousClass == null) ? 0 : _previousClass.hashCode());
		result = prime * result + ((_return == null) ? 0 : _return.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MethodStatement other = (MethodStatement) obj;
		if (_className == null) {
			if (other._className != null)
				return false;
		} else if (!_className.equals(other._className))
			return false;
		if (_methodName == null) {
			if (other._methodName != null)
				return false;
		} else if (!_methodName.equals(other._methodName))
			return false;
		if (_previousClass == null) {
			if (other._previousClass != null)
				return false;
		} else if (!_previousClass.equals(other._previousClass))
			return false;
		if (_return == null) {
			if (other._return != null)
				return false;
		} else if (!_return.equals(other._return))
			return false;
		return true;
	}

	@Override
	public void accept(IVisitor v) {
		v.visit(this);
	}
	
}
