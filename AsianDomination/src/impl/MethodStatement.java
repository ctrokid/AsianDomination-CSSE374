package impl;

import api.IMethodStatement;
import utils.SignatureParser;
import visitor.IVisitor;

public class MethodStatement implements IMethodStatement {
	private String _callerClass; // previousClass
	private String _classToCall; // className
	private String _methodName;
	private String _parameters;

	public MethodStatement(String _callerClass, String _classToCall, String methodName, String _return) {
		this._callerClass = _callerClass;
		this._classToCall = _classToCall;
		this._methodName = methodName;
//		System.out.println("class is: " + _callerClass);
//		System.out.println(" return is: " + _return);
		String params = SignatureParser.getParams(_return, true).toString();
		this._parameters = params.substring(1, params.length() - 1);
	}

	public String getClassToCall() {
		return _classToCall;
	}

	public String getMethodName() {
		return _methodName;
	}

	public String getParameters() {
		return _parameters;
	}

	public String getCallerClass() {
		return _callerClass;
	}

	@Override
	public String toString() {
		return "MethodStatement: " + getCallerClass() + " " + getClassToCall() + " " + getMethodName() + " "
				+ getParameters();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_callerClass == null) ? 0 : _callerClass.hashCode());
		result = prime * result + ((_classToCall == null) ? 0 : _classToCall.hashCode());
		result = prime * result + ((_methodName == null) ? 0 : _methodName.hashCode());
		result = prime * result + ((_parameters == null) ? 0 : _parameters.hashCode());
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
		if (_callerClass == null) {
			if (other._callerClass != null)
				return false;
		} else if (!_callerClass.equals(other._callerClass))
			return false;
		if (_classToCall == null) {
			if (other._classToCall != null)
				return false;
		} else if (!_classToCall.equals(other._classToCall))
			return false;
		if (_methodName == null) {
			if (other._methodName != null)
				return false;
		} else if (!_methodName.equals(other._methodName))
			return false;
		if (_parameters == null) {
			if (other._parameters != null)
				return false;
		} else if (!_parameters.equals(other._parameters))
			return false;
		return true;
	}

	public void accept(IVisitor v) {
		v.visit(this);
	}
	

}
