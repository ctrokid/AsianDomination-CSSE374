package impl;

import java.util.Arrays;

import api.ITargetClassPart;
import visitor.IVisitor;

public class MethodStatement implements ITargetClassPart {
	private String _className;
	private String _methodName;
	private String[] _params;
	private String _return;
	
	public MethodStatement(String _className, String _methodName, String[] _params, String _return) {
		this._className = _className;
		this._methodName = _methodName;
		this._params = _params;
		this._return = _return;
	}

	public String getClassName() {
		return _className;
	}

	public String getMethodName() {
		return _methodName;
	}

	public String[] getParams() {
		return _params;
	}

	public String getReturn() {
		return _return;
	}
	
	@Override
	public String toString() {
		return "MethodStatement: " + getClassName() + " " + getMethodName() + " " + getReturn();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_className == null) ? 0 : _className.hashCode());
		result = prime * result
				+ ((_methodName == null) ? 0 : _methodName.hashCode());
		result = prime * result + Arrays.hashCode(_params);
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
		
		if (!Arrays.equals(_params, other._params))
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
